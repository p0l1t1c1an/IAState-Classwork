
#include <ctype.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/param.h>
#include <sys/types.h>
#include <sys/wait.h>


// Jobs to keep track of child processes
struct job {
    pid_t pid;
    char *name;
};

// List of jobs
struct child_jobs {
    size_t len, cap; // Len == many jobs are running/not cleared, cap == max size of array
    struct job jobs[];
} *child_jobs = NULL;

// Utilities for child_jobs global above
// Removes job by PID
void free_job_by_pid(pid_t pid) {
    if(child_jobs != NULL && child_jobs->len > 0) {
        size_t i, len = child_jobs->len;
        for(i = 0; i < len; i++) {
            struct job *curr_job = &child_jobs->jobs[i];
            if(curr_job->pid == pid) {
                free(curr_job->name);
                if(i < len -1)
                    memcpy(curr_job, &child_jobs->jobs[len-1] , sizeof(struct job));
                child_jobs->len -= 1;
            }
        }
    }
}

// Finds jobs by PID
struct job *find_job_by_pid(pid_t pid) {
    if(child_jobs != NULL && child_jobs->len > 0) {
        size_t i, len = child_jobs->len;
        for(i = 0; i < len; i++) {
            struct job *curr_job = &child_jobs->jobs[i];
            if(curr_job->pid == pid) {
                return curr_job;
            }
        }
    }
    return NULL;
}

// Adds jobs to list
// Will initialize memory allocation 
// Will expand array as well
void add_job(const struct job *j) { 
    if(child_jobs == NULL) {
        child_jobs = malloc(2*sizeof(size_t) + 4*sizeof(struct job)); 
        child_jobs->len = 0;
        child_jobs->cap = 4;
    }

    size_t len = child_jobs->len,
           cap = child_jobs->cap;
    
    if(len >= cap) {
        child_jobs = realloc(child_jobs, sizeof(size_t) * 2 + sizeof(struct job) * (len + 4));
        child_jobs->cap += 4;
    } 
        
    struct job *new_job = &child_jobs->jobs[len];
    new_job->pid = j->pid;

    new_job->name = malloc(sizeof(char) * strlen(j->name));
    strncpy(new_job->name, j->name, strlen(j->name));

    child_jobs->len += 1;
}

// Frees all names in jobs 
// Frees the entire array / list object 
void free_jobs(void) {
    if(child_jobs != NULL && child_jobs->len > 0) {
        size_t i, len = child_jobs->len;
        for(i = 0; i < len; i++) {
            struct job *curr_job = &child_jobs->jobs[i];
            free(curr_job->name);
        }
    }
    if(child_jobs != NULL) 
        free(child_jobs);

    child_jobs = NULL;
}

// Command struct for holding info to pass to execvp
struct command {
    char *cmd;      // I made this before I realized argv needed to start with the command
    char *argv[];   // Now it is stuck like this as too much time is needed to change it
};

// Command Types to know what to do with command
enum command_type {
    FAIL,
    SPACES,
    FOREGROUND,
    BACKGROUND
};

// Generates command struct of varying size based on arguments typed in shells command prompt
enum command_type gen_command(char *line, ssize_t len, struct command **c) {
    int i = 0, start = 0;
    size_t num_args = 0, count = 0;
    int is_background = 0;

    for(i = 0; i < len; ++i) {
        if(!isprint(line[i]) && !isspace(line[i])) {
            return FAIL; // Fail if nonprintable character in command (minus spaces like \t)
        }
        if(line[i] == '&') {
            is_background = 1;
            break; // I aint running more than one command
        }
        else if((isspace(line[i+1]) || line[i+1] == '\0') && !isspace(line[i])) {
            num_args++; // arguement ends with space or end of string
        }
    }
    
    if(num_args <= 0)
        return SPACES;
    
    *c = malloc(sizeof(char *) * (num_args+2)); // +2 is for cmd and null 
    
    for(i = 0; i < len; ++i) {
        if(isspace(line[i]) && !isspace(line[i+1])) { // commands can be separated by arbitrary # of spaces
            start = i+1; // marks start of arguement 
        } else if(!isspace(line[i]) && isspace(line[i+1])) { // end of arguement is at i
           if(count == 0) {
                (*c)->cmd = malloc(i - start + 2); // i - start is only chars past start (+2 for start char and \0) 
                strncpy((*c)->cmd, &line[start], i - start + 1); // copy start to i chars
                (*c)->cmd[i-start+1] = '\0'; // add null
           } 
           (*c)->argv[count] = malloc(i - start + 2);
           strncpy((*c)->argv[count], &line[start], i - start + 1);
           (*c)->argv[count][i-start+1] = '\0';
           count++;
        }
    }

    (*c)->argv[num_args] = NULL; // Last arg is null

    return is_background ? BACKGROUND : FOREGROUND;
}

// Frees command struct 
// Free command name and list of args
void free_command(struct command *c) {
    free(c->cmd);
    int i;
    for(i = 0; c->argv[i] != NULL; i++) {
        free(c->argv[i]);
    }
    free(c);
}


// Built in commands that could be run
// Used to know if one was run or $PATH command should be run
enum built_ins {
    EXIT,
    PID,
    PPID,
    CD, 
    PWD,
    JOBS,
    NOT // No built in was run
};

// Runs a built in command or returns NOT to tell main to use execvp
enum built_ins run_built_in(const struct command *c) {
    if(!strcmp(c->cmd, "exit")) {
        return EXIT;            // Main will jump to exit
    } 

    if(!strcmp(c->cmd, "pid")) {
        printf("Shell pid: %d\n", getpid()); // Prints getpid
        return PID;
    }
 
    if(!strcmp(c->cmd, "ppid")) {
        printf("Shell's Parent pid: %d\n", getppid()); // Prints getppid
        return PPID;
    } 

    if(!strcmp(c->cmd, "cd") && !strcmp(c->argv[0], "cd")) {
        int fail = 0;
        if(c->argv[1] != NULL) { // checks if directory is given 
            fail = chdir(c->argv[1]); 
        } else {
            fail = chdir(getenv("HOME")); // use home if not
        }
        
        if(fail) { 
            perror("cd"); // prints error if chdir fails
        }

        return CD; 
    }
    
    if(!strcmp(c->cmd, "pwd")) {
        char *dir = NULL;
        getcwd(dir, MAXPATHLEN); // use getcwd  
        printf("%s\n", dir);
        free(dir);              // getcwd will alloc if passed null
        return PWD;
    }

    if(!strcmp(c->cmd, "jobs")) { 
        size_t i, len = child_jobs->len;
        for(i = 0; i < len; i++){       // loop through jobs in global
            struct job *curr = &child_jobs->jobs[i];
            printf("[%d] %s\n", curr->pid, curr->name); // print values in list
        }
        return JOBS;
    }
 
    return NOT;
}


int main(int argc, char *argv[]) 
{
    struct command *c = NULL;
    char *line = NULL,
         *prompt = "308sh> ";
    size_t thats_cap = 0;
    ssize_t len;
    int error = 0;
    int status;
    pid_t pid;
    enum command_type type = FAIL;

    // Checking arguements passed for prompt
    if(argc != 1 && argc != 3) {
        printf("Incorrect usage: \n./shell [-p prompt]\n");
        goto Exit;
    } else if(argc == 3 && !strcmp(argv[1], "-p")) {
        prompt = argv[2]; // set prompt
    }

    // Loop
    while(1) {
        usleep(1000); 
        printf("%s", prompt);
        if((len = getline(&line, &thats_cap, stdin)) <= 0) { // Getline so it is not arbitrary size input
            fprintf(stderr, "Failed to read command line\n");
            error = -1;
            continue;
        }
       
        // Generate command struct from input line
        if((type = gen_command(line, len, &c)) == FAIL) {
            fprintf(stderr, "Couldn't parse command from line\n");
            error = -2;
            goto FreeLine;
        }

        // All spaces inputed
        if(type == SPACES) {
            goto FreeLine;
        }
        
        // Check for which built was run if any
        switch (run_built_in(c)) {
            default:
                goto FreeCommand;
            case EXIT:
                goto ExitCommand; // Only way to exit loop as parent
            case NOT:
               break; 
        }

        // No built in is run past this point

        // Fork and check if fails
        if((pid = fork()) < 0) {
            perror("Fork Failed");
            error = -3;
            goto FreeCommand; // Not needed but nice to know where it goes
        } else if(pid == 0) { // Child
            printf(">>> [%d] %s\n", getpid(), c->cmd); // Print what will be run
            execvp(c->cmd, c->argv); // Execute command
            perror("Command Not Found");
            error = -4;
            goto ExitCommand; 
        } else if(type == FOREGROUND) {
            waitpid(pid, &status, 0); // Waits
            if(WIFEXITED(status)){ // Checking status
		        printf(">>> [%d] %s Exited %d\n", pid, c->cmd, WEXITSTATUS(status));
	        }else if (WIFSIGNALED(status)){
		        printf(">>> [%d] %s Killed %d\n", pid, c->cmd, WTERMSIG(status));
	        }else if (WIFSTOPPED(status)){
		        printf(">>> [%d] %s Stopped %d\n", pid, c->cmd, WSTOPSIG(status));
	        }else if (WIFCONTINUED(status)){
		        printf(">>> [%d] %s Continued %d\n", pid, c->cmd, status);
	        }
        } else { // Must be BACKGROUND
            struct job j = {.pid = pid, .name = c->cmd};
            add_job(&j); // Add pid and command name to job list global
        }
        

        // Ways to free/reset data
        // Ulgy and confusing sure - but I like make bad code
FreeCommand:
        free_command(c);
        c = NULL;

FreeLine:    
        free(line);
        line = NULL;
    

        // Check if background processes have ended
        pid_t child;
        while((child = waitpid(-1, &status, WNOHANG)) > 0) {
            struct job *j = find_job_by_pid(child);
            if(j != NULL) {
                if(WIFEXITED(status)){
                    printf(">>> [%d] %s Exited %d\n", child, j->name, WEXITSTATUS(status));
                }else if (WIFSIGNALED(status)){
                    printf(">>> [%d] %s Killed %d\n", child, j->name, WTERMSIG(status));
                }else if (WIFSTOPPED(status)){
                    printf(">>> [%d] %s Stopped %d\n", child, j->name, WSTOPSIG(status));
                }else if (WIFCONTINUED(status)){
                    printf(">>> [%d] %s Continued %d\n", child, j->name, status);
                }
                free_job_by_pid(child); // Remove child from list 
            }
        }

    } // End of while loop

    // Decide what to free based on if they are alloced or not
    if(c == NULL && line == NULL) {
        goto Exit;
    } else if(c == NULL) { 
        goto ExitLine;      // Line must be alloced if we get command
    } 
    

// Ways to exit depending on what needs to be dealloced
ExitCommand:
    free_command(c);

ExitLine:    
    free(line);

Exit:
    free_jobs(); // Frees global jobs list
    return error;
}

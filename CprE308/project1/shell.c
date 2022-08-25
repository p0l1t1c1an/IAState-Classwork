
#include <ctype.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/param.h>
#include <sys/types.h>
#include <sys/wait.h>

static volatile sig_atomic_t running = 1;

static void handle_inter(int _) {
    (void) _;
    running = 0;
}

struct command {
    char *cmd;
    char *argv[];
};

int gen_command(char *line, ssize_t len, struct command **c) {
    int i = 0, start = 0;
    size_t num_args = 0, count = 0;

    for(i = 0; i < len; ++i) {
        if((isspace(line[i+1]) || line[i+1] == '\0') && !isspace(line[i])) {
            num_args++;
        }
    }
    
    if(num_args <= 0)
        return -1;
    
    *c = malloc(sizeof(char *) * (num_args+2));
    
    for(i = 0; i < len; ++i) {
        if(isspace(line[i]) && !isspace(line[i+1])) {
            start = i+1;
        } else if(!isspace(line[i]) && isspace(line[i+1])) {
           if(count == 0) {
                (*c)->cmd = malloc(i - start + 2);
                strncpy((*c)->cmd, &line[start], i - start + 1);
                (*c)->cmd[i-start+1] = '\0';
           } 
           (*c)->argv[count] = malloc(i - start + 2);
           strncpy((*c)->argv[count], &line[start], i - start + 1);
           (*c)->argv[count][i-start+1] = '\0';
           count++;
        }
    }

    (*c)->argv[num_args] = NULL; 

    return 0;
}

enum built_ins {
    EXIT,
    PID,
    PPID,
    CD, 
    PWD, 
    NOT
};

enum built_ins run_built_in(const struct command *c) {

    if(!strcmp(c->cmd, "exit")) {
        return EXIT;
    } 

    if(!strcmp(c->cmd, "pid")) {
        printf("Shell pid: %d\n", getpid());
        return PID;
    }
 
    if(!strcmp(c->cmd, "ppid")) {
        printf("Shell's Parent pid: %d\n", getppid());
        return PPID;
    } 

    if(!strcmp(c->cmd, "cd") && !strcmp(c->argv[0], "cd")) {
        if(c->argv[1] != NULL) {
            chdir(c->argv[1]);
        } else {
            chdir(getenv("HOME"));
        }
        return CD;
    }
    
    if(!strcmp(c->cmd, "pwd")) {
        char *dir = NULL;
        getcwd(dir, MAXPATHLEN);
        printf("%s\n", dir);
        return PWD;
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

//    signal(SIGINT, handle_inter);
    if(argc != 1 && argc != 3) {
        printf("Incorrect usage: \n./shell [-p prompt]\n");
        goto Exit;
    } else if(argc == 3 && !strcmp(argv[1], "-p")) {
        prompt = argv[2];
    }

    while(running) {
        printf("%s", prompt);
        if((len = getline(&line, &thats_cap, stdin)) <= 0) {
            fprintf(stderr, "Failed to read command line");
            error = -1;
            continue;
        }
        
        if(gen_command(line, len, &c)) {
            fprintf(stderr, "Couldn't parse command from line: %s", line);
            error = -2;
            goto FreeLine;
        }

        switch (run_built_in(c)) {
            default:
                goto FreeCommand;
            case EXIT:
                goto ExitCommand;
            case NOT:
               break; 
        }

        if((pid = fork()) < 0) {
            perror("Fork Failed");
            error = -3;
            goto FreeCommand; // Not needed but nice to know where it goes
        } else if( pid == 0) {
            execvp(c->cmd, c->argv);
            perror("Command Not Found");
            error = -4;
            goto ExitCommand;
        } else {
            printf("[%d] %s\n", pid, c->cmd);
            waitpid(pid, &status, 0);
            if(WIFEXITED(status)) {

            }
            // check status to see if succeed
            // if not
                // quit and perror
        }
        
        // Ways to free/reset data
FreeCommand:
        free(c);
        c = NULL;

FreeLine:    
        free(line);
        line = NULL;
    }

    if(c == NULL && line == NULL) {
        goto Exit;
    } else if(c == NULL) { 
        goto ExitLine;      // Line must be alloced if we get command
    } 
    

// Ways to exit depending on what needs to be dealloced
ExitCommand:
    free(c);

ExitLine:    
    free(line);

Exit:
    return error;
}

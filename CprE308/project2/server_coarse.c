
#include <ctype.h>
#include <sys/select.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <errno.h>

#include <sys/param.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/time.h>

#include "Bank.h"


// ---------------REQUESTS-------------------

struct trans {
    int acc;
    int amount;
};

enum req_type {
    CHECK,
    TRANS,
    END,
};

struct request {
    struct request *next;
    struct timeval start, end;
    int type, id, check_acc;
    size_t len;
    struct trans transactions[];
};

// Build CHECK type request
struct request *check_req(struct timeval s, int id, int acc) {
    struct request *r = malloc(sizeof(struct request));
    if(r == NULL)
        return NULL;

    r->next = NULL;
    r->start = s;
    memset(&r->end, 0, sizeof(struct timeval));
    r->type = CHECK;
    r->id = id;
    r->check_acc = acc;
    r->len = 0;

    return r;
}

// Build TRANS type request
struct request *trans_req(struct timeval s, int id, size_t len, struct trans *t) {
    struct request *r = malloc(sizeof(struct request) + len * sizeof(struct trans));
    if(r == NULL)
        return NULL;

    r->next = NULL;
    r->start = s;
    memset(&r->end, 0, sizeof(struct timeval));
    r->type = TRANS;
    r->id = id;
    r->check_acc = -1;
    r->len = len;
    memcpy(&r->transactions, t, len *sizeof(struct trans));

    return r;
}

// Build END type request
struct request *end_req(struct timeval s, int id) {
    struct request *r = malloc(sizeof(struct request));
    if(r == NULL)
        return NULL;

    r->next = NULL;
    r->start = s;
    memset(&r->end, 0, sizeof(struct timeval));
    r->type = END;
    r->id = id;
    r->check_acc = -1;
    r->len = 0;

    return r;
}

// ---------------QUEUE MANAGEMENT-------------------

struct queue {
    struct request *head, *tail;
}q;

pthread_rwlock_t queue_lock = PTHREAD_RWLOCK_INITIALIZER;

// I don't use this but looks at head using only read lock
struct request *queue_peek(void){
    struct request *h = NULL;
    if(pthread_rwlock_rdlock(&queue_lock) == 0){
        h = q.head;
        pthread_rwlock_unlock(&queue_lock);
    }
    return h;
}

// Acquires write lock then removes head if not null
// Sets head and tail to null if taking last request
struct request *queue_remove(void){
    struct request *h = NULL;
    if(pthread_rwlock_wrlock(&queue_lock) == 0) {
        if(q.head != NULL){
            h = q.head;
            if(h->next == NULL) {
                q.head = NULL; 
                q.tail = NULL;
            } else {
                q.head = h->next;
            }
        }
        pthread_rwlock_unlock(&queue_lock);
    }
    return h;
}

// Add request to queue as tail
// Sets head and tail if queue is empty
void queue_add(struct request *r){
    if(pthread_rwlock_wrlock(&queue_lock) == 0) {
        if(q.head == NULL){
            q.head = r;
            q.tail = r;
        } else {
            q.tail->next = r;
            q.tail = r;
        }
        pthread_rwlock_unlock(&queue_lock);
    }
}

// ---------------FILE MANAGEMENT-------------------

FILE *log_file;

// Locks and writes to log file for CHECK success
void log_check(struct request *r, int balance) {
    flockfile(log_file);
    fprintf(log_file, "%d BAL %d TIME %ld.%06ld %ld.%06ld\n", 
            r->id, balance, r->start.tv_sec, r->start.tv_usec, 
            r->end.tv_sec, r->end.tv_usec); 
    funlockfile(log_file);
}

// Locks and writes to log file for TRANS success
void log_trans(struct request *r) {
    flockfile(log_file);
    fprintf(log_file, "%d OK TIME %ld.%06ld %ld.%06ld\n", 
            r->id, r->start.tv_sec, r->start.tv_usec, 
            r->end.tv_sec, r->end.tv_usec);
    funlockfile(log_file);
}

// Locks and writes to log file for TRANS failing
void log_isf(struct request *r, int acc) {
    flockfile(log_file);
    fprintf(log_file, "%d ISF %d TIME %ld.%06ld %ld.%06ld\n", 
            r->id, acc,  r->start.tv_sec, r->start.tv_usec, 
            r->end.tv_sec, r->end.tv_usec);
    funlockfile(log_file);
}


// ---------------BANK LOCKING-------------------

pthread_rwlock_t bank_lock = PTHREAD_RWLOCK_INITIALIZER;

// Read lock bank
int rdlock_bank(void) {
    if(pthread_rwlock_rdlock(&bank_lock) == 0) {
        return 0; // success
    }
    return 1; // fail
}

// write lock bank
int wrlock_bank(void) {
    if(pthread_rwlock_wrlock(&bank_lock) == 0) {
        return 0; // success
    }
    return 1; // fail
}

//unlock bank
void unlock_bank(void) {
    pthread_rwlock_unlock(&bank_lock);
}   

// ---------------TRANSACTION--------------------

// Checks for sufficient funds 
// then adds amount to each account
int add_funds(struct request *r) {
    int i;
    struct trans *t = NULL;
    for(i = 0; i < r->len; i++) {
        t = &r->transactions[i];
        t->amount += read_account(t->acc);
        if(t->amount < 0)
            return t->acc; // ISF
    }  

    for(i = 0; i < r->len; i++) {
        t = &r->transactions[i];
        write_account(t->acc, t->amount);        
    }

    return 0;
}

// ---------------WORKER PTHREAD-------------------

void *worker_thread(void *arg) {
    (void) arg;
    struct request *r = NULL;

    while(1) {
        r = queue_remove();
        if(r == NULL) { // Queue Empty ?
            usleep(100);
            continue;
        }
        
        if(r->type == CHECK) {
            if(!rdlock_bank()) { // locks the whole bank
                int bal = read_account(r->check_acc); // read account info 
                unlock_bank();
                gettimeofday(&r->end, NULL);
                log_check(r, bal); // log balance
            }
            free(r);
        } else if(r->type == TRANS) {
            if(!wrlock_bank()) { // locks the whole bank
                int isf_acc = add_funds(r); // try to add funds
                unlock_bank();
                gettimeofday(&r->end, NULL);

                if(isf_acc == 0) { // add funds succeeded
                    log_trans(r);
                } else {            // add funds failed
                    log_isf(r, isf_acc);
                }
            }
            free(r);
       } else { // END type
            break; // don't free end request
        }
    }
    
    pthread_exit((void *)0);
}

// ---------------MAIN & HELPER FUNCTIONS-------------------

// Safe string to integer system
int strtoint(char *str, int *val) { 
    char *end;
    int l = strtol(str, &end, 10);
    if(*str && *end == 0) { 
        *val = l;
        return 0; // succeed
    }

    return 1; // fail
}

// Adds reads input line from user and 
// creates new request to add to the queue
int add_request(char *line, int id, int num_acc) {    
    int end = 0;
    const char *delim = " \t\n";
    struct trans transactions[10] = {0};
    struct request *r = NULL;
    
    struct timeval s;
    gettimeofday(&s, NULL); // Start time
    
    char *token;
    while((token = strsep(&line, delim)) != NULL) { // Loop getting tokens with space after them
        if(*token == '\0') { // spaces are replaced with \0 so when repeat we get a \0
            continue;
        } else {
            break;
        }
    }

    if(!strcmp(token, "CHECK")) { // first valid token is CHECK
        int acc;
        while((token = strsep(&line, delim)) != NULL) { // get next token
            if(*token == '\0') {
                continue;
            } else {
                break;
            }
        }
        
        if(strtoint(token, &acc)) { // convert to account number
            return 2;
        }
        
        if(acc <= 0 || acc > num_acc) { // valid account number
            return 3;
        }

        r = check_req(s, id, acc); // send request to check
    } else if(!strcmp(token, "TRANS")) {
        size_t len = 0, count = 0;
        int buf;

        // Loop through all tokens (max 20)
        while((token = strsep(&line, delim)) != NULL) {
            if(*token == '\0') {
                continue;
            } else {
                if(len >= 10) { // limits number of accounts
                    return 4;
                }
                if(strtoint(token, &buf)) { // 
                    return 2;
                }
                if(count % 2 == 0) { 
                    // if first number for a transaction
                    // then treated as acc
                    if(buf <= 0 || buf > num_acc) {
                        return 3;
                    }
                    transactions[len].acc = buf;
                } else {
                    // second is the amount
                    transactions[len].amount = buf;
                    len++;
                }
                count++;
            }
        }
        
        // number of args need to be even and not 0 
        if(count % 2 == 1 || len == 0) {
            return 5;
        }

        // Check if accounts repeat as that breaks how I add the amounts
        int i, k;
        for(i = 0; i < len; i++) {
            for(k = i+1; k < len; k++) {
                if(transactions[i].acc == transactions[k].acc) {
                    return 6;
                }
            }
        }

        r = trans_req(s, id, len, transactions);
    } else if(!strcmp(token, "END")) {
        // creates end request pointing to itself as next
        // makes queue endless with END request at end
        // main will free when all threads close
        r = end_req(s, id);
        r->next = r; 
        end = -1;
    } else {
        return 1;
    }

    if(r == NULL) { // failed to alloc request
        return 7;
    } 
    
    queue_add(r); // add the request
    return end;
}              


int main(int argc, char *argv[]) 
{
    char *prompt = "> "; // Kept a prompt
    char *line = NULL;
    size_t thats_cap = 0;
    ssize_t len;
    
    int error = 0;

    // Checking arguements passed for prompt
    if(argc != 4) {
        printf("Incorrect usage: \nserver <# of worker threads> <# of accounts> <output file>\n");
        return 1;
    }

    int num_thread, num_acc;

    // strtoint returns 1 on fail
    // OR it to check if either fail
    error |= strtoint(argv[1], &num_thread);
    error |= strtoint(argv[2], &num_acc);
    
    // Need at least 1 account and thread
    if(error || num_acc < 1 || num_thread < 1) {
        printf("Please enter valid numbers for # of threads and # of accounts");
        return 1;
    }

    char *file = argv[3];
    
    log_file = fopen(file,"w+");
    if (log_file == NULL) {
        perror("Couldn't open log file for server");
        return 2;
    }

    pthread_t threads[num_thread]; // Had to be before gotos ?
    
    // init the accounts first
    if(!initialize_accounts(num_acc)) {
        goto CLOSE_LOG;
    }
    
    // spawm all worker threads
    int i;
    for(i = 0; i < num_thread; i++) {
        if(pthread_create(&threads[i], NULL, worker_thread, NULL) != 0) {
            int k; 
            for(k = 0; k < i; k++) {
                pthread_cancel(threads[k]);
            }
            goto CLOSE_LOG;
        }
    }

    // Loop
    int code = 0, id = 1;
    while(1) {    
        printf("%s", prompt);    
        if((len = getline(&line, &thats_cap, stdin)) <= 0) { // Getline so it is not arbitrary size input    
            fprintf(stderr, "Failed to read command line\n");    
            error = -1;       
            continue;    
        }
        
        code = add_request(line, id, num_acc);
        if(code < 0) { // End request is negative
            break;
        }

        switch(code) {
            default:
                break;
            case 0: // Command success
                printf("> ID %d\n", id);
                id += 1;
                break;
            case 1: // Invalid
                printf("> Invalid Command\n");
                break;
            case 2: // StrToInt failed
                printf("> Couldn't Parse String To Int\n");
                break;
            case 3: // Number larger than accounts
                printf("> Invalid Account Number Range 1 - %d\n", num_acc);
                break;
            case 4: // Bad transaction
                printf("> Too Many Args For Transaction\n");
                break;
            case 5: // Other bad transaction
                printf("> Odd or Zero Args In Transaction\n");
                break;
            case 6: // Third bad transaction
                printf("> Duplicate Account In Transaction\n");
                break;
            case 7: // malloc failed somewhere
                printf("> Couldn't Allocate Request\n");
                break;
        }
    }
    
    free(line);

// JOIN_THREADS
    void *ret;
    for(i = 0; i < num_thread; i++) {
        pthread_join(threads[i], &ret);
    }


// CLEAR QUEUE
    struct request *r = NULL;
    while((r = queue_remove()) != NULL) {
        if(r->type == END) {
            free(r); // frees end request in queue
            break; 
        } else { 
            free(r);
        }
    }

CLOSE_LOG:
    fclose(log_file);

    return 0;
}


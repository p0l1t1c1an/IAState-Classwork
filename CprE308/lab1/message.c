
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "message.h"

static const char* message[] = {\
    "Hello Iowa!",
    "Goodbye Iowa!",
    "The penguins are coming!",
    "Caffeine is my friend!"
};

void print_message() {
    int index;
    srand(time(NULL));
    index = rand()/(RAND_MAX/4);
    printf("%s\n", message[index]);
}


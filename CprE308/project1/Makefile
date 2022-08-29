
EXE = shell
CC = gcc
SRC= $(wildcard *.c)
CFLAGS = -Wall -g -O3
LDFLAGS = -Wall
OBJ= $(SRC:.c=.o)

.PHONY : all

all : $(OBJ) $(EXE)

$(EXE) : $(OBJ)
	$(CC) $^ -o $@

%.o : %.c
	$(CC) $(CFLAGS) -c $^ -o $@


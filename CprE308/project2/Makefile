
EXE = appserver
EXE2 = appserver-coarse
CC = cc
SRC= Bank.c server.c server_coarse.c
CFLAGS = -pthread -Wall -g -O3
OBJ= $(SRC:.c=.o)

.PHONY : all

all : $(OBJ) $(EXE) $(EXE2)

$(EXE) : Bank.o server.o
	$(CC) $(CFLAGS) $^ -o $@

$(EXE2) : Bank.o server_coarse.o
	$(CC) $(CFLAGS) $^ -o $@

%.o : %.c
	$(CC) $(CFLAGS) -c $^ -o $@


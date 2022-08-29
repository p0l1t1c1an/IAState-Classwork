
EXE = memory_mgmt
CC = cc
SRC= memory_mgmt.c
CFLAGS = -pthread -Wall -g -O3
OBJ= $(SRC:.c=.o)

.PHONY : all

all : $(OBJ) $(EXE) $(EXE2)

$(EXE) : $(OBJ)
	$(CC) $(CFLAGS) $^ -o $@

%.o : %.c
	$(CC) $(CFLAGS) -c $^ -o $@



EXE1 = bytedump 
EXE2 = bsdump
EXE3 = fat12ls
CC = cc
SRC = bsdump-template.c bytedump.c fat12ls-template.c 
CFLAGS = -Wall -g -O3
OBJ = $(SRC:.c=.o)

.PHONY : all

all : $(OBJ) $(EXE1) $(EXE2) $(EXE3)

$(EXE1) : bytedump.o 
	$(CC) $(CFLAGS) $^ -o $@

$(EXE2) : bsdump-template.o 
	$(CC) $(CFLAGS) $^ -o $@

$(EXE3) : fat12ls-template.o 
	$(CC) $(CFLAGS) $^ -o $@

%.o : %.c
	$(CC) $(CFLAGS) -c $^ -o $@


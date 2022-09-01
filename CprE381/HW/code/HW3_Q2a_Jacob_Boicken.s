
.data
prompt: .asciiz "Enter the index in the Fibonacci Seq:\n"

ans1: .asciiz "Fibonacci #"
ans2: .asciiz " is equal to "

.text
.globl MAIN

MAIN: 
li $v0, 4
la $a0, prompt
syscall

# User input N into $v0
li $v0, 5
syscall 

addu $t0, $v0, $zero # n = user input
addu $t1, $v0, $zero # temp for output later

FIB:
# Initial Variables in Seq
li $s0, 0   # a = 0
li $s1, 1   # b = 1

j COND

# Psuedocode:
#
# fib(n):
#   a = 0
#   b = 1
#   
#   for n to 1:     (if n=0 loop is skipped)
#       c = a + b
#       a = b
#       b = c
#   
#   ret a
#

LOOP:
subiu $v0, $v0, 1       # subtract n by 1
addu $s2, $s0, $s1      # c = a + b
addu $s0, $s1, $zero    # a = b
addu $s1, $s2, $zero    # b = c

COND:
slti $t0, $v0, 1        # $t0 = (n < 1)  
beq $t0, $zero, LOOP    # if n >= 1

PRINT:
li $v0, 4
la $a0, ans1
syscall

li $v0, 1
addu $a0, $t1, $zero
syscall

li $v0, 4
la $a0, ans2
syscall

li $v0, 1
addu $a0, $s0, $zero
syscall

EXIT:


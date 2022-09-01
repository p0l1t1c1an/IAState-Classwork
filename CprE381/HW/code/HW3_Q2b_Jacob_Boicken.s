
.data
prompt: .asciiz "Enter the index in the Fibonacci Seq:\n"

ans1: .asciiz "Fibonacci #"
ans2: .asciiz " is equal to "

.text
.globl MAIN

j MAIN

# Psuedocode:
#
# fib(n):
#   if n < 2:
#       ret n
#   else:
#       ret fib(n-1) + fib(n-2)
#

# a0 is n 
FIB:

sltiu $t0, $a0, 2
bne $t0, $zero, RET_N

# store return address
subiu $sp, $sp, 8
sw $ra, 0($sp)

# build args in stack
subiu $sp, $sp, 4
subiu $a0, $a0, 1
sw $a0, 0($sp)      # store n-1   
subiu $sp, $sp, 12  # remaining padding for args 2-4

jal FIB     # call fib(n-1)

addu $t0, $v0, $zero   # store returned value from fib(n-1)

addiu $sp, $sp, 12      # Stack pointer to $a0
lw $a0, 0($sp)          # $get $a0 back

addiu $sp, $sp, 4       # Stack pointer to $ra
lw $ra, 0($sp)          # get $ra back

addiu $sp, $sp, 4       # Reset $sp to where it started (kept padding for fib(n-1))
sw $t0, 0($sp)          #store fib(n-1)

# store return address
subiu $sp, $sp, 8
sw $ra, 0($sp)      

# build args in stack
subiu $sp, $sp, 4
subiu $a0, $a0, 1
sw $a0, 0($sp)      # store n-2
subiu $sp, $sp, 12  # remaining padding for args 2-4

jal FIB     # call fib(n-2)

addu $t1, $v0, $zero   # store returned value from fib(n-2)
addiu $sp, $sp, 16      # Stack pointer to $ra

lw $ra, 0($sp)          # get $ra back
addiu $sp, $sp, 8       # Stack pointer to fib(n-1)

lw $t0, 0($sp)          # load back fib(n-1) values
addiu $sp, $sp, 4       # reset stack pointer

add $v0, $t0, $t1
jr $ra

RET_N:
addu $v0, $a0, $zero
jr $ra 


MAIN: 
li $v0, 4
la $a0, prompt
syscall

# User input N into $v0
li $v0, 5
syscall 

addu $s0, $v0, $zero    # Store n for output later 
addu $a0, $v0, $zero    # Pass n to fib call 

jal FIB                 # Call fib(n)

addu $s1, $v0, $zero    # Store Return of fib(n)

PRINT:
li $v0, 4
la $a0, ans1
syscall

li $v0, 1
addu $a0, $s0, $zero
syscall

li $v0, 4
la $a0, ans2
syscall

li $v0, 1
addu $a0, $s1, $zero
syscall

EXIT:


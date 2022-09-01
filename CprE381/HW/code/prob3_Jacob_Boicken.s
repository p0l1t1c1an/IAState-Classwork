.data
str1: .asciiz "Please enter an index (0-9):\n"
.align 2
vals: .word 25 1 4 10 381 42 100 60 0 12
.text
.globl main

main:
# Start program
inputs:
# Request some user input:
li $v0, 4
la $a0, str1
syscall
# Read some user input:
li $v0, 5
syscall

# Index1 is in $s2
sll  $s0, $v0, 2

# Request some user input:
li $v0, 4
la $a0, str1
syscall
# Read some user input:
li $v0, 5
syscall

# Index2 is in $s1
sll $s1, $v0, 2

# Request some user input:
li $v0, 4
la $a0, str1
syscall
# Read some user input:
li $v0, 5
syscall

# Index3 is in $s2
sll $s2, $v0, 2

# Address of vals in $s3
la $s3, vals

#Update Indexes to memory addresses
addu $s0, $s0, $s3
addu $s1, $s1, $s3
addu $s2, $s2, $s3

# Load values from first two addresses
lw $s0, 0($s0)
lw $s1, 0($s1)

# Average loaded values
add $s0, $s0, $s1
srl $s0, $s0, 1

# Store average in third address
sw $s0, 0($s2) 


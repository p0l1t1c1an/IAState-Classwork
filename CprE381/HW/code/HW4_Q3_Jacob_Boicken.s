
#
# 	for(i=0; i<a; i++)
#		for(j=0; j<b; j++)
#			y[8*j] = i+j
#
#	a = 10 b = 1
#	i = j = 0
#
#


.data

Array: .space 40 # 10 word array 

.text 

main:

# Initialization
ori $s0, $0, 10
ori $s1, $0, 1

la $s2, Array

ori $t1, $0, 0
ori $t2, $0, 0

# The code from the Exam (basically)
addi $t0, $0, 0
j Test1

Loop1: 
addi $t1, $0, 0
beq $0, $0, Test2

Loop2:
add $t3, $t1, $t0
sll $t2, $t1, 5
add $t2, $t2, $s2
sw $t3, 0($t2)

addi $t1, $t1, 1

Test2:
slt $t2, $t1, $s1
bne $t2, $0, Loop2

addi $t0, $t0, 1

Test1:
slt $t2, $t0, $s0
bne $t2, $0, Loop1


# Exit
exit:



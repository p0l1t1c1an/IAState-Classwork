
.data
text1: .asciiz "Don't\0\0\0\0"      # Size 10 array with Don't
text2: .asciiz "Stop\0\0\0\0\0"     # Size 10 array with Stop
text3: .asciiz "Believing'"         # Size 10 array with Believein'
text4: .asciiz "Hold On"            # Size 8 array with Hold On
empty: .asciiz ""
newline: .asciiz "\n"
plus: .asciiz " + "
equal: .asciiz " = "

.text
.globl MAIN

j MAIN # Goto Main at bottom

######## PART B: Implement STRCAT ########
##########################################

# char *strcat( char* dst, const char* src);

# dst is $a0 and src is $a1
# return on $v0

STRCAT: 
add $t0, $a1, $0        # char *c = src

# Until *dst is 0 / null terminator
NULL:
lb $t1, 0($a0)
beq $t1, $0, CONCAT     # if null term go to concat
addi $a0, $a0, 1        # Add one (byte) to go to next char  
j NULL

# while(!(*dst++ = *c++))
CONCAT:
lb $t1, 0($t0)   
beq $t1, $0, EXIT_STRCAT    # if *c is null term exit
sb $t1, 0($a0)              # store byte *c into dst
addi $t0, $t0, 1            # dst++
addi $a0, $a0, 1            # c++
j CONCAT

EXIT_STRCAT:
sb $0, 0($a0)               # *dst = 0, last index set null term
add $v0, $a0, 0             # return address of dst

jr $ra

#### Print String in $a0
PRINT_STRING:
li $v0, 4       # arguement is in $a0
syscall         # prints string at $a0 
jr $ra

######## PART C: Using STRCAT ########
######################################

MAIN: 

#### First Test ####
# concat text2 to text1, both are buffered with empty space
# Don't + Stop 
# Expected Output is Don'tStop

la $a0, text1
jal PRINT_STRING

la $a0, text1 
la $a1, text2
jal STRCAT

la $a0, plus
jal PRINT_STRING

la $a0, text2
jal PRINT_STRING

la $a0, equal 
jal PRINT_STRING

la $a0, text1 
jal PRINT_STRING

la $a0, newline 
jal PRINT_STRING

#### Second Test ####
# concat empty to text2, text2 is buffered
# Stop + ""
# Expected Output is Stop

la $a0, text2
jal PRINT_STRING

la $a0, text2 
la $a1, empty
jal STRCAT

la $a0, plus
jal PRINT_STRING

la $a0, empty
jal PRINT_STRING

la $a0, equal 
jal PRINT_STRING

la $a0, text2 
jal PRINT_STRING

la $a0, newline 
jal PRINT_STRING

#### Third Test ####
# concat text4 to text3, neither are buffered  
# Believin' + Hold On
# Expected Output is Believin'Hold On
# However, the buffer overflows so should be checked in MARS
# As well, if text4 is placed directly after text3, 
# then "Believin' + old On = Believin' Hold On" 
# will be printed showing the overflow

la $a0, text3
jal PRINT_STRING

la $a0, text3 
la $a1, text4
jal STRCAT

la $a0, plus
jal PRINT_STRING

la $a0, text4
jal PRINT_STRING

la $a0, equal 
jal PRINT_STRING

la $a0, text3 
jal PRINT_STRING

la $a0, newline 
jal PRINT_STRING




# Finishing AES

### Nonlinear
- Substitutions are nonlinear
- What is nonlinear 
    - linear => y = mx + b
    - f(x) = m1 * x + b1
    - g(x) = m2 * x + b2
    - L(x) = f(g(x)) 
        - Still linear
- Doing multiple linear doesn't add information
    - Additive turn to one action

### Math
- Binary Polynomials 
    - Treating coefficients as xor
    - More or less odd is 1 and even is 0
- x^2 xor 1 == b"101"


- Work in modulus of polynomial 
    - Look this up more


- Logarithms
    - log(x xor 1) = 1
    - log((x xor 1)^2) = 2
    - log((x xor 1)^3) = 3
    - log((x xor 1)^4) = 4
    - continued until 255


- Multiply
    - Make log table
    - add log(a) + log(b) == x
    - log^-1(x) == a * b


### Sbox
- g(a) = a^-1
    - nonlinear
- f(a) is linear algebra matrix multiplication 
    - Spreads out bits in output
- sbox = f(g(a))
    - 256 byte look up table


### Mix Columns
- Column (a0, a1, a2, a3) (upward)
- b(x) = c(x) * a(x) mod x^4+1 
- Becomes simple matrix math
    - Has inverse so undo able


### What does it all mean!
- Confuusion in sboxes and xor with keys
- Diffusion with shifts rows and mix columns
- Done over 10 times



# DES Quickly


### Fiestal cipher
- Break left and right
- Right goes through irreversable function with round key
- Xor output with left
- Xored left and out become new right
- Right becomes new left
- Repeat for rounds
- Only left half getting encrypted





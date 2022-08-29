
### Information Theory
- Cluad Shannon - 30s - 40s
- What does it mean to send info over a channel
    - A lot of noise that blends info
- 9/10 error on gigabit is worse than clean 100Mb
    -  Need to resend
- Info is what gets through that is right

### Math (OH NO) 
- Events: some set of outcomes - X -> { x1, x2, x3 .. xn }
- Prob function (pdf) 
    - 0 <= p(xi) <= 1 
    - Sum p(xi) = 1
    - How does a die look to be a fair die
        - Roll it a bunch
        - Uniform distribution
            - Flip a coin - P(H) = P(T) = 1/2
        - Sum of dice, isn't uniform

### Passwords
- Not uniform
- Some more common that others
- Always guessable
- Generate code in alphabetical order
    - Or by most common
    - John the ripper - progressive mode
        - Three characters together that are what people put together
- People do bare minimum of requirements of complexity
    - P@ssw0rd

### Entropy in Bits
- 1 outcome of heads or tails == 1 bit
- How much info in a password
- Uncertainty in set of events 
    - X = { x1, ... xn } 
    - p(xi) ...
- Entropy
    - H(X) = Sum( p(xi) * log2(1/p(xi)) )
    - Also, - Sum( p(xi) * log2(p(xi)) )
    - Capital X for the coin not the events
    - if p(xi) == 0
        - then use limit making that value a 0 in entropy
        - ignore it
    - Head and tails
        - 1/2 * log2(1/(1/2) + 1/2 * log2(1/(1/2)
        - 2 * (1/2 log2(2)) 
        - 2 * 1/2 
        - 1 bit of entropy 
    - Heads, tails, and sides - X = {H, T, S}
        - P(S) = s 
        - P(H) = P(T) = (1 - s)/2
        - H(X) = 2* ((1-s)/2 * log2(2/(1-s)) + s * log2(1/s)
        - (1-s) * log2(2/(1-s)) + s * log2(1/s)
        - s == 0 -> log2(2) = 1bit
        - s = 1/1024
            - 1023/1024 log2(2/(1023/1024)) + 1/1024 * log2(1024)
            - 1023/1024 * 1 - ~0 + 10/1024
            - ~.99 + 10/1024
            - Increased entropy to more than 1
            - More information in each flip
    - Pin numbers for cards
        - X = { 0000, ... 9999 }
        - B: Bank chooses uniformly random
        - p(xi) = 1/10000 = 1/10^4
        - H(B)
            - Sum: 0 -> 9999
                - P(xi) * log2(1/p(xi)
                - 10000 * (1/10000 log2(1/(1/10000))
                - log2(10000)
        - Uniform dist of n elements
            - H(X) = log2(n)
    - Byte 
        - 0 to 255 - 256 states
        - Amount of information
        - log2(256) = 8
        - 8 bits
    - AES Crypto key
        - 128 bits 
        - Each bit adds up entropy 
        - Because Independency



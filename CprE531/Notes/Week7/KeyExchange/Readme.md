
### Key Exchange 
- Most influencial 
    - Diffie Hellman

### Hard Problems
- Discrete Log Problem
    - Given a g and a prime number, p
    - Given $g^x$ mod p, find x
    - p is prime
    - g is generator (mod p)
- $g^0$ = 1
- $g^1$ = g mod p
- $g^2$ = $g^2$ mod p
- until $g^{p-1}$ = 1
- Visits every number in the range
- p is absurbly large


### Forward Problem
- Given g, x, p
    - computer $g^x$ mod p
- $g^0$ = 1
- $g^1$ = g mod p
- $g^2$ = g * g mod p
- $g^4$ = $g^2 * g^2$ mod p
- $g^8$ = $g^4 * g^4$ mod p
 
- $g^1025$ = $g^1024 * g$ mod p
- Only need to take the binary of the number and mulitply what maps to the array created
- $g^60$
    - 60 == 0111000

### DH KX
- Alice and Bob want to agree on a key
- share publicly a g and p
- Alice chooses $r_a$ mod p
    - Cryptographicly random
    - Sends A, $g^{r_a}$ mod p
    - Can't get $r_a$ from it
- Bob replies and chooses own $r_b$
    - Sends B, $g^{r_b}$ mod p
- Alice
    - $K_a = (g^{r_b} mod p)^{r_a} mod p$
- Bob
    - $K_b = (g^{r_a} mod p)^{r_b} mod p$
- $K_a = K_b$
- Works as if mods don't exist forward
    - Prevent backwards learning of the key


### Zero authentication DH
- Need to use with authentication method with DH
- Secure against eavesdroppers
    - Eve
- Mallory 
    - Operates inbetween and intercepts message
    - Acts as Alice to Bob and Bob to alice 
    - Using own $r_m$
    - Generates key for alice and mallory and for bob and mallory
    - Mallory decrypts message from alice
    - Reencrypt for bob and vice versa
    - Can read and control all messages
    

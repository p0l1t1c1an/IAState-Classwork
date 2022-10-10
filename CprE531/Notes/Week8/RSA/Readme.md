
### Hard problem
- Trapdoor
- n = pq
- find p or q 
    - Can get n

### Key Generation
- pub == (e,n)
- private == (d)

- Choose two cryptographicly random primes p,q
- Compute modulus n = pq
- Choose e (relatively prime to n)
    - generally now 65537
    - 2 binary ones (2^16 + 1)

### Private Key Generation
- compute phi(n) == (p-1) * (q-1)
    - Euler's totient function
    - Difficult if you only know n 
    - Number of numbers relatively prime to n
    - n = 15 (p = 3, q = 5)
    - phi(n) = 2 * 4 = 8
        - 1, 2, 4. 7, 8, 11, 13, 14
- find d (private key)
    - find d such that
    - e * d == 1 mod phi(n)
    - (e * d) mod phi(n) == 1
- erase p, q, phi(n)

### Encryption and Decryption
- 0 < m <= n
- Encrypt
    - c = $m^e$ mod n
- Decrypt
    - m = $c^d$ mod n

### Why does it work
- ${m^e mod n}^d$ mod n
- $m^{ed}$ mod n
    - ed = 1 mod phi(n)
    - ed = K * phi(n) + 1
- $m^{k * phi(n) + 1}$ mod n
- $m^{k * phi(n)}$ * m mod n
- $m^{phi(n)}^k$ * m mod n
    - if m and n are relatively prime then
        - we get m 
    - Otherwise (not relatively prime)
        - m = $m^{b*p}$ 
        - Still works out

### Small numbers for m is bad
- Create lookup table for short messages
    - Messages are turned to numbers
- Predictable messages are bad as well
    - need entropy in message
    - Few possiblities for what m is 
    - create look up table again
    - plaintext attack
- padding to the rescue


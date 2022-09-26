

### Continue One time pad
- ci = (pi + ki) mod |alphabet| 
- to binary
- ci = (pi + ki) mod 2
- which is just XOR
- C = P xor k and C xor k = P

- What is true for key to be secure
    - Random
    - only ever used once

- Need to be able to send just as much key as data
    - If we can securely do that why not use it for data


### Repeating
- M1 xor K == C1
- M2 xor K == C2
- Eavesdropper has both messages
- C1 xor C2
    - M1 xor K xor M2 xor K
    - M1 xor M2
- Learn where they are different and same
    - 0 is same 
    - 1 is different
- Really easy to get plaintext if one of them has header

### Detection of Reuse
- M1 xor M2 / What does statistical pattern look like?
- Email, most common characters would be spaces
    - most likely to match spaces
    - or Xor of e and space

### Example why OTP is perfect
- C = 101 
- M = 000 to 111 
- K = 000 to 111 
- Every key gets has a way to get C 
- Bruteforce goes through everything
- Learning any bit of the key doesn't help with others


### Stream Cipher
- Generates a long seq of keys bits from shorter key
- Block takes fixed key, IV, and gets out bits
- Looks like psuedo random number generator
    - Cryptographic psuedo random number generators
    - Looks like actual CRNG 
    - Could get enough of stream out to figure out the sequence
    - Only randomness is from fixed key

### Alice and Bob
- A --- IV, CPRNG(IV, K) xor M ---> B
    - Bob decrypt by xoring recieved C to CPRNG(IV, K)

- Why do we have the IV 
    - Never reuse the IV with the same K 
    - Then, it is the encryption done multiple times
    - Allows reuse of the same shared K

### Block Cipher 
- Takes in plaintext block / 128 bits commonly
- Takes key of fixed size (128 to 256 bits)
- Outputs block of ciphertext fixed (128 bits or same plaintext)


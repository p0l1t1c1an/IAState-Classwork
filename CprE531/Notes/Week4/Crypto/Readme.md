
# Cryptography

### Cryptosystem
- I/O
- Plaintext Alphabet
    - Characters
    - Bits
    - Bytes
    - AES is 16 bytes (128 bits)
- Ciphertext alphabet
    - Generally same thing as plaintext
- Key Set
    - Possible keys
- Encryption function
    - f(M, k) -> C
- Decryption 
    - g(C, k) -> M


### Simple Cipher example
- P == { A - Z } (alphabet)
- C == P
- k == {0 - 25 }
- E(M, k) = (M + k) mod 26 
- "TOM" -> "WRP"
- D(C, K) = (C - k) mod 26 
- This is a ceasar cipher

### Modulus
- Mod n 
- Is actually just 0, 1, 2, ... n-1
- Remainder does not work going backworks 
    - Negative does not wrap (%)

### Cryptographic Analysis
- Frequency Analysis
    - Most common would match in simple example
    - E is most
    - ETAOINS
    - JXQZ not each a quarter of a percent
- This useful same encryption is repeated

### Vignere
- Repeated ceasar ciphers
- M == "Attack At Dawn"
- k == 4, 1, 25
- Repeat k over M to get C
- C == "Eusedj Eu Cexm"
- Different encryption for keys so flatter character distribution

- E(M, k) => for each Mi in M { (Mi + k(i mod |k|)) mod 26 }


 









### AES History

- IBM lucifer 
- NSA made DES from lucifer
- Shortened key length
- Closed design kinda untrusted but became standard
- Thought to be weak
    - So lets do three times 3des/ triple des
    - $E_{k1}(E_{k2}(E_{k3}(M)))$
    - Slow


- Make something better
- Original Rijndael
- Voted / Ranked and Rijndael named into AES


### What Crypto Does
- Confusion
    - Hide message and ciphertext
- Diffusion
    - Spread out the message (effects of encryption)
- Only secret is the key
- Don't write it yourself

### AES working
- Load data to state matrix
    - 4 by 4
- Xor each input byte by corresponding byte of encryption key
- Later rounds expand key from initial one 
    - Gets later round keys (AES needs 10 plus init)
    - AES takes last column and rotates top to bottom
    - Each byte goes through S-box (substitution box/look up table)
    - Xor colum with round constant
    - Xor with first column of previous round key
    - Next column is made by xoring the column from previous round key with the generated column
- Rounds
    - Sbox
    - Shift rows 
    - Mix columns
    - Xor round key to matrix
- Confusion 
    - Sbox's do this / are nonlinear
- Diffusion 
    - Shift rows
        - Real simple
    - Mix colums
        - Multiply each colums bits together (or something)
- Secrecy 
    - Xor the round key

### AES Modes
- AES is just the encryption block in flow diagram
- Electric Codebook Mode 
    - Bad 
    - Reuses same key on each block of input
    - All output the same with same input
- Cipher block chaining
    - IV xor input1
    - That goes to AES
    - The output of first AES xors with input2
    - Repeat for next AES and inputs
- Counter mode 
    - Turns to stream cipher
    - Take IV and counter put through block cipher
    - Xor with plaintext 
    - Incrememnt counter by one and repeat
    

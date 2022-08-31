
### Practical Entropy Examples 
- DES Keys
- Kerberos v4
- Idea 
    - System has inputs and outputs
    - Run I/O through model to get if running normally
    - Some level uncertainty == Entropy 
    - Intrusion detection
    - Compression
        - More bits of certainty means less compression

### DES Keys
- 64 bit block + Key (64 or 56 bits) -> 64 bit enc block
- Naively a 64 bit key should have 64 bits of entropy
- Uniform distribution - 64 bits that are 1 or 0 == 64 bits entropy
- Or 2^64 possibilities -> log2(2^64) -> 64 bits entropy
- Most secure possibility -> if one more likely guess that first

- DES Keys has a parity bit in each byte
- H(DES Keys) = 56 bits
- 8th bit is forced by values of other bits
- 2^56 unique keys
- Equally likely then 56 bits entropy / information


### Kerberos v4
- Generates session keys
    - new random DES key at every new session
- Seed generation
    - Time seconds (basically known)
    - Process ID (2^12)
    - Key count (Very little importance)
    - micro seconds (2^20)
    - Hostid (known)
    - Seed (32 bits) but 12 bits known as they xor
- Seed then is the seed in rand
    - Runs rand twice to get DES key
    - About only a million possible keys
    - 3 seconds Brute force
    - Concate does nothing so only 20 bits of information


### Psuedo Random Numbers Generators (PRNG)
- Given a seed, generate maximum length sequence of numbers in a range
- Doesn't repeat until all are reached and then repeats once back to starting number
- Only uncertainty is seed

### Cryptographic Random Source
- Given a sequence of bits - b0,b1 .. bn-1
    - then guessing bn is still maximally hard/entropy
    - H(bn) = 1/2 even when knowing all that came before it



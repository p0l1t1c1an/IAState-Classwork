
### Cryptographic Random Numbers
- Given some n bits, still have max uncertainty in next bit
- Can't be done w/ deterministic procedure
- State (n bits of storage)
    - seed inits state
    - call rand to get new state
    - repeats at some point
    - 2^n states and n bits of entropy

### Entropy Sources
- Use environment to get randomness
    - Sources
        - Temperature
            - Need to be high precision to have high entropy
        - CPU util
            - Cores used 
        - Scheduling Information 
            - Processes
        - Peripherals
            - Keyboards
            - Mice
            - Disks
            - Timing
            - *Network I/O*
                - timing
- Attacker shouldn't be able to adjust to these
- SGI
    - Lava Lamps
    - Use image frame
    - Hash the image or information from it

### Actually Done
- CPU has random number source
- Idea
    - 2 1K resisters with meter at junction
    - Vary resistence by temperature
    - Influenced by noise
    - Xor many of them together
    - Linus torvald not trust

- OS mixed CPU and Environmental Sources


### /dev/random vs /dev/urandom
- /dev/random
    - slower
    - blocks
    - tries to measure how much entropy collected
    - needs 8 bytes of entropy to write out infromation
    - needs enough entropy to write

- /dev/urandom
    - faster
    - non-blocking
    - harvests sources + new data and mixes them to make entropy pool
    - /dev/random / takes from pool and waits if out of entropy
    - /dev/urandom / gets key and encrypts sequence of numbers 
        - gets new key at some point




# Password Authentication
- What do we store

### Plaintext
- (userid, passwd) is stored
- Reading passwd file reveals everyones password
- Compromise of file is compromise of accounts/subjects

### OG Unix Password
- Store a "hash" of password
- No standard hash / not discussed 
- Called crypt
- Given P,
    - Trunc to 8 chars (64 bits)
    - Drop high bit from each byte to 7 bytes (56 bits)
    - Turning P to key
- Stores (userid, DES("0", key))i
- Bad as hash 
    - Only 56 bit input 
- Good as hash
    - 1 way operation
    - Knowing the plaintext and ciphertext
        - still hard to find P/key
- How many passwords
    - Only 8 inputs of 7 bits
    - only printable assci can be entered
    - A-Z,a-z,0-9,20ish special chars
        - 82
    - 1 + 82 + 82^2 ... + 82^8
    - (1 - $c^{n+1}$) / (1 - c)
    - ${82}^{9}$ - 1 / 81
    - Basically, 82^8 (slightly above)
    - Come from adding a char
- Stored /etc/passwd
    - readable by all users

### Modern Password Storage
- Use Cryptographically secure hash function on password
- (userid, h(P))
- (Also have salts with each password)

### Attacks (to Password Systems)
- Online Attack
    - guess passowrd, brute force
    - counters 
        - require long passwords
        - lockout after n tries
            - DOS attack
        - slow down login attempts
- Offline Attacks
    - Have some password file stolen from the file
    - Find some password that hashes to hash on file
    - Could brute force search
        - List of common passwords
        - Words in dictionary
        - Generate other passwords
            - Modify common passwords
            - Append numbers common
            - ...
    - 



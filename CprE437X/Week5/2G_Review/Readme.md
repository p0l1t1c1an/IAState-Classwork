
# 2G Security


### Problems on GSM 
- Cryptanalysis attacks against A3/A5/A8/COMP‐128 algorithm
- Over‐the‐air interception using fake BTS
- Only air interface transmission is encrypted
- Ciphering key (Kc) used for encryption is only 54 bits long
- No messages authentication and integrity protection

### Jamming
- DoS physical layer 
- Add interference
- SINR: Signal to Interference and Noise Ratio
    - Decreases this value
- Cause decoding failure/ packet loss

### Fake Base Station
- Also called IMSI‐catchers 
- used legaly by law enforcement and intelligence agencies

### Attacks on Wired Part of GSM
- Only encrypted between MS and BTS
- Inside the network traffic is not encrypted
    - Could read modify data on the fly / wire tapping
- If attacker accesses HLR
    - can get the $K_i$ for all subscribers


### Get $K_i$ from subscribers
- Manipulate subs to provide responses to attacker requests
    - Crypt analysis
- MS is required to respond to every challenge by GSM network 
    - SIm card is victm
    - Need fake BS

### Design Issues
- No integrity
- No mutual Auth
- Replay of triplets
- Issues of stream cipher






# Botnet Investigation

### Botnet
- Series of zombie machines 
- Denial of service
- Many can only have so many established connections
- Load balancers to keep of server
- Attacker controlling machines to protect
- Heavily changing 
- Bypass blacklisting
- Or could just be an Adclicker


- Uses
    - DDOS
    - Spam
    - Phishing/ ID theft
    - Keylogging
    - Steal Registration keys
    - Click fraud
    - Whatever for the money
        - Make money for criminals 


### Protection from Spamming
- Cpre430 greylist concept
- Set whitelist of trusted machines
- Drop first message from a server 
    - Add to allowlist if they resend the message
- Spam machines generally send alot of messages constantly
- Won't likely reply (in meaningful time)


### Malware evasion
- Dynamically linked language
- Downloads / sets up libraries when run
- Doesn't contain malware in binary
- Malware in library download/setup when ran

### Control 
- Master generally through stepping stones until sending commands to botnets
- Can set up peer to peer network 
    - Don't need a specific machine to send commands
    - Just sets up P2P with zombies from setting stone
    - Protects them from server getting shutdown by provider/govt


### Questions
- Determine other members of the same botnet
    - On the same network
- Can be detected if they are querying blacklist
    - DNS Blacklist
    - Need to always see DNS activity




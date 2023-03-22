

# WPA3


### Overview
- WPA2 update
- Same architechure
- No new hardware

### Features
- SAE / Simultaneous Auth of Equals
    - Password bases authenticated key exchange
        - Created PMK
        - Zero knowledge proof
        - Uses ECDH
    - Attacker cannot find shared passwords or PMK
    - Resistant to dictionary attacks
    - Provides forward secrecy
        - Compromise of previous PMK doesn't provide advantage to find password or other PMK

### Enterprise
- SAE is was added to improve personal mode of WPA3 and PSK usage
- Enterprise mostly uses TLS
- GCM encryption
    - 256-bit AES, 384-bit HMAC-SHA2



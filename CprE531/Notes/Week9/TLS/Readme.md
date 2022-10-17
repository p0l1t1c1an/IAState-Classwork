
# Transport Layer Security / Secure Sockets Layer

### Goals and History
- End to End encryption
- Authentication 
    - 1 way 
        - Check the service
    - 2 way
        - Both client and service check
- Sits on top of TCP

### TLS Record Protocol
- Compress, HMAC, and encrypt null initially
- Each side has an hmac key and bulk enc key for sending
- Key exchange generates all 4 of those keys


- Record protocol
    - takes mess from apps 
    - fragments data in blocks
    - optional compress
    - applies a MAC (message auth code)
        - integrity and auth
    - encrypts
    - transmits the result
    - Append record header
    - Reverse operations upon recieving

### Message Authentication Code
- HMAC / Hash-based
    - keyed hash
    - HMAC(K,m) === H((K xor opad) || H((K xor ipad) || m))
        - added to defeat certain attacks against just hashing message and key
- Combined Encryption + Auth Modes
    - GCM
    - Turn mac and enc into one step 
        - compute hash as we are going 
        - encrypt with first counter
    - AEAD
        
### Browser Certs
- Client has set of trusted Certificate authorities
    - Format: Issuer << Subject >>
- Trust CA certs becuase they are included in the browser

### Hellos
- 1 Client 
    - Version, Random (unix time, random bytes), Session ID = 0
    - Ok Sipher Suite
    - Ok compression list
    - Extensions
- 2 Server
    - Version, Random, session id is chosen
    - Cipher suite chose(dHE_RSE, AES128CBC, SHA256)
    - Compression 
    - Extension

### Server Cert
- 2b Server
    - CA1 <<Server>>
    - CA2 <<CA1>>
    - CA3 <<CA2>>
- 3 Client 
    - verifies cerificates to find trusted trust anchor
    - Sign must verify, times are valid, type of cert ...
    - Client learns servers public key

### Server Key Exchange
- 2c KX
    - Server DH params gp
    - g^X mod p
    - Sign({cR, sR, Server DH params}, d_S)
- Verifies Server sign aborts if bad
- hashes cR, sR, and DH params
- Verify nonces
- 2d ClientDHParams
    - g^Y mod P
    - Y is random

### Premaster Secret
- PMS = g^X^Y mod p
- Generate master secret
- Pseudo rand function = fnacy hash
    - PMS, master secret, cR, sR, first 48 bytes
    - PMS is secret
    - master secret is label
    - cR is seed
    -p_hash(secret, label || seed)
        - which is 
        - HMAC_hash(secret, A(1) || seed) ||
        - HMAC_hash(secret, A(2) || seed) ||
        - ...
    - A(0) == seed
    - A(i) hmac_hash(secre, A(i-1))


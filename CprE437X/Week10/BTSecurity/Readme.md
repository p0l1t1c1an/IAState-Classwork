
# Bluetooth Security and Attacks

### Security Features
- Exchange Business Cards
    - Needs a secret key
- A security manager controls access to services and devices
    - Security mode 2 does not provide any security until a channel has been established
- Key Generation from PIN
    - PIN: 1‐16 bytes. 
    - PINs are fixed and may be permanently stored. 
    - Many users use the four‐digit 0000


#### Bluetooth Initialization Procedure (Pairing)
- Creation of an initialization key (ki)
    - Pin and its length affect this
- Creation of a link key Authentication (ka)


#### Challenge‐Response Based
- Claimant: intends to prove its identity, to be verified
- Verifier: validating the identity of another device
- Use challenge‐response to verify 
    - whether the claimant knows the secret (link key) or not. 
    - If fail, the claimant must wait for an interval to try a new attempt.
    - The waiting time is increased exponentially to defend the “try‐and‐error” authentication attack
- Mutual authentication is supported
    - Challenge (128‐bit)
    - Response (32‐bit)
    - 48‐bit device address

#### Encryption
- ACO (Authenticated Cipher Offset) is 96‐bit, generated during the authentication procedure
    - ACO and the link key are never transmitted
- Encryption key Kc is generated from the current link key
    - Kc is 8‐bit to 128‐bit, negotiable between the master and the slave
    - Master suggests a key size
    - Set the “minimum acceptable” key size parameter
        - prevent a malicious user from driving the key size down to the minimum of 8 bits
- The key stream is different for different packet since **slot number** is different



#### Encryption Modes
- Encryption Mode 1: No encryption is performed on any traffic
- Encryption Mode 2: Broadcast traffic goes unprotected
    - while unicast traffic is protected by the unique key
- Encryption Mode 3: All traffic is encrypted


#### Basic Steps
- Step 1: User input (initialization or pairing)
    - Two devices need a common pin (1‐16 bytes)
- Step 2: Authentication key (128‐bit link key) generation
    - Possibly permanent, generated based on the PIN, device address, random numbers, etc.
- Step 3: Encryption key (128 bits, store temporarily)
- Step 4: key stream generation for xor‐ing the payload


#### Short Comings / Oddities
- The security of the whole system relies on the PIN which may be too short
    - Users intend to use 4‐digit short PINs, or even a null PIN
- Utilized new cryptographic primitives, such as (E0,E1,E20,E22) algorithms


### Bluetooth Cryptography
- The E0 algorithm is designed specifically for Bluetooth
- E0 has gone through many security analyses. 
    - When used in Bluetooth mode, the security of E0 is decreased from 128‐bit to 84‐bit;
- A Bluetooth device resets the E0 key every 240 output bits, 
    - severely limiting the amount of known key stream that may be available to the cryptanalyst.


#### Crypto Attacks
- Focus on short key attacks
- Can guess content of smallest three Linear feedback shift registers
- Takes 128 bits of known plaintext and ciphertext 
    - 27 operations 
    - Recovers key crazy fast
- E0 is insecure

- Problems with E0
    - PIN (if too short)
- Problems with E1
    - Location privacy
    - Denial of service attacks

- Can guess small pin size that is basis for all crypto
- Some use default pin
- Most people use easy pins

#### E1 Function
- Safer+
    - Block cipher
- Slow cipher

### Additional Issues
- Short range was a countermeasure to force the attackers to be in close proximity
    - now range extenders can be easily built
- Attackers grow when information is more attractive


### List of Attacks
- Radio jamming attacks
- Buffer overflow attacks
- Blocking of other devices
- Battery exhaustion (e.g., sleep deprivation torture attack)
- Bluesnarfing
- Bluejacking
- Bluetapping



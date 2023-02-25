
# Aithentication


### Digital ID
- Identifier for the real thing
- Real thing may be person
    - Or could be a device 
    - Or Bot or ...
- ID could be SSN, email, MAC, key/signature

### Assigning/Proving ID
- Person or thing is given ID to them
- Person or thing needs to provide that ID to prove it is them
- Proving would be authentication

### Authentication Overview
- validating the authenticity of something or someone 
- Authentication is the first step in an identity management system
- Proof on identity
- Four different types of authentication
    - User to host
        - Person proves the identity to computer resource
        - Most  prevalent
    - Host to Host
        - Work being done to strengthen this 
        - In past usually done by IP address
    - User to User
        - Contracts, secure email
        - Useful for online auctions
    - Host to User
        - Server authenticating to user

### User to Host
- UserID and Password 
    - username or email generally
- MFA
    - ARE, KNOW, and HAVE
    - ARE generally not online but local

### Authentication Systems 
- Password 
- Third party
- PKI

### Password
- Static
- OTP
- Stored in hash
- Social Engineering issues
- Old ways encrypted passwords instead of hash

### Unix password encryption
- 56 bit (old)
- Need to get password file to get password
- Password file of salts and hashes
- Used to be stored /etc/passwd 
    - Now separated by user info in passwd
    - /etc/shadow has password hashes
    - Only name:uid:gid is world readable
- If get passwd file
    - John the ripper


### Attacks to auth
- Guess passwords
- Identity theft
    - Create account as someone else
    - Like I could make a bank account if I have SSN
- Get people to give up access to accounts

### Trusted Third Party
- Internal
    - Kerberos
        - Manages passwords internall
- Hybrid
    - Okta
        - External Site
    - Final check is internal site
        - Not managing password we internally do
- External
    - Trust the site / host
    - Oath and Saml are also this
    - Site manages all password


### Host To Host
- Security Systems rely on this
    - Firewalls and IDS
- Other services
    - Remote backups
- Authentication
    - Bad
        - No auth
            - Implicit by IP/hostname/MAC
        - Password
    - Digitial Signature and PKI/Third party
        - Encrypts using cert/key


### User to User 
- Application level
- Could be digital signatures
- Thinking of matrix it is based off of key


### Host to User
- Digital Certs
- Digital Signatures
- Generally just public key 



# Cryptology


### Base Features
- Data protection
- Authentication
    - Data
    - Users
    - Not host to host
- Web communications
- Link level
    - SSH
    - Hardware  (VPN)
- User level
    - PGP
- Most implementations are transparent to user

### Issues
- Key Distribution and Protection
- DOS
- Access control
- Key selection / Generation

### Types of Crypto
- Symmetrical
- Asym or Public/Private Key

### Problems of Public
- Complexity
- Key Revocation
    - When one is compromised
- Third Party

### Utilities
- We rely on strong hash functions
    - Have many to one relation
    - But hard to find another input to match output

### Key Distribution
- Symmetric
    - Physically
    - Or Use old key to send new key
        - Both don't scale
    - Trusted Third Party 
- Public key
    - Public knowledge of pub key
    - PKI 


### PKI
- Distribute public key
- Bind public to owner of private key
- Need private to read messages
- Trust that private key is kept secret
- Global Key management 
    - bureaucracy and tech together


### Certificate Authority
- Trusted third party
- Issue and revoke keys
- Conceptually a huge database
- Revocation list or CRL
- Signs certificate so one can tell if it is altered

### Regristration Authority
- Vouches for users
- Certifies the validity of user
- Have to go through RA to get to CA
    - Verify information
    - Policy enforcement
    - Lack liabilty?
        - Have just messed verify users before
- Why RA, billing information and administration


### Certificate Types
- X.509
    - Standard certificate
- SSL
    - Secure socket layer certificate
- SET
    - Banking certificate
- Applications can create their own certificate



### Certificate Contents
– Name of user given private key
– Public Key
– Digital signature
    - hash of name and public key
    - Then encrypted with the private key of CA
– If one has public key of CA, then can verify that CA signed it
– If CA gets comprised, nothing can be trusted



### Root CA
- Root CA
    - Multiple CA’s underneath root, like DNS
    - Trust  relationship  in  hierarchy
- How do CA’s trust each other
    - Hierarchical  model
    - IPRA,  Internet  Policy  Registration  Authority


### IRPA
- May be different reasons for giving out keys
- Persona
    - Get certificate but information doesn’t link to you
- Residential
    - No association, just need ticket
- Organizational
    - Companies, governments having own  tickets


### Web of Trust
- Created by makers of PGP
- John trusts Joe
- Joe trusts Mary
- John asks Joe for Mary's certificate


### Cross Certification 
- Allows CA’s to build trust between each other
- Doesn’t depend on trust to upper CA’s


### X.509
- Close to X.500
    - LDAP is X.500
- X.509 defines a certificate
- Contains
    - Version
    - Serial  Number
    - Hash algorithm, certificates designed to be algorithm independent
    - CA that issued certificate
    - Not before
    - Not after
    - Subject
    - Public key
        - Not just the key but the algorithm and any parameters to make  algorithm  work
    - CA id
    - Subject id
        - Numbers used to uniquely identify
    - Extensions
    - Signatures
        - Algorithm,  parameters,  actual  encrypted  signature
    - Hash of the certificate encrypted with CA’s private key
- Information is not encrypted












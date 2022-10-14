

### Hash Functions
- Sha256 round
![sha256](sha256.png)

- Compression function

- Davies Meyer compression function
![davie](davies_meyers.png)

### Digital Signatures
- Bad Sign(M, d) = {M} encrypt with priv key
- Good sign (M, d) = H(M) encrypt with priv key
- Verify(E, m', e) = {E} decrypt e and compare with H(m')
    - E = signature
    - e = pub key
    - if match, m' is verify from sender and unchanged
        - Authenticity and integrity

### Basic Cert
- Subject
- Issuer
- Pubkey subject
- Valid time range
- Signature by Issuer private key


# More Authentication Methods

### SRP Protocol
- Secure Remote Password
- Now Know as a PAKE
    - Password Authenticated Key Exchange
- Protocol to let you:
    - Never Send password 
    - Use password to generate shared key
    - Authenticates that user knows the password
    - Protection from MitM
        - MitM couldn't get your passwd
        - Offline attack does not reveal password
        - Must learn by trying to auth 

![SetupMaths](./math.png)

### Variables (Math in Mod N)
- I = User id, p = user password
- N (large safe prime)
- g (generator mod N)
    - Visits every 1 to N in g^0 ... g^N-1 
- k = H(N, g)
- a,b - secret rand num
- A = g^a mod N
- b = (kv + g^b) mod N
- s = user salt
- v = g^(H(s,p)) mod N
- u = Random Screcmling parameter

### Comms (Math in Mod N)
- User sends I,A
- Server sends s, B
    - Don't want eavesdropper to learn v
    - Does one time pad
- Both calc u = H(A,B)
- x = H(s,p) (Salt and password which can be stored on server)
- S = (B-kg^x)^(a+ux) (client)
- S = (Av^u) ^b (server)
- S = $g^{b(a+ux)}$
- K=H(S)
    - Key shared
    - Key and S are the same
- IKE for IPSEC can do this PAKE
- OPAKE


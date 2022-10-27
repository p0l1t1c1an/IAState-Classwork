
# More IP Traceback


### Routers
- Only focus on routing packets to the correct destination
- SPIE inefficient and dont hav large memory / drive 
- Marking / no memory needed for the router 
- Store information in the packet

### Deterministic Packet Marking
- Marking each packet as it enters network
- Using 16 bit packet ID(IP address) and reserver 1 bit flag in header
- Mark remains unchanged for as long as the packet traverses network
- Packet is marked by interface closest to the source of the packet on the edge ingress router
- Interface makes a distinction between incoming and outgoing packets
- Incoming packets are marked; outgoing not;

- Appending identifier for router to know what routers this device goes through
    - IP address
    - Downside is the size of the packet growing
    - Can cause the packet to be fragmented

### Smaller ID for marking
- Not all need to record 
    - Every other allows us to see the router inbetween
- Could record randomly
    - Picks a random packs to attach ID
    - Will be missing packets that don't attach anyway
    - Not necessarily for tracking a single packeti
        - Attacks will commonly send many packets
    - Log the many packets together to build the path/s taken

### Probabilistic (Not From Lecture) 
- 

### Algebraic Packet Marking Assumptions
- Attackers able to send any packet
- Multiple at once
- Aware of traceback
- Send thousands of packets
- Routes between hosts in a general stable, but packets can be reordered or lost
- Routers can not do much per-packet computation
- Routers are not compromised, not all participate
- Difficult to change the marking algorithm used by routers
- Easy to change the reconstruction algorithm used by victims 

### Algebraic Schemes
- Reconstructiong polynomial in a prime field
- Constant size for any number of hops
- Basic ida of for any polynomial fx of degree d (num hops)
    - can recover fx given fx evaluated at d+1 unique points
- A1..An be 32bit ip addressof the routers on the path
    - fp(x) = $A_{1}{x}^{n-1} + A_{2}{x}^{n-2} .... A_{n-1}x + A_n$
    - evaluate fp(x) as the packet x travels along the path 
    - accumulating the result of the computation in a running total along the way
    - When enough packets from same path reach dest, then fp can be reconstructed by interpolation
- Full path encoded
    - Fill out matrix with enough (n) packets recieve
    - Full rank matrix
    - Left matrix is polynomials n * n size
    - Right is A values starting at A1 ... An in one column 
        - Matrix multiplication
    - Each packet would get different x values so we can fill out left matrix

### Other solutions
- Ingress filtering
    - Firewall doesn't allow illegit ip addresses
- Input debugging
    - Build attack signature when attack 
    - Blocking packets matching signature
- Control flooding
    - test links by flooding them with large bursts of traffix 
    - observe how this perturbs traffic from attacker


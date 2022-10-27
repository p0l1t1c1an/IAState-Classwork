
# Continuing Android Forensics

### Android GUI
- Gui hierarchy tree
- draw on canvas 
- Guitar geometric relocation

- Recover gui data structures over time
- worses over time


# IP Traceback
- Goal / Traceback and ID network attacker

### Problem
- Traceback
    - Trace the path of a datagram traverse thru the internet
    - Schemes
        - Hash based
        - Probabilistic marking schemes
        - Algebraic Packet marking schemes
- Attack attribution
    - Stepping stone attack attribution
        - The problem is to discover the real origin of attacker
        - Stepping stones are compromised host, web proxyies, anonymous comm services
    - DDOS
        - Discover master computer
        - Master controls zombies 
        - Zombies are stepping stones
- Can determine where someone is or attack is coming from
- Spoofed addresses
- Ingress filtering
    - Supress packets arriving form given network 
with source addresses that do not properly belong to that network
    - Transit networks are dependent upong 
their peers to preform the appropriate filtering

- No actual way to guarentee source IP


###  Deployable solutions
- What packets should we trace
- Maintain privacy of legit users
- Min cost
    - Router time spent for tracking over routing traffic
    - Storage for info

- Hash based
    - Log based trackback
    - Keep log of packets
- Marking
    - Add information to packet to traceback the source packets
    - Deterministitic, probablistic, algebraic


### Hash based
- Single Packet IP traceback
    - Easy to track when used for days to trace
    - Person made single packet traceback 
        - Only one packet
- Payload Attribution
    - Look at payload

### SPIE
- Assumptions
    - Packets can be addressed to more than one physical host
    - Duplicate packets may exist
        - TCP sliding window, NAK or timeout
        - Router may resend packet
            - Downstream is down so sent to different path
    - Routers may be subverted
    - Attackers are aware they are being traced
        - Packet logging is occuring
    - The routing behavior of network is unstable
        - Routes may be different for every packet
    - Packet size shouldn't grow as a result of tracing
    - End hosts may be resource-constrained
    - Traceback is an infrequent operation
        - Costly

- Goals
    - Source of a packet
        - Ingress point to the traceback enabled network
        - The actual host or network of origin
        - One or more compromised routers within the enabled network
    - Constructing an attack poin pathe, where path consists of each router traversed by packet on its houry from src
    - Packets transform
        - Encap
        - ICMP

- Packet Digestion
    - Look at IP Header
        - Look at static fields 
        - Not Type of service, TTL, checksum, and options
        - Fragmentation may occur
            - share ID value
        - Version, lengths, ID, Src and Dst address
    - Build ID for each packet and use bloom filter
        - computer k distinct packet digests for each packets using independent uniform hash functions 
        - uses n-bits result to 2^n sized bit array
        - Array is init to 0, and bits are set to 1 as packets are recieved
        - Hash is index in array
        - Each packet has k independent hashes

- Membership tests
    - Computer k hashes of packet in question and check bits to all be 1
    - If any zero is packet wasn't
    - If all 1s packet is likely to be 
        - Can be filled by other packets hashes
    - Can have false positives but not false negatives

- Path Contstruction
    - Send query to connected routers
    - They query if they did have the packet
    - On and on
    - Until the last device
    - Each router must be implementing system
    - ISP network should be 
        - ISP can look through to trackdown attacker

- Implemented in hardware 
    - Low latency

- False positives
    - At most, rate p/d 
    - Router neighbors (degree) is d, p is tuning
    - np/(1-p) 

- P = ${(1 - {(1 - 1/m)}^{kn})}^{k}$
    - P = $(1 - e^-kn/m)^k$

- Once in a while, archive bloom field and clear
- SPIE not implemented on every router
    - Only most critical base on topology



- Hierarchical Bloom Filter
    - Breaks down string into multiple fields 
    - Block base bloom filter





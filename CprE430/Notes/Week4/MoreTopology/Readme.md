
# Finishing up Taxonomy

### Authentication
- Often use authentication via network 
- Ports, ip, etc is used as form of authentication
- Zero trust is not doing this (I think)
- Four tuple 
    - src/dst ip and src/dst port
- Five Tuple
    - Add whether using UDP or TCP

### Addressing Auth
- Firewall world
- Only let these addresses in 
- Don't only rely on this
- Enterprise world uses this to limit access to only internal network
- Lateral movement idea


### Traffic Based Attacks
- 2 Categories
    - Data volume (flooding) 
    - Sniffing 


### Data volume
- To machine
- To app
- To network device
- Overwhelming devices
- Goal to shutdown (DoS)
    - Service
    - Network 
    - Machine

### Sniffing
- View packets
- Some layers won't be able to mitigate this (by design)
- Gain entry
- Learn network topology


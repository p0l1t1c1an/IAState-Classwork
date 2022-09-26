
### Wireless Separation
- Enterprise environment
- Wireless on separate network


# Network tools

### VLANs 
- Switch only lets certain traffic out by ports associated with MAC
- VLAN on top of this idea
- Traffic is only part of certain set of ports
- Grouping ports together into switch
- Need router of other device in place to move between VLANs
- Creates separated networks (physically?)
- Tag on ethernet switch
- Client is unaware of them

- Static 
    - Maps ports on switch to VLAN
- Dynamic
    - Maps MAC to VLAN
    - Could move devices between groups
    - Are authed in one and authed put in proper group

### Network Access Control
- Trusted devices
- Switch is device that allows connections
- User connects to policy engine
    - Policy reconfigures switch to set user to proper VLAN
        - Dynamic VLANs
        - Netreg
    - Initially limited set of connectivity


- Policy 
    - User ID agreeing (like hotel room agreement)
    - Machine ID checks that user matches
        - Has hardware key
    - Correct / Needed / Up-to-date software
        - Interesting: if not meeting requirements then allowed access only to get needed SW

### Packet Broker
- Sits out of band of network
- Collection and replication of netowrk data
- Idea could be some tap points with a common destination 


- Broker 
    - Many taps 
    - Left is many filters
        - Allow breaking out certain traffic of the taps
    - Middle is a traffic director 
        - Replicate and merge traffic 
    - Right is more filters
        - Filtering based on application level information 
        - Packet data / Port numbers
    - FPGA 
        - Hard to program
        - Could be easy to set up new connections
    - Could have one output to IDS/IPS
    - Can have inline devices
        - Decryption
    


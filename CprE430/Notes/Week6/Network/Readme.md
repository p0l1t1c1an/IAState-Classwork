
# Network Layer

### IP Fun stuff
- Has internal layers
- Different protocols internally to operate


### IP Addresses
- Ipv4 == 32 bits
- Split into networks
    - Networks get to the host

### Network Addresses
- N1 == 192.12.15.0
    - 256 addresses
    - Can't use all of them
    - Only .1 to .254 
        - .255 is broadcast
        - .0 is for the network protocols
            - thought .0 was network address

### IPv4 Classes
- A 
    - Large network size  
    - Few created
    - set first bit 0 
    - 7 bits for network
    - 24 bits rest for hosts
- B 
    - Middle network size
    - Set first bits 11
    - 14 bits for network
    - 16 bits for hosts
- C 
    - Small network size
    - Many created
    - Set first bits 110 
    - 21 bits for network
    - 8 bits for hosts
- D 
    - Multicasting
    - Private broadcasting space
    - Set first bits 1110 
- E 
    - Left over
    - Set first bits 11110


### Special Addresses
- 0.0.0.0
    - Never supposed to be destination address
    - Used when don't know IP as src
        - DHCP
- 0.0.HOST
    - Know host but not network
- 255.255.255.255
    - Broadcast to network
    - Never source 
- Net.255
    - Broadcast into a network
    - Not used / allow any longer
- 127.0.0.1
    - Loopback
    - Talk to self through IP stack
    - IPC and test IP stack
    - Shouldn't be accepted across the network


### Subnetting
- Breaking up address block

![subnet](./subnet.png)


### Netmask
- Don't know which part of address is network and which is host
- Netmask extracts network part of address
    - Logical bitwise and

- Class A: 255.0.0.0
- Other 255.255.128.0

### CIDR 
- Class A is /8 
- Number is the leading bits that are network address
- Maps to netmask and bitwise AND


### Routing
- Routing Table 
    - Dest 
        - Address where we are going
        - MyNetwork / CIDR
        - Default
    - Next
        - Device who will get us there
        - Me
        - Router
    - Interface
        - Interface there are on
        - Interface to MyNetwork
        - Interface to router

### Routing Process
- Given dest / say 129.186.5.15
- Look up in the table 
    - mynetwork / say 129.186.5.0/24 
    - default
- Example
    - Using mynetworks CIDR
        - We get matching network address
        - We need to handle routing of the packet
    - If they did not match it gets passed to router
    - Could have multiple networks and need to go through them
    - I think the smallest CIDR goes first


### ARP 
- Know IP is on network and want to send packet
- Don't know physical address
- Send broadcast message to asking who has IP and give the HW addr
- Responds back (not in broadcast)
- Also need to do this when sending out network but asking for routers HW addr
- Will go into attack points later



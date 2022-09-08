
### Layered Attacks
- Bad Packets could reach any layer to the user in TCP/IP
    - Attacker can target/compromise any layer potentially
- Where is the attacker
    - Client's/My network
    - Internet 
    - Server's network
    - Server machine itself
        - Generally considered not the network's fault/issue

### Internet Attacks
- Can't attack ethernet headers from the internet
    - Routing device will strip headers off
- IP can't be messed with as it is needed to route to devices
- Access to TCP, App, and user

### Internal Networks
- Can attack all layers
- Really do anything
- I believe can mess with internal services / routing

### Server Network Attack
- Full access as will if we connect to them
- Infront of servers 
- Thought is potentially rerouting internally for servers

### Timeline of Attack
- Vulnerability
    - Design 
        - Everyone running it is vulnerable 
        - May or may not be able to be compromised
    - Impl 
        - Code that impl is vulnerable
        - Different languages or OS
    - Config
        - I done messed up setting up implementations
        - Windows regkeys / network settings
        - Firewall issues
- Exploit
    - Most cases yes, yes we can
    - Some cases (academic challenges) are a stars align case
- Implementation / Code
    - Metasploit
    - python scripting
    - What ever you want to run
    - Rust ransomware baby
- Attack
    - Can we get the code to run 
    - Need user interaction
    - Can it be delivered via internet

### Standpoint
- They are already in and active


## Taxonomy

### Header Based
- Invalid headers
- How will machines handle invalid headers
- Mostly design or *implementation* issues
- New impl / redesign will fix the issue
- Local network src and dst addresses
    - Send themselves packets and loop
- Bits for configurations 
    - Bit for open and closed
    - 1 bits in TCP SYN/ACKs must be set
    - Just misconfigure and see how it is handled
    - Reserve bits 
        - Ignored so implementation won't handle the same
- Levels
    - Should be between a value 
    - Or counts so can't be less than prev (SEQ num)
    
#### Ping of Death
- 1 packet attack 
- IP spoofing
- IP fragmentation 
    - Offset and length
    - Last frag bit
    - Offset is by bytes
- Offset to large number
    - Larger than the buffer created
    - Simple memcpy writes to stack in kernel networking
    - Blue screen

### Protocol Based 
- Typically valid packets
- Missing or out of order and see how they respond
- Interject packets 
    - Valid packets say done with communications as remote src

#### SYN Flood
- SYN must have a buffer space allocated
- Spamming syn without responding ACK 
- Allocs a ton of buffers to a device
- Won't drop them until delta T expires for a connections
- Send greater than 1 in delta T time
- Set limit per IP
- Either fake address or use N devices




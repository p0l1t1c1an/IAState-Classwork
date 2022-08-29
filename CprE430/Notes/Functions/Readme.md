### Segmentation and Reassembly
- We have fixed size packets 
- Must split up large data to many packets (Segmentation)
- Put back data together from packets (reassembly) 

### Encapsulation
- Add control info to packets as header
- Type of info
    - Address
    - Error detection control
    - Protocol Control
        - Manage data
        - Describe data
        - Peer Comms

### Connection Control
- Handles the creation of a conntection/logical association
- Phases 
    - Request/Connect
    - Data transfer
    - Terminate 
- Connection-less 
    - No connection control needed
    - No coordination

### Ordered Delivery
- Connection Oriented (only but not required)
- Data is recv and sent in same order

### Flow Control
- Many devices between start and end 
- These devices could get overwhelmed
- System to slow down the sending of packets 
    - Some systems drop packets to do this

### Error Control
- Recovery from lost/damaged PDU
- Packets that are damaged are treated as missing to higher layers
- Mechanisms
    - Positive ACK
    - Restransmit after timeout
    - Error detection

### Multiplexing
- Upward - set up multiple higher layers on a single lower layer
    - Many browser tabs (System addresses these to seperate them)
    - Many network connections on single NIC
- Downward - Single higher layer split among many lower layers
    - Many NICS for load balancing like I have on my router

![multi](./multiplexing.png)


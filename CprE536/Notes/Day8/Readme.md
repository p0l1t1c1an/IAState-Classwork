
# OS Basics

### Operating System
- abstract hardware 
- portable
- protection from misuse of hardware system

### Process management
- Separate each process 
    - Memory and Stack
- Multi-threaded shares heap but separate stack
- Run one process at a time
    - Scheduling

### Parallelism
- Wait on IO?
    - Turns into block state
    - Run another process
- Round robin is another technique

### Process vs Program
- Process is the program while running
- Program is the instructions and data
- Run a program multiple times
    - Same program but different processes

### Address Space 
- stack 
- (gap filled by dynamic and stack)
- dynamic
- bss (Globals)
- data
- text

### Page Replacement
- Random
- FIFO
- Optimal
    - Impossible to actually design as it looks into the future
- Least Recently Used

### Disk Addressing
- Continuous Allocation 
    - File is in one whole sequence of block next to one another
    - Need table to show where every "file" starts
- Linked List 
    - File descriptor points to first point of a file
    - Block points to the next one
- Multilevel indexed files
    - inodes
    - File descriptor == 14 block pointers
    - Can point to indirect blocks 
        - Shown double layering this
        - Can go infinite?

# Networking  

![ip_datagram](ip_packet.png)

- Windows one ip packet crashes OS
    - Version number 0 does this
    - No logging
- Important ones
    - Version number
    - Type of service
    - length
    - Fragmentation / reassembly
    - Time-to-leave
    - Upper layer protocol
        - ICMP == 1
        - TCP == 6
        - UDP == 17
        - OSPF == 89
    - Options
        - timestamp
        - record route taken
        - list of routers to vist

### IDS / IPS
- IDS/IPS bases off match on packets
- Find patterns
- Going to bad sites
- Fragmentation can break the pattern matching
- Add info between some chars
- Give that extra info lower number of max hops
- They drop on the way 
- Closer to reciever can see it normal
    - IDP/IPS there could stop this
    - My thought is this would likely be message to C2

### Reassembly
- Wait until we get everything
- All done is at the final reciever

### TCP and UDP
- TCP
    - Congestion, flow, and error control
    - Connection oriented
- UDP
    - Best effort
    - Send and pray


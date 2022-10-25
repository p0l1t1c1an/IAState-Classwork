
# TCP Attacks

### Header Based
- Invalid Flag combinations used to break things
- Fixed but now able to help determine type of OS
    - Probing attacks
    - Invalid header response
    - Initial values
        - Seq Number
        - Window Size

### Protocol Based
- Syn flood
- Reset Packets
- Session Hijacking• Session Hijacking


### SYN Flood

![syn_flood](./syn_flood.png)

### RST Shutdown

![rst_shutdown](./rst_shutdown.png)

### Session Hijacking

![sess_hijack1](./sess_hijack1.png)


![sess_hijack2](./sess_hijack2.png)

- Can see the data and become the user

### Mitigations 
- Session Hijack
    - Encrypt the traffic
- RST 
    - 
- SYN Flood
    - Cloudflare
    - Firewalls have mitigations


### Passive Network Filter

![filter1](./sess_hijack1.png)


![filter2](./passive_filter.png)

- Filter catches access attempt to something not allowed
    - Resets the connection to prevent access

### Authentication Based
- No Authentication 
- Ports aren't but are

### Traffic Based
- Flooding 
- QoS
    - Application layer / Port switch
    - Traffic type separation and speeds
        - change Window Sizes
        - 3 acks
- Sniffing
    - Can't stop sniffing of tcp
    - Encrypt data though





















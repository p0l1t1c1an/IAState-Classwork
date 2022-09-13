
# Physical Layer

### One Off 
- Only see what is coming from ISP to our network
- Can't control

### Main Idea
- HW Layers of Physical Layer
    - Physical Medium Data is sent over
    - MAC/HW address for the computer
    - Device interface into the computer (vendor)
- SW Layers of Physical Layer
    - Drivers for device 
    - SAP buffer (for the next layers)

### Attacks
- Malicous Driver Update
- Can't really change the HW
- Can insert own device 
- Wireless (don't follow rules)
    - Jamming
- Physical attacks
    - Attack the medium
    - Bad cable

### Hardware Failure
- Can look like an attack is occurring
- Routing repeated sending of broadcast packet
- Opposite is probably also true

### Same address
- Both think it is for them 
- Both take it and push it up their stack

### LAN Protocols
- Ethernet won, baby!!!
- Token Ring / IBM
- Token Bus / Motorola

### CSMA/CD
- ????
- Collision Detection
- Listen, quiet?, talk, listen, collision?, backoff, 
- This is from 489
- Need to see if the bus is busy
- Listen for interference by another person talking over us
- When detect collision
    - Random backoff 
    - Range is bigger more collisions in a row 
    - 16 time the packet errors

### Breaking the collision domain
- Repeaters - increase length
- Hub - all to one and broadcast 
- Bridges - Ethernet back and forth
- Router - Split networks 
- Switch - Intelligence and VLANS baby!!! 


### Switches
- 2 machines in a collision domain only
- Split up talking and recv
- Buffer containing hardware addresses to the port it came from
    - Learns and updates
- Multiple entries per table 
    - Stacking switch behind switch works






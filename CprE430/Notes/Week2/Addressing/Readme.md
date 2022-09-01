
### Application Addressing
- Packet entering network
- Multiple addresses needed
    - Decision points
        - HW Addr / Separates on shared physical bus
            - MAC addr
        - Protocol Type / Multiple protocols other than IP  
            - IP addr (can have multiple on one device)
        - Transport Protocol Type 
            - TCP or UDP (when over IP)
        - Application identifier
            - port number
            - socket
        - User identifier / Multiple users per an app?
            - Cookies
            - Login

![app_addr](./application_addressing.png)


### Address Assignment
- Potential Attack points on how they are addressed
- Static Addressing
    - Builtins 
        - This is /etc/protocols that defines/numbers all TCP, UDP, ICMP ... 
    - Configurable
        - IP and MAC can be changed via software
- Dynamic Addressing
    - Use a protocol to get an address 
    - User provided
        - From user input like getting a web site

- Who assigns it
    - Central Authority
        - DHCP
    - Ad-HOC
    - Local based controls 

### Hardware Addresses
- Vendor assigned
    -  Can be forged
- Used by security devices 
    - Netreg

### IP Addresses
- Public side
    - Distributed assigners / ISPs
    - Statically handed out by providers

### Port numbers 
    - Default for applications
    - Well known ports

### Hostnames
- DNS baby
- For users to be able to address devices that they can go to
- Domain names are what users can read
- Domain maps to ip address
- Federated central authorities
    - Regionally local 
    - Work with companies to make money / distribute addresses

### Headers
- Fixed header
    - Packets
    - Plus defined options 
    - Fixed part are the same always
        - Generally starting with addresses to read as little as possible
    - Trailers at first layer
        - Error control

![packet](./packet.png)

- Freeform headers
    - For each application 
    - Need software that can parse them
    - Kinda like html/xml

- Need to be making the assumption that attackers can put any information in the headers
    - How do we handle those cases 


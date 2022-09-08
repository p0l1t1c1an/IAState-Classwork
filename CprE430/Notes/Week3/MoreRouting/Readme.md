
### IP addresses information to come
- Public/Private IPs

### Machine Names
- On top of the internet
- Breaks applications to lose them not the internet
- Simplest example is DNS

### Domain Name System
- Was just one file that people downloaded before DNS
- Only need to ask one place and will get address
- Physically centralized and logically distributed
- Recursive lookup
    - We ask local DNS server
    - It asks root server
    - That tells us to ask .com server
    - That tells us to ask google.com server
    - Get back www.google.com

![recursive](./DNS_recursion)

### Client Server Model
- Networking standpoint
- Server 
    - Waits for client to initiate connection
    - Runs all the time
    - Calls / Open, Listen, Wait (in C code) 
- Client 
    - Know how to address server
    - IP/Port / network side need this / likely from DNS name
    - Provides IP and port to server to respond
    - Client port is unique so multiple connections can't use the same port (multiplexing done by OS)
    - Calls / Open, Connect (in C code) 


![server_client](./server_client)


![SC_with_internet](./sc_internet)


### Multiplexing
- Servers on one single port need to respond to multiple clients 
    - May be on different addresses and ports
    - May have clients on same ports and different addresses
    - May have clients from same address and different ports
- Interesting as they need to associate different authentication to ports and IP pairs in the application

### Routing 
- Minimal knowledge to route info (packet switching)
- Lifetime of 254 routers/hops
- Routing table
    - Dest
        - where can I go
    - Next Hop (for each dest)
        - who will help me
    - Interface (for each dest)
        - physical
- Interconnecting networks

- Basic table on endpoint
- Dest      Next        Int
- My net    Me          *
- Default   Router      *

### How we build the table
- Static 
    - Gross but also perfect
- Dynamic
    - Technically they can change
    - Boot time gets IP address and route table with it
    - OR 
    - (He did not say so look up in the book) 


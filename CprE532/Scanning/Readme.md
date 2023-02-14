
# Scanning / Reconnaissance

- Stay invisible
- Detect Servers
- Detect Vulnerabilities

### Header Scanning
- Invalid header format sent
    - But still get back response
- Can use response to determine information about device connecting to

### Protocol Scanning
- Responses to different valid messages
- May report different by which version
    - Additional (plugin) services may change responses


### Authentication Scanning
- Generally not in scanning/recon
- Can test to see if there exist authentication on open ports or services
- May use MAC or IP as authenticator


### Traffic Scanning 
- Not used 
- Generally just sniffing and flooding 
    - Can't sniff externally
    - Flooding is an attack

- One concept that isn't meaningful
    - Sent packet might be replicated internally
    - Get multiple responses back

### User Scanning
- Social Engineering 
    - Have gotten email information from footprinting
    - Phishing, trying to reveal information from employee 

### TCP Streams
- Create them in special way to avoid detection by defensive mechanisms



# Scanning Techniques


### Ping / ICMP
- Use ICMP
- Echo / Echo reply
- Generally blocked from external 
    - Unless dedicated to allow ping
    - 8.8.8.8 is good example
- Tells if computer is alive 
- Ping sweep
    - Going through range of IPs and see what is alive
    - Fping in unix
    - Async


### TCP Ping
- Check if TCP port is open
- Common to have 80/443
- May be dependent on service
    - Mail provider has 25, 110, 143 and tls equivalents
- Sweeping tries common ports (or range)


### ICMP enumeration
- Try other ICMP 
- like Timestamp 
    - Generally shouldn't be used


### Detection
- Watching traffic for sweeps
- IDS 
- Typically signature based
    - Idea: checks for iteration linearly of IP addresses
        - Could bypass by going around randomly
    - Idea: checks for one ip going connecting to many in small time frame
        - Could slow down or use many devices/IPs


### Prevention 
- Block as much ICMP as possible
- Need to allow (only incoming) 
    - Echo reply
    - Host unreachable
    - Time exceeded
- From this, it seems like only should have echo outbound and echo reply inbound


### Breadth Scanning Complete
- Have list of device that are alive
- Now to depth scanning
    - Determine info regaurding the devices
    - OS, software, vulnerabilities


### Discover Service
- Indepth TCP scan
    - Try connecting to all the ports
    - Could do complete connection 
        - logged by service
    - Incomplete TCP connection (half open)
        - Only send syn
        - wait for syn/ack
        - Service not likely to log
            - Network monitoring will
    - Send TCP Fin
        - RST must be sent if FIN to closed port
            - Should by TCP standard
            - Not always done
        - Xmas tree
            - Fin, Urg, and Push
            - Same idea

    - TCP Null scan 
        - No flags are set
            - Should send RST on closed port
    - TCP ACK scan
        - Poorly configed stateless firewalls
        - Stateful prevents

### Remote Procedure Call
- Remote service that tells what is running on the machine

### UDP for scanning
- Stateless
- Send UDP packet to port
- Only should be DNS/53
- Generally blocked by border


### Tools
- Netcat and Nmap
- Tenable / Nessus to automate / graphical front end
    - IMHO
    - There are others
- Cheops
    - Draws picture of network

### Counter Measures
- Generally detection like IDS
- Otherwise remove services that don't need to be public


### Determine OS and Software
- Reading banners 
    - Services may tell version and what they are
- What services are running?
- Stack fingerprinting
    - How do they respond to invalid packets
        - OS may act differently
    - Sequence numbers
    - don't fragment bit
        - Invalid fragment (overlapping)
        - Do they overwrite or no?
    - TCP window size
    - Unsure if nmap -O scan does all this to guess OS


### Passive finger printing
- More  difficult
- Monitor the network with sniffer
- Look for 
    - TTL
    - Window size
    - Fragment



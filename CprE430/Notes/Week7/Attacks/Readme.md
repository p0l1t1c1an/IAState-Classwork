
### Header Based
- Some IP header attacks
- Ping of death
    - Most famous
- Most fixed
- Few ARP and ICMP


### Protocol Based
- IP is simple but routing complex
- Large number of protocol attacks
    - Sending packets to confuse the reciever
    - Or interject packet
- No authentication of the packet sender/receiver
- ICMP
    - Redirect 
    - Convince device that your device is the router
        - Go through my device
        - Then, pass to the actual router
- ARP 
    - ARP cache poisoning
    - Kind of an auth attack
    - Reply to arp request that the device shouldn't


### Authentication Based
- Often use IP as authentication
- IP
    - Address Spoofing
        - Difficult unless you see traffic
        - Hard to stop if attacker in right spot
        - On same network can pretend to be H1
            - Why, assume identity and get messages
            - Convince H1 I am router and router I am H1
                - Session hijack
    - Mitigation
        - Check the source addr before allowing in/out the network
            - Prevents external spoofing of internal network and vice versa
- ARP
    - Cache poisoning


### Traffic Based
- Sniffing
- Broadcast flooding
- Flooding routers as DoS
- ARP Broadcast Flood
    - Mitigated by bandwidth
    - Sweep discovery on the network 
    - Uses up network bandwidth and 4 ARPs broadcast



# Security Threats


### Wireless is Different
- Physical layer issues
    - Coverage
    - Harshness of the radio channel
        - High error rates need mitigation
        - Effect on protocols
- Signals “leak” outside desired perimeter
    - Eavesdropping
    - Ease of “attaching to network”
    - Denial of service or channel degradation?
- MAC layer issues
    - Shared “broadcast” medium
        - Need for a simple decentralized medium access mechanism
    - Performance
        - Throughput, delay and QoS
- Shared => Easy
    - For a malicious user to get in
    - To capture packets
    - To launch active attacks

- Mobility management
    - Location management
        - Tracking where a MS is
    - Handoff management
        - Routing packets as a MS moves
- Radio resource and power management
    - Assignment of radio channels and transmit power
    - Admission control, power control and handoff decision
- Legitimate or malicious signaling messages?
    - Examples of Impact
        - Raising  power level can increase interference
        - Unnecessary handoffs can increase load
    - Privacy issues of “tracking”

- Network design and deployment
    - No single type of wireless access is available everywhere
    - Spectrum is scarce
        - Coexistence, interference, planning
- Legitimate or malicious interference?
    - Rogue access points vs Microwave ovens
- Network operations and management
    - Accounting and billing to charge subscribers correctly
    - Access to resources and services on the network
- Is an MS legitimate?
    - Roaming MSs must be authenticated
    - Different domains and access policies make this difficult

- Service discovery and data management
    - Sensors and RFIDs
        - How is data maintained? 
        - Where should data reside? 
        - How can it be efficiently accessed?
- Protection of data
    - Who should be allowed to read/write the data?
    - How do we ensure integrity and confidentiality?
- Form factor and capabilities
    - A mobile device has to be light weight, durable, have long battery life 
    - Yet be capable of performing complex tasks
    - Energy efficient design of software and protocols
- Security is expensive
    - Mobile devices are resource constrained


### Wireless Threats
- Accidental Association
    - Overlapping networks
    - Connecting to neighbors on accident
- Malicious Association
    - Malicious access points steal password
    - Coffee shop free wifi
- Ad-Hoc Networks
    - Two devices exchange data
- Nontraditional Networks
    - Bluetooth can be used to eavesdrop
- MAC spoofing
    - Change MAC address to match privileged computer
- MitM 
    - Rouge access point pretends to be real ap
- Denial of Service
    - Keep medium busy
- Network injection
    - Spoof routing/management messages



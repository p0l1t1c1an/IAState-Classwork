
# Vehicular Networks


### Vehicular Networks
- Networks built upon vehicles, where a vehicle can exchange information of its neighboring vehicles, mobile devices, and infrastructures.
- The concept was first introduced in 2001 as the key part of intelligent transportation systems (ITS).
- Vehicular networks aim to provide road safety, navigation, and other roadside services.
    - Traffic information systems
    - Road Transportation Emergency Services


### V2X Comms
- Following communication scenarios in vehicular networks are collectively called V2X communications:
    - Vehicle-to-pedestrian (V2P)
    - Vehicle-to-vehicle (V2V)
    - Vehicle-to-network (V2N)
        - vehicular UE and serving entities w/ cellular networks
    - Vehicle-to-infrastructure (V2I)
        - vehicles and roadside units (RSUs)

### Continuing
- Communications technologies involved:
    - Dedicated short-range communications (DSRC)
    - Cellular network technologies
- DSRC: used to be considered as the main technology to support V2X.
- However, research communities are nowadays more interested in cellular network-based technology.
- 3GPP has already completed its Release 14 with LTE based V2X service as one of the main features.
- Organizations like 3GPP and Qualcomm have also prepared the roadmap towards 5G based V2X service.



### Dedicated Short-range Communications
- 75 MHz band of 5.9 GHz
- IEEE 1609
- 802.11p
- Latency critical

- Challenges
    - Channel congestion in dense vehicular env
    - Lack of handshake/ACK in broadcast
    - No QoS
    - Limited network comms / no internet connectivity
    - Lack of ability to receive broadcast messages
    - 802.11bd is in early stage


### Security and privacy in V2X communications
- Security and privacy are vital in most of the wireless communication systems.
- Due to the nature of wireless channels, the V2X system is vulnerable to diverse network attacks:
    - On-the-fly information may be eavesdropped, modified, or replayed.
    - Attackers may forge malicious information or impersonate legitimate entities to distribute malicious information.
    - Attackers may gain some private information of a legitimate user after intercept a large number of messages.
- Protecting both V2X entities and messages from attacks is significant


### V2X Arch
- Trust Authority
- Access points
    - Base stations
    - Roadside units
- Vehicles 
    - have on-board units (OBU)


### The trust authority (TA)
- The TA is a trust administration which manages the registration process of all OBUs and APs.
    - Certificates of OBUs and APs are usually provided by the TA after registration.
- The TA is responsible to preserve all the information of legitimate users
    - real ID and location of APs
    - real ID and reputation scores of OBUs
    - for revealing the real identities and locations of the malicious users.
- In most cases, the TA is fully trusted with unlimited storage and strong computation capability.



### Access points (APs)
- LTE BSs and RSUs are two kinds of infrastructures deployed on roadside to provide various V2X services.
- LTE BSs provide cellular based V2I communications. 
- RSUs provide DSRC based V2I communications.
- They are responsible to manage communications
    - between vehicles within their coverage area
    - deliver messages sent from vehicles to the TA through wired networks.
- APs are considered to have less computation capability and more vulnerable to attackers compared to the TA.



### On-board units (OBUs)
- An OBU usually is a tamper-proofed device (TPD) loaded on a vehicle 
    - to support communications between OBUs and APs through wireless access.
- Credential information of the vehicle owner/the driver usually is stored in the OBU.
- In general, OBUs are considered to have limited storage and computation capability.



### Security Concerns in V2X


### Authentication
- Authentication is one of the most important security requirements and usually be the first line against various attackers.
- Entity authentication: 
    - In V2X system, each legitimate entity should be uniquely identified and authenticated before launching any activities.
    - Vehicles
    - RSU
    - eNodeB
    - Application server
- Message authentication: 
    - All messages should be authenticated so that they can be verified by the receivers. 
    - And receivers can verify the received messages sent by authenticated entity


### Availability
- Availability requires that (under DoS attacks):
    - V2X services should be accessible to authorized V2X entities.
        - Vehicles have access to various services.
        - Application server is enable to handle all the application requests.
        - The core network should work functionally all the time.
        - eNodeB can provide V2N communication for vehicles.
- Exchanged messages should be accessible to intended receivers.
    - V2X entities can keep receiving messages and react. 



### Data confidentiality and integrity
- Data confidentiality:
    - To protect sensitive data from being exposed to malicious entities
    - messages generated in V2X communications should be properly encrypted.
- However, some information exchanged in V2X communications is in plaintext for road safety.
- Data integrity:
    - This requirement ensures that messages exchanged in V2X communications can be verified 
    - so that any modification of the message during transmission can be detected.


### Authorization
- Authorization ensures that V2X services can only be accessed by authorized V2X entities.
    - Pre-defined rules and policies can be utilized.
    - Specific rules can be used for allowing/denying V2X entities to certain services.
    - Also, first responders may have a global view of the network information


### Privacy preservation
- Privacy issues in V2X communications can be split into identity privacy and location privacy.
- Identity privacy requires both
    - conditional anonymity of a specific entity
        - requires that an entity’s real identity cannot be revealed by others entities except the authorities.
    - unlinkability of all the messages sent by the same entity to be satisfied.
        - requires that received two different messages cannot be identified to be sent by one entity or two different entities.
- Location privacy requires that each entity’s real location will not be exposed to other entities.


### Recommendations from 3GPP
- To ensure security requirements, 3GPP has recommendations which can be summarized as follows:
- Each entity can authenticate and verify the received messages that was sent by an authorized entity.
- Data confidentiality and integrity should be guaranteed against various attacks.
- Only the fresh messages will be accepted so that the replay attack and DoS attack can be alleviated.
- The cryptographic algorithms should be light weighted and have high security level due to the stringent time requirement and the safety related property.






### Stepping Stone Attack Attribution
- Identify attackers
- Passive or Active


### Shot spotter
- Triangulates gun shots through microphones on streetlamps
- Can determine rough location of shot
- Hasn't reduced crime or helped gather evidence
- But police could (theoretically) respond more quickly
- Discrimination to poor black community
- High spending when could be given to community

### ID Source of Attack
- Need more secure IT 
    - Not enough
    - Unexpected interactions and fails
    - Users with privilege but bad training or malicous
- We need reliable tools to invest when untoward event occurs
    - fix colatoral
    - id cause
- Hard?
    - No caller ID in IP network
    - Spoofing source of IP
    - Attackers are hiding and adapting 
        - Use of network traffic relays
            - Stepping stones / proxy
        - Use of concealment techniques
            - Deley and chaff perturbtions / packet split/merging
        - Use of normal network traffic to carry attacks
            - Voip
    - Attack traffic looks like normal traffic
    - Stepping stones 
        - Attackers can go relay to many devices through SSH
        - IP traceback only reaches the last device they initiate attack from

### Problem 
- Attacks go through compromised devices to send attacks
- Correlating thraffic coming into and going out of a stepping stone
- Attacks/Commands may be over encrypted channel
- Correlating Traffic
    - Problems
        - headers are removed or replaced
        - packet application payloads encrypted
    - What left?
        - Timing of packets
        - Stepping stone gets packets from one source at 1,2,5,6
            - Sends out attacks at 3,4,7,8
            - Timing pattern is the same
            - Likely a static deley for how long it takes to read packet and send out attack message

### Tracking Through Stepping Stone Challenges
- Ambiguity / similarities in traffic timing
- Timing pertubation or deley jitter
    - Breaks the idea above that we had
    - As the repley host or final attack now does not operate with attack
- Packet merging/spliting
    - Breaks the timing as well
- Traffic padding
    - Additional packets make timing difficult
- Use multiple protocols


### Techniques
- Active 
    - Watermarking Traffic Timing for Tracing purposes
- Passive
    - Deviation based
    - ON/OFF Based
    - CLT / Computational Learning Theory
    - MTD-Based
    - Data-Tick-Based


### Watermarking
- Add watermark to traffic
- Digital watermark
    - pattern of bits inserted into files to id copyright
- Desirable properties
    - invisible to casual inspection
    - Withstands modifcation of content
- Perfect for tracing
    - Inject into packet and can track it through network with detectors

- Tell same flow of traffic and not multiple with same
- Idea 1: Calc inter-packet delay mod s
    - 0 increase IPD mod s = 0
    - 1 increase IPD mod s = .5s
- Changes if different latencies so may not be reliable

- Idea 2: compare average IPD of two groups of packets
    - Encding
        - 0: increase IPD of group A, decrease IPD of group B, so group A avg IPD > group B
        - 1: increase IPD of group B, decrease IPD of group A, so group A avg IPD > group B

- Adding Redundancy 
    - Too much timing jitter will reslt in errors in decoding the water mark
    - redundant coding (increase # of IPDS for encding one bu 

- This needs to be added to

### Passive Approaches

### Deviation Based
- Interactive connection telnet
- Many connections to a device
    - Chain C = <C1 ... Ci ... Cj ...>
- Calculation deviation (similarity) of one packet stream and another
    - One upstream and one downstream
    - Calculation different between them (like seq number or timing)
    - Smaller means they are connected
    
### ON/OFF Based
- Encrypted Stepping stones
- On and Off periods
    - When there is no data traffic on a flow for more than T seconds, 
        - the connection is considered off
    - When packet with non empty payload then appears,
        - Flow ends its off period and begins ON period
        - which lasts until the flow again goes data idle
- Correlate connections based on coincidence in when OFF periods end and when On period begins
    - See when connections on one machine the Off period match and On period ~match



### Packet merge / split
- Data Tick
    - time and size summary of a group of packets from server to client 
        - in an interval


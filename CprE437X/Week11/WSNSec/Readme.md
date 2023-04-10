
# WSN Security


### Obstacles to WSN Security
- Very limited resources
    - Limited memory and storage space: 
        - A typical sensor has a 16 bit 8 MHz CPU with 10K RAM, 48K Program memory and 1 MB flash storage
    - Power limitation
- Unreliable Communications
    - Unreliable Transfer
    - Conflicts
    - Latency
- Unattended Operation:
    - Exposure to physical attacks
    - Managed remotely: hard to detect physical tampering


### Potential Attacks on WSNs
- Physical Layer Attacks
- Link Layer Attacks
- Network Layer Attacks


### Attacks on Physical Layer
- Jamming
    - Defense
        - Spread-spectrum
        - Region mapping
- Tampering
    - Defense 
        - Tamper proof, hiding

### Link Layer Security in WSNs
- In‐network processing
    - In most applications intermediate nodes process data (mostly aggregate)
    - Thus we need link‐by‐link security
        - Confidentiality, authentication
- For link‐level security
    - We need pairwise keys, and
    - a secure link‐level communication protocol
        - E.g. Tinysec


### Network Layer Security in WSNs
- In Network Layer, attacks are mostly routing‐related
- Some of them
    - false (i.e. spoofed, altered, or replayed) routing information
    - selective forwarding
    - sinkhole attack
    - sybil attack
    - wormholes
    - HELLO flood attacks
    - acknowledgment spoofing


### Black Holes
- Diance vector weakness
- Node advertises zero cost to all routes


### Sybil Attack
- A single node pretends to be present in different parts of the network
- How is Sybil attack detected?
- The received signal power from a sending node is matched with its claimed position. 
    - By using this method, received signal power can be used to calculate the position of the node.


### Hello flood attack
- Specific to WSN
    - In some protocols, nodes advertise themselves with hello broadcast
    - No auth
- Attacker can convince nodes its a neighbor


### Acknowledge spoofing
- Adversary spoofs ACKs to convince the sender a weak/dead link supports good link quality 



### Countermeasures
- Shared Key and link layer encryption 
    - Prevent outsider attacks
    - Not insider attacks
- Sybil attack
    - Every node shares secret key with base station
    - Create pairwise sharked for authentication
    - Limit number of neighbors

### Key Distribution in WSNs
- We said that
    - The need for in‐network processing requires link‐by‐link security
    - Confidentiality, authentication
- Key distribution is a must
    - Characteristics of a sensor network must be taken into account for key distribution
    - Public Key Cryptography
        - There is research to implement the PKC fast enough for sensor nodes, but PKC is not practical
        - So we need symmetric crypto for encryption and HMAC‐like keyed authentication
- Bottomline: For link‐based encryption and authentication, we need pairwise keys



### Other Key Types
- Link keys (pairwise keys) are needed for unicast communication in the link level
- Other communication patterns in WSN
    - End‐to‐end (between a particular node and sink)
    - local broadcast
    - global broadcast
- necessary key types (in addition to link keys)
    - node keys / shared by a node and the base station (sink)
    - cluster keys / shared by a node and all its neighbors for local broadcast
    - network key / a key shared by all nodes and the base station/sink for global broadcast



### The Challenge of Key Distribution
- The challenge is in the distribution of the link keys
- How to put these keys into nodes
    - Sensor nodes are to be manufactured (keys may be put in the nodes during this process)
    - But we do not know which node will be communicating with which others after deployment


### Two Naive and Extreme Approaches
- “Single master key” approach
    - There is only one key shared by everyone prior to deployment
    - All pairwise keys are derived from this master key
    - Disadvantage: single point of failure (if the attacker got the master key)
    - Advantage: memory‐efficient
        - perfect resiliency against node compromise
- “All keys in all nodes” approach
    - Random pairwise keys to communicate with all other nodes are stored prior to deployment
        - If there are N nodes, then each node stores N‐1 keys
    - Disadvantage: memory inefficient
        - even one node compromise collapses the entire network.
    - Advantage:


### The Hierarchical Key Management Approach
- the practice of organizing nodes in a network into groups or clusters
    - where each cluster has its own set of keys. 
    - The keys are then used to secure communication within and between clusters.
- Advantages
    - Increased security
    - Flexibility
    - Reduced overhead
- Disadvantages
    - Increased complexity
    - Increased processing


### Random Key Predistribution
- Original idea by Eschenauer and Gligor (ACM CCS 2002)
    - Pick some keys out of a key pool at random and put them into nodes before the deployment
    - Nodes try to figure out if they have common keys after the deployment


### Steps
- For each node, m keys are selected randomly from the pool and pre-loaded in the node (key ring)
- after deployment, each node finds out with which of its neighbors it shares a key

-  neighboring nodes that do not have a common key in their key rings 
    - establish a shared key through a path of intermediaries
    - each link of the path was secured in the direct key establishment phase

### Performance Metrics
- Local Connectivity
    - Probability that any two neighboring nodes have a common key
- Global Connectivity
    - Does the graph that is obtained after link (direct) key establishment cover all nodes?
- Resiliency
    - If a node is captured, all keys that this node carries are compromised
    - This causes some other links that these keys are used to be compromised as well
- Communication cost
    - Analysis of number of hops in path key establishment



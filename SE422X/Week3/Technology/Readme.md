
# Technologies Enabling Cloud


### Virtualization
- Virtualization refers to partitioning the resources of a physical system into multiple virtual resources
- Key enabling technology of cloud computing that allow pooling of resources  
- In cloud computing, resources are pooled to serve multiple users using multi-tenancy

### Hypervisor
- Type 1
    - Baremetal
- Type 2
    - In OS

### Virtualization Types
- Full
    - Binary translation
    - Slow
- Para
    - Modify Kernel to execute hypervisor calls
- Hardware
    - No modification or translation
    - Hypervisor traps calls that are sensitive
    - Basic calls operate normally

### Load balancing
- Cloud computing resources can be scaled up on demand to meet the performance requirements of applications. 
- Load balancing distributes workloads across multiple servers to meet the application workloads. 
- The goals of load balancing techniques include
    - Achieve maximum utilization of resources 
    - Minimizing the response times 
    - Maximizing throughpu

- Algorithms
    - Round Robin load balancing 
    - Weighted Round Robin load balancing 
    - Low Latency load balancing 
    - **Least Connections load balancing**
    - **Priority load balancing** 
    - Overflow load balancing

- Persistence
    - Load balancing can route successive requests from a user session to different servers
    - Need way to maintain the state or the information of the session

- Persistence Approaches 
    - Sticky sessions 
    - Session Database 
    - Browser cookies 
    - URL re-writing


### Scalability and Elasticity
- Multi-tier applications such as e-Commerce, social networking, b2b can experience rapid changes in their traffic 
- Capacity planning involves determining the right sizing of each tier of the deployment of an application 
    - in terms of the number of resources and the capacity of each resource 
- Capacity planning may be for computing, storage, memory or network resources

- After design/planning, we monitor and refine
    - Everything in the system
        - Deployment
        - Replication
- Define metrics before we can monitor 


### Deployment
- Cloud application deployment design is an iterative process that involves: 
    - Deployment Design 
        - The variables in this step include: 
            - Number of servers in each tier
            - Nomputing, memory and storage capacities of severs
            - Server interconnection, load balancing and replication strategies.  
    - Performance Evaluation 
        - To verify whether the application meets the performance requirements with the deployment
        - Involves monitoring the workload on the application and measuring various workload parameters such as response time and throughput.  
        - Utilization of servers (CPU, memory, disk, I/O, etc.) in each tier is also monitored. 
    - Deployment Refinement 
        - Various alternatives can exist in this step 
            - vertical scaling (or scaling up)
            - horizontal scaling (or scaling out)
            - alternative server interconnections
            - alternative load balancing and replication strategies

### Replication
- Replication is used to create and maintain multiple copies of the data in the cloud
- Cloud enables rapid implementation of replication solutions for disaster recovery for organizations
- With cloud-based data replication organizations can plan for disaster recovery 
    - without making any capital expenditures on purchasing, configuring or managing secondary site locations
- Types 
    - Array-based Replication 
    - Network-based Replication 
    - Host-based Replication

### Monitoring
- Monitoring services allow cloud users to collect and analyze the data on various monitoring metrics 
- A monitoring service collects data on various system and application metrics from the cloud computing instances
- It allows the users to keep track of the health of applications and services deployed in the cloud


### Network Function Virtualization
- NFV is a technology that leverages virtualization to consolidate the heterogeneous network devices onto industry standard high volume servers, switches and storage. 
- NFV comprises of network functions implemented in software that run on virtualized resources in the cloud.  
- NFV enables a separation the network functions which are implemented in software from the underlying hardware


### MapReduce
- Google search 
- MapReduce is a parallel data processing model for processing and analysis of massive scale data
- MapReduce phases: 
    - Map Phase: 
        - Data is read from a distributed fs
        - Partitioned among a set of computing nodes in the cluster
        - Sent to the nodes as a set of key-value pairs
    - The Map tasks process the input records independently of each other and produce intermediate results as key-value pairs
    - The intermediate results are stored on the local disk of the node running the Map task  
    - Reduce Phase: 
        - When all the Map tasks are completed, the Reduce phase begins in which the intermediate data with the same key is aggregated

### IAM
- IAM for cloud describes the authentication and authorization of users to provide secure access to cloud resources  
- Orgs with multiple users can use IAM services for management of user identifiers and user permissions
- IAM services allow orgs to centrally manage users, access permissions, security credentials and access keys  
- Orgs can enable RBAC to cloud resources and applications using the IAM services  
- IAM services allow creation of user groups where all the users in a group have the same access permissions  
- OpenAuth, Role-based Access Control (RBAC), Digital Identities, Security Tokens, Identity Providers, etc.


### Billing 
- Elastic Pricing 
    - Pay-as-you-use pricing, the customers are charged based on the usage of resources   
- Fixed Pricing 
    - Fixed pricing, customers are charged a fixed amount per month for the cloud resources 
- Spot Pricing 
    - Spot pricing models offer variable pricing for cloud resources which is driven by market demand



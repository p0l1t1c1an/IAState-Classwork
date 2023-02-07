
# Cloud Services


###  Compute Services 
- Compute services provide dynamically scalable compute capacity in the cloud
- Compute resources can be provisioned on-demand in the form of virtual machines
    - Virtual machines can be created from standard images or custom images created by the users 
- Compute services can be accessed from the web consoles of these services 
    - that provide graphical user interfaces for provisioning, managing and monitoring these services 
- Cloud service providers also provide APIs for various programming languages
    - allow developers to access and manage these services programmatically.


### Storage Services 
- Cloud storage services allow storage and retrieval of any amount of data any time
- Buckets or containers
- Scalability
- Replication to multiple facilities 
- Access Policies
    - Access Control Lists (ACLs)
    - bucket/container level policies
- Encryption
    - Server side encrytion 
- Consistency 
    - Strong data consistency is provided for all upload

### Amazon S3
- Buckets 
    - Data stored on S3 is organized in the form of buckets
    - You must create a bucket before you can store data on S3.
- Can specify the redundancy and encryption

### Database Services
- Relational Databases 
    - MySQL, Oracle, SQL Server 
- Non-relational Databases 
    - Proprietary solutions or MongoDB maybe
    - NoSQL
- Replication
- Scalability
- Preformance
    - Guaranteed input/output
- Security 
    - Access control

### Amazon RDS
- Automate deployment of DB to use with other services
- Easy management view

### Amazon DynamoDB
- NoSQL
- Model
    - DynamoDB data model includes tables, items and attributes
    - A table is a collection of items and each item is a collection of attributes
- Fully managed
    - Spread over a collection of servers
    - No deployment, monitoring and refinement


### Other ideas
- Can use variety of runtimes
    - Containers
- Simple Queue service
    - FIFO messaging system with HA
    - Messaging between services
- Email service
    - Send emails outbound only
    - Delivery for things like marketing
- Media Service
    - Convert to web native formats

### Notifications Services
- Push notifications to mobile devices / users
- Publish-subscribe model
    - Consumers say they want to be notified of event (types?)
    - Event occurs and producer sends to all subscribed consumers
- Amazon Simple Notification Service
    - SQS (Query service)
    - SMS
    - HTTP/S

### Content Deliver Services
- Streaming
    - Download when needed
- Content Delivery network
    - distributed system






### Subjects and Objections

- Object is an entity that contains or receiving information (passive)
- Subject is an entity requests access to the information of an object (active)
    - Causes the movement or modification of info
    - Is an object itself

- Oversimplified idea
    - Program acts as you as a subject
    - Program gains authentication
    - Person is input source
    - Programs assume your identity

### Confidentiality 
- Authenticated subject should only be able to read data
- Acheived by access control and crypto
- Secrecy 
    - Depends of time of usefulness
    - Where/How stored on disk or in-transit
    - Value -> impact this data has

### Integrity
- Inforamtion is modified only in a controlled manner
- Example Policies
    - Only authenticated subjects
    - Consistent and meaningful fashion
    - Never modified
    - By a given method

### Availability
- Data and Services
- Existence of object or service to be used
- Enough of object or service to meet demands
    - Many VMs for web services
- Sufficient speed of access/response
- Faire use of shared object or service

### Balancing CIA
- Situational
    - Need to be able to explain
    - Military likes the Confidentiality
- Data dependent
    - Web pages need I & A

### Authenticity
- Confirmation of identity of some object
    - User auth
    - Document authorship
    - Group membership

- VS Integrity
    - Integrity means an object doesn't change
    - Object is authentic if it is the same object it claims to be
    - So object can be changed and and keep identity
    - Lacking integrity but having authenticity

### Non-repudiation
- Subject can not later falsely deny an action
- Can't back out on an agreement
- Signing for mail, gambling, contracts, timestamp

### Access Control
- Who can do what to whom
- For each subject and object pair, specifies operations the subject can preform on the object
- Access Matrix - Columns of objects by rows of subjects
- Firewall
- Ad Hoc permission bits 
- File System
- Encryption
- ACL - Column of access matrix
- Capabilities - Row of access matrix
    - tokens / cookies - Transferable
- Access matrix flaw - how do we modify the access matrix?
    - Need another access matrix

- VS Authentication 
    - Access control relies of authentication
        - Real Subject?
        - Real Object?
            - Ctrl+Alt+Del - confirm using OS login mechanism as is an interupt
        - Are they part of a group?
    - Separate mechanisms

### Auditability
- Store logs of transactions
- Later detect mistakes, wrongdoings, recover
- Othen Neglected or Ad Hoc
- Example - web server
    - Requests - Get, put, post
    - IP of who sent request
    - Logs of web pages sent back/accessed
    - Timestamp

### Privacy
- Avoid monitoring, not just explicit content but behaviors as well
    - More that confidential
    - Behavior tracking
- Approach: Pseudonymous communication
- Tracking / Street cameras can track anywhere anyone is in a city
- Military hi res drones that fly above metropolitan areas
- Spot criminal and track through entire city

### Content Control / Proection AKA Digital Rights Management
- Protecting copyrights on content
    - Software, text, music, images ...
- Prevention, deterrence, and detection
- Trusted hardware/software to access content
- Watermarking
- Apple store




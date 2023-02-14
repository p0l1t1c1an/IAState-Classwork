
# Enumeration

### Looking for
- Shared resources
- Usernames
- Apps and banners
    - Client talks first for enum (HTTP)
    - Server talks first for scanning
- SNMP / DNS
    - Target when inside
    - Gain more targets
- Machine names, routing tables
- AD or other authentication systems
- Enumeration is very operating system dependent


### Windows
- Windows resource kit
    - Software that allows one to manage windows domains
    - Administration software can also be used for hacking
- Windows can run
    - DHCP
        - No different then other DHCP implementation
    - DNS
        - Watch out for zone transfers
- Brute force attacks on shares


### Null Session and NetBios (Sharing in windows)
- Unauthenticated connection
    - Possible to obtain information on shares and users
    - Network information
- Block windows sharing port at firewall 
- Netbios
    - Protocol that supports printing and messaging in windows environment
    - Net view allows a user to gather information about a domain
    - Nbtstat queries individual machines for DNS information, very similar to nslookup
        - Returns netbios names as well as the user logged in and the mac address


### Domain Controller (AD)
- Domain Controller
    - User database
    - Keeps passwords
- DumpSec
    - Gather information from domain controller and generates information on shares and users
- Legion
    - Give it an IP range and it will find all the shares in that IP address range
    - Gives tree view of shares
    - Tries dictionary attack on the shares
    - Brute force


### SNMP
- Simple network management protocol
    - Goal is to allow queries of network elements
    - Queries routers, bridges, gateways
- Port 161
- Can have public and private information
    - Scanning with SNMP walking programs
        - Usually gives back the device type and possibly the administrator of that device
        - Shares, usernames, domain names
        - Get back windows, unix, routers, etc
    - Generally mitigated and only for routing devices
- As environments become more complicated SNMP will be relied on more and more



### Web
- This is kinda just fingerprinting from what Doug is talking about
    - More in-depth
- Open shares
    - Public Files on cloud storage like google drive
    - Only url
- Usernames / Employee list
    - Conventions for email
- Also something like connecting to a public S3 bucket 

### Unix
- NFS
    - Can query a machine to find shares
- Samba
    - Allows UNIX machines to talk to windows
    - Sharing above
- Finger
    - I doubt it is used
    - Get info on users of machine (logged in)

### Other
- Mail
- FTP
- TFTP
    - Zero authentication
    - General PXE to my knowledge
- RPC
- These are generally not allowed


### Extra (Start of Malware History)
- Data
    - Public systems
        - Software and OS
    - Private systems
    - Cloud systems
        - Usernames
- Getting in (Tools)
    - Exploit a vulnerability 
        - Metasploit
        - Custom program
    - Social Tools
        - Inject yourself into email chain (example by doug)
    - Authentication tools
    - These are external
- Once in (Tools)
    - Priviledge escalations
    - Lateral movement
    - To Attack the systems
        - What are we infecting them with or doing to them
        - This is the malware
    - Persistence
    - Can be gain access to email
        - Furthers social eng abilities 
        - To increase the above ideas


# IAState-Classwork
Backup of my notes and labs for Cybersecurity Engineering


### Classes

This is most of the classes I have taken. 
Other ones are low level digital logic and embedded systems.
I lost those classes when I was testing FreeBSD-current compiling most code and own kernel, which unsurprisingly crashed. 

#### Computer Engineering
- CprE 308 - Operating Systems: Principles and Practice
    - Low level ideas on how the OS interacts with HW and users
    - Concepts of filesystems, processes, and system security + reliability
    - Labs were writing code 
- CprE 310 - Theoretical Foundations of Computer Engineering
    - Discrete math course focusing on conceptual proofs and logic
- CprE 489 - Computer Networking and Data Communications
    - Low level networking course focusing on the physical, data link, and network layers
    - Data encodings, multilayered error detection/handling, flow control, OSPF/BGP, DHCP and NAT/subnetting 
    - Labs were socket programming and configuring Cisco switches

#### Computer Science
- CS 228 - Introduction to Data Structures
    - Some really bad code from when I was a freshman
- CS 311 - Introduction to the Design and Analysis of Algorithms
    - This extends CprE 310 and CS 228 applying proofs to algorithms
    - Graph theory and Java programs implementing shortest path algorithms
- CS 309 - Software Development Practices
    - This is the Chirrup project that can be found on my github profile
    - Intro to DevOps and Agile workflows with teams of 4 to build front and back ends 

#### Cybersecurity
- CprE 230 - Cyber Security Fundamentals
    - Sysadm course that goes over the basics of Nix OSes, simple networking, VMs, and implementing services
    - Labs were about setting up services in Linux VMs in ESXi like LDAP, DNS, and OpenVPN
    - Final Project was a scenario where a disgruntled employee set up backdoors and vulnerabilities on a machine and you have to hunt them down
- CprE 231 - Cyber Security Concepts and Tools
    - Ethical hacking course where we learn of security attacks and incident response finding attacks in a SIEM set up for us
    - Labs were going through the MITRE ATT&CK framework starting with initial access techniques and moving to priviledge escalation and lateral movement techniques
    - Final was an attack competition where students defended their own VMs and tried to exploit vulnerabilities in other students VMs, all of which were filled with backdoors. 
- CprE 234 - Legal, Professional, and Ethical Issues in Cyber Systems
    - Primary ethics course for cybersecurity that goes over many different concerns that people face in the numerous security positions 
    - I have all of the papers I have written for the course stored here
- CprE 331 - Application of Cryptographic Concepts to Cyber Security
    - Simplified view of cryptographic algorithms, PKI, Kerberos, IAM, blockchain, SAML, and attacks to these
    - Labs working within VMs set up as environments with infrastructure of these concepts and view how they function
- CprE 430 - Network Protocols and Security
    - Abstract view point of mechanisms that operate on each layer of the OSI model
    - Looking at protocols and software vulnerabilities/weaknesses in the fundament design like ARP and SMTP
    - Defensive mechanisms put in place such as firewalls, WAF, and IPS
- CprE 437 - Intro to Wireless Security
    - Wireless networks such as Cellular, WLAN, and PAN
    - Looking at security challenges and attack points of these networks
- CprE 531 - Information System Security
    - Fundemental ideas of trusted/trustworthy, entropy, and cryptographic analysis
    - The CIA triad and extended ideas like authenticity and nonrepudiation
    - Hardware security mechanisms like cpu ring levels and virtual memory
    - Encryption and KX algorithms like AES, RSA, and DH as well as hashing algorithms like SHA
    - Look at certificates, verification of signatures, and functioning of TLS 
    - Authenitcation models like RBAC, PBAC, MAC, and DAC as well as inner working of many MFA methods like biometrics and hardware tokens
- CprE 536 - Computer and Network Forensics
    - Goes over digital forensics ideas like watermarking and ip traceback
    - Analyzing hardware compenents like harddrives, ssd, and memory
    - Finding hidden information in file systems
    - Projects use FTK and Encase
- CprE 532 - Information Warfare
    - This is an offensive and defensive course looking at how attackers utilize the entirety of IT infrastructure
    - We go over attack methodologies and utilizing tools like Metasploit
    - We go over abilities to mitigate attacks and incident response techniques
    - With labs on virtual machines (this is similar to encompassing 230, 231, and 331 with more complexity)


#### Software Engineering / Cloud
- SE 422 - Cloud Computing Software Development
    - Team based project course going over cloud concepts like rapid elasticity and using AWS
    - This goes further into detail about containerization and distributed computing
    - Focuses on abstract view cloud systems for software engineer and light amounts of inner workings of cloud systems

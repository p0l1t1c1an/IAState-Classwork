
# Footprinting

### Basics
- Footprinting builds profile of target
    - People information
    - Backdoor information
    - Network
    - Analogous to casing a bank
- Any public information from anywhere
    - Physical (Dumpster diving)
    - No interaction with target

### Information 
- Internet
    - Domain name
    - Network addresses
    - Topology of target network
- Intranet
    - Obtain technology used
    - VPNs
    - Remote access
- Extranet
    - Access control that the target uses

### Steps
- Check web site 
    - Possible to download entire web site
    - Can obtain
        - Location
        - Related sites
        - Merger and acquisition
        - Contact information
        - Privacy policies
            - use policy to infer what security is in place
        - Comments in html source
        - Hosting Provider 
        - CMS

- Newsgroups
    - Look for posting from the target’s IT team
- Tools
    - FerretPro
        - Advanced searching, Newsgroups,  IRC, etc..
    - AltaVista or other search engines
        - “Link: www.issl.org”, this search will find any web page that links to issl.org
        - Used to find backdoors
        - Employees usually link back to the company they work for, build employee  list
- Public Databases
    - State and local government databases
        - SEC for board/C-suite targets
    - Gather information on partnerships or subsidiaries, looking for trust relationship to exploit
    - Wayback Machine

- DNS (Domain Name Service)
    - Registration  information
    - Use whois searches and domain query
        - Finds  organizational  information
        - Address of register
        - Admin  contact
        - DNS  server
        - NSlookup will query DNS for IP address of target
        - Domain table transfers  (usually blocked)
            - ls –d acme.net >> file
        - Tools for DNS
            - Sam Spade whois lookup and zone transfers
- Network query
    - Ask what networks belong to target’s name

- Point of contact
    - List of names
    - Social  engineering  targets
- Trace route
    - Unix program, in Nt called tracert
    - Figures out the various machines along the path from attacker to target
    - Uses time to live field 
        - The device that decreases the time to live field to zero sends a packet back to originator telling who killed the packet
    - Visual Route is a graphical trace route, shows geography
        - Graphical helpful for denial of service attack
- UDP
    - Mostly  blocked
    - Port 53 is usually open for UDP, DNS service runs on port 53


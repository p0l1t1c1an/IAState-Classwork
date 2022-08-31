
### Layers 
- Application
- Presentation
- Session
- Transport
- Network
- Data Link 
- Physical

- Top 4 done by the software / app
- Bottom 3 done by phone lines / AT&T / who ever is connecting you to internet 

### Physical 
- Transmission of the physical bits 
- Notify if the channel/medium is busy
- Ordered delivery
- 1010 to (volts, amps, light, rf, sound)
- Point to point (1 to 1)
- Or Multipoint (1 to many) - like a hub 

### Data Link
- Hide the physical medium from user/higher layer
- Reliable transmission should be error free
    - Though may occur in physical channel (noise)
    - Guarentee valid if delivered or passed up a layer
- Establish connection of data link devices
    -  Some not all
- Data link packets are called frames
- Flow control
- Layout
    - Prabmle | Frame | Frame Check Sequence


### Network
- Get data from Start to end, A to B
- IP protocol 
- Flow and Error Control
- Idea
    - A -> Gateway -> { Internet Routing } -> Gateway -> B

- From 489, this layer sets up network, OSPF and the other one, creates routing table
    - I think also BGP is part of this but was not talked about in detail in 489

### Transport
- Reliable transfer from End to End 
    - Two session layer
- TCP Protocol 
- Doesn't care about the data format or the underlying network
- Session layer requires QoS and Transport layer maintains it
- Flow and Error Control

### Session 
- Coordinates dialog between presentation layers 
- Multiplexing a single conntection
- Establishes sessions and manages its communcations
- Session - Part of application 
    - User login is start of session even though there is a constant network connection up

### Presentation
- Translation of data to useable/common formats
- Common format means that both ends only need to convert data from one format each
- Network and Security dashboard use this to get information from many services/apis

### Application 
- What the user sees 
- App process access to OSI stack
- Services for apps 
- App to App communications 
- Telnet, Ftp, Web



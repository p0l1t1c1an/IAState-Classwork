
# GSM Security


### Mobile service switching center (MSC)
- It provides the interface between MS and the fixed network
    - Switching and call routing
    - Communication with HLR and VLR
    - Communication with other MSCs
    - Controls connected BSCs 

- Network Subsystem components
    - MSC: Mobile services Switching Center
    - HLR: Home Location Register
    - VLR: Visitor Location Register
        - Reachs to HLR when user switches to new BSC/MSC  
    - AuC: Authentication Center
        - All the security (IAM)
    - EIR: Equipment Identity Register 
- Network Subsystem features
    - Telephone switching function
    - Subscriber profile
    - Mobility management


### Identification 
- Identification of Mobile Subscriber
    - International Mobile Subscriber Identity (IMSI)
        - Beyond USA
    - Temporary MSI (TMSI)
        - Minimize sending of ISMI
    - Mobile Subscriber ISDN number (MSISDN)
        - Phone number
- Identification of Mobile Equipment
    - International Mobile Station Equipment Identification (IMEI)
    - Mobile Station Roaming Number (MSRN)
        - Used for routing
        - Not phone number for routing
 

### ISMI 
- Stored in SIM, not more than 15 digits
    - 3 digits for Mobile Country Code (MCC)
    - 2 or 3 digits for Mobile Network Code (MNC)
        - It uniquely identifies the home GSM network of the mobile subscriber.
    - <= 10 digits for National Mobile Station Identity (MSIN)


### TSMI
- 32‐bit number assigned by VLR to uniquely identify a MS within a VLR’s area
- Has only local significance (not stored in HLR)
- TMSI is used in place of IMSI for security reasons
    - prevents an eavesdropper from identifying and tracking the subscriber
- A typical usage:
    - TMSI is assigned by VLR when IMSI is transmitted to AuC when the phone is first switched on
    - TMSI is used for all MS‐to‐network communication (e.g. During call initialization)
    - When the MS switches off, TMSI is stored on SIM card to be reused next time

### IMEI
- Uniquely identifies mobile equipment internationally
- IMEI numbers are registered by the Network operator and stored in Equipment Identity Register (EIR)
- Security implication
    - Stolen devices can be pinpointed
- Privacy implication
    - System can track you


### MSRN
- Temporary location‐dependent telephone number
- Structure same as that of MSISDN
- Calls are routed to MS by using MSRN
- Assigned locally by VLR after registration 
    - Sent to HLR since home network should know how to route the calls to this MS
- Normally, current MSC of the subscriber can be determined using MSRN


### LAI: Location Area Identifier
- Location Area (LA)
    - Consists of one or more cells in a network
- Based on international ISDN numbering plan
    - Country Code (CC)
    - Mobile Network Code (MNC)
    - Location Area Code (LAC) : maximum 5 decimal digits
- Broadcast regularly by the BTS
    - Each MS registers its LAI at HLR and VLR


### Cell Identifier (CI)
- Within LA, individual cells are uniquely identified with CI.
- It is maximum 16 bits
- LAI + CI = Global Cell Identity


### Roaming 
- Changing from one network to another
- More technically
    - Ability to use services outside the coverage of the home network in a visiting network
    - Not necessarily in another country
- From Security point of view
    - It is an authentication (actually AAA) problem
    - Challenge:
        - Visiting network does not know the mobile user
        - Authenticate and authorize to provide services
    - Trust to send IMSI?


### Security
- GSM is not all‐wireless network
- MS to Base Station is a vulnerable wireless link
- The rest is either wired or fixed wireless link that could easily be secured
- But not secured


- Main security requirement is subscriber authentication 
    - For the sake of billing (revenue for the network operator)
- long‐term secret key shared between the subscriber and the home network operator
    - GSM supports roaming without revealing long‐term key to the visited networks


- Other security services provided by GSM
    - Confidentiality of communications and signaling over the wireless interface
        - encryption key shared between the subscriber and the visited network is established 
        - with the help of the home network as part of the subscriber authentication protocol
    - protection of the subscriber’s identity from eavesdroppers on the wireless interface
    - usage of short‐term temporary identifiers (TMSI)


### SIM
- Contains all data specific to the end user which have to be used by the MS
    - IMSI (permanent user’s identity)
    - TMSI
    - K i :User’s secret key 
    - K c: Ciphering key 
    - List of preferred operators
    - Supplementary service data (addressbook, last short messages received,...



# 4G Attacks

### Renegotiation Attacks
- Threat
    - Rouge base station downgrades to GSM
    - Can decrypt weak algorithms 
- Mitigation
    - UE sets use LTE Only


### Device and Identity Tracking
- Threat 
    - IMEI and IMSI can be intercepted to track phone/user
    - Rouge base station force connect to it
- Mitigation
    - UEs should use temporary identity
    - IMSI-catcher-catcher

### Call Interception
- Threat
    - Renegotiation may allow MitM
    - Then can listen to calls
- Mitigation
    - Ciphering indicator feature
    - Alerts user if connections are unencrypted

### Jamming UE Radio Interface
- Threat 
    - Decreases signal to noise ratio
    - Prevents emergency calls
- Mitigation
    - None, its a physical attack over air
    - Track down the noise and destroy transmitter

### Attacks Against the Secret Key (K)
- Threat
    - Attackers may be able to stak K from the carriers HSS/AuC
    - Or from UICC manufacturer
- Mitigations
    - From the companies
        - Improve UICC physical security
        - Carrier network security


### Physical Base Station Attacks
- Threat 
    - Radio equipment operate at base station could be physically tampered
- Mitigation
    - Physical security measures at base station

### Availability Attacks on eNodeB & Core
- Threat
    - Many simuiltaneous requests may prevent eNodeBs and core network from functioning
- Mitigation
    - None
    - My thought is maybe some way to drop messages (learned to be bad)





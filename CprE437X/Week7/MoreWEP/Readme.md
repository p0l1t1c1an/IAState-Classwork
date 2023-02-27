
# More WEP Issues


### Confidentiality
- IV too small and is commonly reused (only 2^24)
    - Some just set to zero and increment
    - Some are random per packet
- XOR means IV reuse gives Plaintext xored for 2 messages
    - Can reveal some information
- Weak RC4 keys 
    - Some seeds create weak keys where beginning of output not random
    - First bytes reveal information
    - Should remove first 256 bytes
    - IV increment means it will occur eventually
- Bottomline
    - Can be broken by capturing one with weak key
    - [FMS attack](https://en.wikipedia.org/wiki/Fluhrer,_Mantin_and_Shamir_attack)
    - Need tens of thousands messages (not many)
- Wepcrack
- Airsnort
    - Completely automated

### WEP lessons
- Engineering security protocols is difficult
    - Combine (for the time) strong pieces incorrectly
        - Reveal information
        - Stream ciphers with challenge response
        - OR message digest linear with encrypting 
- Listening to an expert in the design phase pays out 
    - Hard to fix design flaws
    - Experts will know shortcomings
- As secure as wired
    - No security

### Safeguards
- Treat WLANs that use WEP as untrusted.• Similar to Internet.
- Invest on personal and end to end security
    - Antivrus, personal firewalls and IDS
    - Use SSL/TLS to send critical data
- Firewall between WLAN and Backbone.
- Intrusion Detection 
    - WLAN / Backbone junction.
- Ideally locate access points
    - In centre of buildings.
    - Try to avoid access points
    - By windows
    - On external walls
    - Line of sight to outside


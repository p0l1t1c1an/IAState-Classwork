
# Attack Scenarios


## Wireless Security Example (WEP Network)


### Step 1: Find networks
- War driving
- Use netstumbler, locates a strong signal on WLAN
    - Can map APs location
- NIC must allow monitor mode (read raw 802.11 packets)


### Step 2: Choose Network
- Pick your taked
- Netstumbler tells you if enc
- Ethereal to look for additional info

### Step 3: Analyzing the network
- Netstumbler gets SSID
- Find all APs
- Auth method and WEP, WPA, or WPA2/3

### Step 4: Crack WEP Key
- Monitor mode
- Airodump
- Capture packets
- Aircrack after hours of packets
    - Get key

### Step 5: Sniff Network
- Can connect to network 
- See what peers are connecting to 
- Literal see everything that is not encrypted


## Security is hard
- Users share medium
- Roaming
- APs are everywhere


## WPA attack
- Wi-Fi Protected Setup feature
    - Allows users to connect to WPS network easily
    - Auth is just 8 digit pin
        - Easy to bruteforce
        - Can recover key from pin
    - Kali wash command
- Without WPS
    - Capturing packets isn't useful to get key info 
    - Need to capture handshake packets
    - Wordlist attack against handshake
        - Bruteforce the PMK
        - Can then get information to generate session key (PTK)


### Summary of WLAN Security
- WEP sucks
- Use 802.11i
    - Buitl upon 802.1x 
    - EAP Authentication (RADIUS plus challenge response, TLS, or soemthing else)
    - TKIP uses old enc
    - AES-CCMP 
        - Still good
- WPA3 
    - Protects from weak passwords and has best crypto

### Wi-Fi Direct
- Allow wifi device to talk without AP
- Embeds software AP into any device supporting direct
- Needs WPS support
- Can connect to Direct host 
    - Send files
    - Syching
    - Tethering



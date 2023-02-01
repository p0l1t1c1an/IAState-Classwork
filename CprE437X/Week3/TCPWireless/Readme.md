
# TCP


### Impact of transmission errors
- Wireless channel may have bursty random errors
- Burst errors may cause a timeout
- TCP cannot distinguish between packet losses due to congestion and transmission errors
- Throughput suffers


### Split connection approach
•End‐to‐end TCP connection is broken into one connection on the wired part of route and one over wireless part of the route
•Connection between wireless host MH and fixed host FH goes through base station BS
- Create two connections (sessions)
    - Know if issue of wireless or wired congestion


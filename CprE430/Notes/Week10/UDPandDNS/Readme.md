

# UDP and DNS

### UDP Header

![udp](./udp.png)

- Just a wrapper to give ports to IP

### Attacks
- Header & Protocol: None since there is no protocol and very simple header
- Authentication: same as TCP
- Traffic: typically not a problem 
    - Sniffing is a potential problem
        - most UDP protocols don’t try to hide data
    - Flooding is hard with UDP
- Mitigation: Most organizations block all UDP except port 53 (DNS)





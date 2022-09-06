
### Open Media Forensics
- CTF to look into


### Continued Case Example
- Password guessing to get into the account brucer
- Assuming Brucer did not do it
- Login from 31.27,.11.7
    - From firewall logs and telnet
    - Trusting the time
    - Firewall logs have time and can trust that it was not modified
    - Can't trust the IP as the attackers could be some elses they are using
    - Chain of IPs
- ABC Ventures own the IP 
    - rc.local was changed
    - Modified boot system
    - chmod 0 /var/log/\* / No more logs can be written
    - Loaded shadow, passwd, ifconfig, ps aux to /tmp/admin (all privileges 777)
    - Insert admin bsmith
    - Send out the admin file to hacker@fantasy.com
    - Del file
    - Turned logs back on
    - Added fack commands to bash history
- Who owns the emails
    - Search warrant (freemail.com)
    - Confirmed source IP of system accessed emails (badboy and hacker)
    - Jeff wylde is badboy
    - Carlos Fernandez is hacker
    - Phone records of Fernandez
    - Times matched in phone with firewall logs
- Fernandez was former security employee
- Follow the procedures, dammit


### Criminalistics / Forensic Science History
- Fingerprints 
    - Affected by weather and actually hard to analyze
    - Not actually unique, I remember murder trial from CJST course
        - Guy in NW US got arrest for European murder
- Blood groups
- Firearms and bullet comparison
    - Try matching by eye
    - 3d recreation ?
- Document examination 
    - How people write
    - Who wrote something
- Something is always left at crime scene the was not present when the person arrived
- RCFL
    - Regional Computer Forensics Labs



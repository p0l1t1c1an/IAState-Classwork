
# Kerberos

### Goals of Kerberos
- Not cleartext password sent or stored
- Password in memory as short as possible
- Limit compromise
- Finite time to certificate
- Transparent to user

### Principle (and terms)
- User, client program or server program
- Primary Name
- Instance (admin or root on local machine)
- Realm is essentially the domain
- Principle Name
    - Jones@IASTATE.EDU
- Server
    - Fred.root@IASTATE.EDU
- App
    - Rcmd.sarek@IASTATE.EDU

### Server
- Authentication server
    - data base of users and passwords
    - Authentication informatin like groups/permissions
- Ticket Granting Server
    - Hands out tickets 
    - Authenticates user for access to service or resource

### Authentication Process
- Client sends request to AS
    - Username:tgsname:ts
- AS gets keys for client and TGS
- Sends message to client
    - $E_{kc}$ {kc,TGS || ${ID}_{TGS}$ || time stamp || Life Time || Ticket}
    - Ticket is encrypted in TGS key so client does not know it
    - Ticket : $E_{ktgs}$ {Kc,TGS || client ID|| address of client || timestamp || lifetime} 
    - Session key is kc,TGS
- Send to TGS
    - Ticket, authenticator, and service name/ID
    - Authenticator: $E_{kc,TGS}$ {ID of service || IDc || time stamp} 
- Get back
    - Ticket (for s), $E_{kc,TGS}$ {kc,s || IDs || nonce}
    - Now have ticket to communicate with server 
    - Only server can decrypt ticket
- Send message with {ticket_s || Auth_s}
    - Authenticator for server is: $E_{kc,s}$ {IDc || time stamp || SEQ #, || Subkey?}

### Version Five
-  Just have more advanced options
    - Time
        - Start and end
        - Random number to stop replay
        - Renew time
    - Ticket Contains
        - Option field
        - Realm info
        - Time 
        - Standard Identifier
- Still open to dictionary attack


### Active Directory
- Uses Kerberos to obtain two tickets
    - Ticket Granting  Ticket (TGT)
    - Session Ticket (used to access resources)
- Uses X.500 style directory service
    - pass out information about users and applications after authentication


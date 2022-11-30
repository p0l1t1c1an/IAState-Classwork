
# Remote Access



### Telnet


![telnet](./telnet.png)

![telnet_time](./telnet_time.png)

### Rlogin
- Remote login (rlogin)
- Similar to telnet, but much simpler
- Designed for unix to unix communication
- Possible for hosts to login without a password
- Uses port 513
- Sequence:
```
– Client  sends:    \0
                    local login name
                    \0
                    server login name
                    \0
                    terminal type
                    \0

– Server  sends:    \0
```

![rlogin](./rlogin.png)

![rlogin_time](./rlogin_time.png)



### X Windows
- The user sits on the server side of X windows
- Usually SSH into client and start X window client 
- X windows then starts and the client authenticates  to the X windows server
- X windows sends information in clear text


![xwindows](./xwindows.png)






# TCP

### Service 
- Reliable end to end connection for app layer
- Even over unreliable IP layer
- Buffering and ordering
- Multiplexing
    - Process within a host uses a port 
    - Port plus IP addr is a socket
        - Unique
    - Connection between sockets

### Connection Management
- Establishment
    - Setup a connection
    - Only one connection for a pair of sockets
    - Can have multiple different sockets to the same socket
    - Both agree
- Maintenance
    - Exchange of data
    - Support data transport
    - Keep connection established
- Termination 
    - Abrupt or graceful
    - data lose vs waiting for all data to be recieved

### Data Transport 
- Full duplex
- Timely
- Ordered
- Labeled
- Flow and error control 


### TCP Stream
- Treat recieving of data from TCP as stream of data
- Breaks up into packets
- Sends, recieves and reorders packets
- Puts data packet into stream of data

![tcp_stream](./tcp_stream.png)

### Special TCP
- Data Stream Push
    - Used to force the delivery of all data waiting to be sent
- Urgent Data Signaling
    - Provides a means of informing the destination TCP user that urgent data is in the incoming data stream
- Error Reporting
    - TCP will report service failure stemming from catastrophic conditions

### TCP Handshake

![handshake](./tcp_handshake.png)
- Send different seq numbers

### Data Transfer
- Seq number is the number of bytes
- Flow control by credit allocation 
    - Can only send so much data unacknowledged by recv

![data](./tcp_data.png)

### Connection Termination

![term](./tcp_term.png)


### Header Format 

![header](./tcp_header.png)


![flags](./tcp_flags.png)


- Flag Functions
    - URG / Packet contains urgent data
    - ACK / Acknowledgment number is valid
    - PSH / Data should be pushed to the application
    - RST / Reset Packet
    - SYN / Synchronize packet
    - FIN / Finish packet
















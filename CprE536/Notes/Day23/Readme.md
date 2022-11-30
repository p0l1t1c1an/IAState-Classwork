

# P2P Forensics, Internet Fraud, Organized Crimes


### File Sharing
- Sharing content between homes
    - which is fine until
- Napster / Torrenting
    - Illegal sharing of music / movies
    - Pirating of IP protected content
- People share processor/storage to napster to download files they want
    - Join P2P network
    - Help with sharing of content
    - Indexing server (centralized)
- Freenet 
    - decentralized
    - DFS-like search
- How to determine uploader versus downloader

### CAN
- Each node knows neighbors in d-space
- Forward query to the neighbor that is closest to the query id
- Can route around some failures

### Chord 
- Forms ring 
- Sends to next neighbor
- May know that neighbor doesn't have the file
- Sends to next in ring (next is wierd and don't understand)

### BitTorrent
- Breaks up file into multiple pieces
- Download from multiple sources at once


### Evidence
- Determine first uploader
- Starts off with many connections / leech nodes  
- Have to get earliy before hill in graph



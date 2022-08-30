
### Block Devices and Memeory
- Hard drive, ssd, flashdrive, sd card, CD/DVD
- Same block size 
    - Which is max page size
- Page faults will bring more data that just the page into memory
    - Getting more info from memory dumps than user would have requested
    - Bypass some info on encrypted drives

### FSCK and File systems
- Fix file system
- Normally done at boot on Unix systems
- Will change the state of a turned of machine's hard drive
    - Correcting issues and potentially removing / fixing info we will want from a drive
    - Don't want to turn on the devices
- Don't need to look at data to see if useful
    - Timestamps will give what is relevant
    - Most likely past 30 days or so (depends)

### Hardware 
- LBA - logical block addressing
- RAID - redundant array of independent disks
- Master file Table
    - Shows files / partitions
    - Hide partitions that don't show up in the records
    - 3 partitions but table does not show 1
    - Can store "hidden data" in that partition where you need to know where it exists to use it

### Networking
- OSI
    - Only things alive are LDAP and x500 certs

### Criminology
- Who has handled evidence and where it has been
    - Chain of custody

### Memory 
- Deduplicating data - creating bit by bit copy
    - imaging
- ROM
    - Nonvolatile Memory - read only
- Volatile memory 
    - Some is recoverable after poweroff
    - No guarentee of state after poweroff, not that it will be gone

### Files
- MAC Times by file attributes
    - Modified time
    - Access time
    - Creation time
    - Useful info to find relevant data stored 
- SHA
    - Hash to keep integrity of files and data
- File Signature
    - Header/footer info that verifies the format of the file


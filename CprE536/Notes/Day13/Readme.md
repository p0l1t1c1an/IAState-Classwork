
### SSD Challenges
- Wear leveling will fragment data
    - unpredicatable
- Can't use the same tricks as HDDs
    - Trimming
- Deleteing
    - Only deletes in FS 
    - rive block stays allocated
    - Trim command so microcontroller also frees it
- Hard to read off chips directly
    - Generally need to go through microcontroller
        - But then you need to trust it
    - Hard to understand 

### Findings for Forensics
- Periodic wiping on dealloced space
    - Garbage collection
- Reliably erasing data
    - some data still there due to over provisioning
- Trim command: added to ATA8 spec
    - When deleted,
        - Copies valid pages to mem then deletes block
        - Block is rewritten with valid data and can happen immediately after delete instead of when block needed
        - Drive controller software decides when to erase the blocks it knows are free
- Can't confirm the data is cleaned from device
- Seemingly impossible to recover trimmed delete
    - Not always though
- High usage drives have greater chance of data retention
- Formatted drives using defaults result in partial data loss for all OS's 
    - full loss with Windows 7 with trim
- Manufacturer variance in GC seems to change the recoverability of files on the drive
- Single click can tell the drive to trim itself destroying evidence (Windows)
    - Just moving mouse as well

### Newer Ideas with SSDs
- Self encryption
    - Encryption key in SSD 
    - Encrypts data in the device
    - How can we get the SSD's keys
- GC and Trim
    - SSDs must avoid writing repeatedly in the same location
- Over-provisioning
    - Give them extra grooming space for data depending on the write needs
    - Stated capacity is smaller than how much their actually is 
    - Backups for dead cells
    - Increases the lifetime with stated capacity

# File System Analysis

### Overview / Misc 
- Update time table (access and modify)
    - Touch command
        - Empty if file does not exist
    - Open the file (just access)
    - Write to file (just modify)
- Inode structure 
    - Manually modify to say a block exists or not
    - Time tables of file
    - Metadata on a file 
        - Which blocks it is using
- Recycle bin
    - Doesn't actually delete 
    - Put in hidden directory
    - Still recoverable
- File Systems
    - FAT / NTFS
    - Ext2/3/4
    - UnixFS
    - HFS / APFS
- Windows Registry
    - Could have saved encryption key

### Forensic Mindset
- Examine data in a disk/partition

### What is a FS?
- Files and Directories
- File System 
    - Structure
    - Allocation Strategies
        - Consecutive and fragmentation
    - Slack Space
        - Allocated for file but not being used
        - Block based allocation for a file
        - May allocate in super blocks
            - Multiple at once
        - Not all have to get used 
        - Space at end
- Wiping
    - Write disk all zeros to storage blocks
    - Delink inode


### Investigation Chronology
- Time attributes (Modified, Access, Created)
    - Timeline of the incident
- mtine, atime, ctime
- Improperly access or searching a system can alter the time lines destroying evidence
    - Reading a file or running a program changes the atime
    - mtimes are changed by modifying file's contents
- Some systems have dtimes (deleted) dtime used as an aprox of when deleted

### One Off
- What happens if you don't call close() before exiting after a write()
    - No guarentee of sync to FS 
    - Write may not go through




### Ransomware Issues FBI
- FBI not giving the key for many ransomware attacks
- Encryption keys needed to tested
- Can't disclose the information of ongoing case
- Information is closed and can't help grow information
- Open source information is the opposite
- Lets us learn, grow, share resources of common intelligence


### Support Scams
- Trying to do transfer to other people 
- Give them information to make transfer
- Have you verify transfer
- Tricking people



# Digital Forensics Process


### Definitions
- Digital Evidence == info stored / transmitted in binary form that relied up in court
- Original DE == Physical items and data objects, which are associated with those items at seizure
- Duplicate DE == Accurate reproduction of all data objects contained on the original item
    - imaged 
- Copy == accurate reproduction of info contained in data objects independent of original physical item
- Chain of custody == means of accountability, shows who obtained evidence, when, where, who secureed, who had control and possession
- Rules of evidence == eveidence must be competent, relevant, and material to issue



### Categories of Evidence
- Admissibility of evidence based on factual foundation
- Best
    - Primary in trial
    - Documentation
- Secondary
    - Not viewed as reliable in providing guilty/inno
    - Oral
- Direct
    - Proves a fact by itself
    - Eye witness
- Conclusive 
    - Irrefutable and connot be contradicted
- Circumstantial 
    - Proves and intermediate fact to deduce or assume another fact
- Corroborative 
    - Supporting to prove idea / point
- Opinion 
    - Witness testimony
    - Witness testity to only facts on the issue and not opinion of fact
- Hearsay 
    - Oral or written that is secondhand
    - No firsthand experience
    - Saying someone told you
    - Computer records fall outside hearsay when
        - Relates to business 
        - Automated data generation


### Chain of custody
- Protects integrity
- Effective process of ducmenting journey of evidence
- Answers
    - Who collected it?
    - How and where?
    - Who took possession?
    - How stored and protected?
    - Who took out of storage and why?


### Seizures (Read on own)
- Part 1 plan the search
    - Plan out
        - Type of case
        - Type of computer
        - OS
        - Savviness of end user
    - Primary evidence person should be made
        - Responsible preparing plan for documenting, preservin, and maintaining integrity of evidence

- Part 2



# Disk Imaging Basics

### Misc Start off
- SSD don't last long
    - limited number of writes
- Harddrives last forever in storage
    - Can keep information a long time 
    - Can reexamine evidence 
    - Prove in future innocence
    - Prevent false data


### Disk Drives
- Floppy Disk
    - Single sided platter
    - Disk drive to rotate disk
    - Dual sided could hold info on both sides of disk
- Modern Disk Structure
    - Hard disk drives are made a stack of disk
    - Each with 2 surfaces
    - SCSI
    - Jumper pins for more controls
    - Ribbon cable to logical board
    - Each platter has read/write head
- Forensics need to fix mechanical issues of drive
    - Get info out of drive
- Breaks of cyclinders in sectors and into blocks
- Track 0 is outermoster ring
    - Sector is countinuous line of track
    - Sectors smallest physical unit
    - 512 bytes
- CHS
    - Cylinder / Head / Sector

### Partitioning 
- Breaking disks into smaller portions
- Multiple Operating systems on different partitions
- Logically separating data on disk
- Change partition table to hide partition
    - Missing empty space in partition table is being used
    - The pointers are no longer existing
    - Can also change partition type code

### Low Level Formatting
- First sector reservered
- Organized first sectors of first track 
- Created MBR, mater boot record, in first sector
    - Stores the partition table
- Master partition table 
    - Types of entries 
    - Primary
        - Allows 4
        - Logical drive
    - Extended Partition
        - Contains on or more logical drives
        - Each logical drive defined by own partition table 
- Entry Codes tell us type of info/OS being stored




### Misc Init
- Wired video of hackers in TV
    - ffmpeg can extract video frames that are not corrupt
- Video decoding missing frames
    - If frame in between corrupted we can generate


# Continue Disk Imaging

### Boot Process
- Will change system so cant boot machine that is evidence
    - States change like logs and fsck
- User device at crime scene
    - Can't trust anything with device
    - Commands and anything could be changed on the device
- Checks IO devices and other peripherals for working
- Then boots OS
- OS checks boot records and partition tables

### Unix
- Self test code
- Probes bus for boot device
- Reads boot program
- Reads kernel and passes control
- Kernel ids devices and configs
- Init system
- Can be in single user mode
- Startup scripts
- Then multiuser system


- Superuser can modify these steps past kernel
    - Modify system with startup to make unusable for forensics

### Kernel
- Two stage loading
- Small program read to memory from default device
- Loads kernel to fixed size memory
- Kernel probes devices during config and initializes devices

### Unix bootstrapping
- System processes
    - Kernel finds root, swap, and dump devices
    - Starts programs to schedule processeses, manage physical and virtual memory, and init process
- Sched
    -  Real time scheduler
- Swapper
    - Manages physical memory by moving process from physical mem to swap when needed
- Page daemon 
    - Various memoruy handlers run at process 2
- Init
    - last step starts init process
- Single user
    - "root" user access for maintenence without full boot happeneding
    - only root partition mounted
- Startup 
    - shell scripts that init spawns\
    - Different OS means different organization
    - BSD are /etc/rc dot something
    - set of processes defined in /etc/inittab / each line is action to take

### Controlled Boot Environment
- Somethings change image at boot
- Create controlled boot disk
- Custom media to boot to preform forensics on the device


- DOS
- System files
    - IO.Sys
    - can change file systems


### Boot Issues
- Poweron/BIOS could have password
- Then forensics cannot boot computer w/o passwd
- Hardware vendor may have way to bypass
- Could have owner unlock it
    - But they don't have to

### Disk Write Blocker
- Prevent data being written to suspect drive
- Ensure integrity of suspect drive
    - Hash of the image
    - Compare copy you get versus original hash
- SW or HW
- Examination process cannot modify image
    - No longer admissible


### SW Write Blocker
- Changes the normal operation of the interrupt 0x13
    - Which is read and write operations
- Installs new interrupt 0x13
- Application program inits IO operations with 0x13
- Replacement intercepts IO
    - The new interrupt can now not handle writes preventing modifying the device
    - Firewall, allow read and block write
- If blocked, returns to app without going to BIOS I/O
- Allowed does go to BIOS I/O
- Results returned
    - A block could return fail or success depending on the SWB
- Bypassable
- Trusting the software and OS of forensic machine

### HW Write Blocker
- Device connected between the computer and storage device
- Monitors and filters activitiy / data between computer and storage
- Independent of client machine trying to read storage
- Just trusting hardware device


### SSD 
- Operate independently for read and write operation
    - Flushing is what I think of
- These can remove information
- They are small computers themselves 

### Data Acquisition
- Get data at lowest layer
- Save every byte that may contain evidence
- Dead vs Live
    - Dead == Copy without assistance of suspect OS
        - Boot from trusted CD
    - Live == Copy while suspect OS is running
        - Risks
        - Tursting the suspect themselves
        - Don't know what could have been done
        - Can't turn off machine because we don't know what it will do
- Error Handling
    - Generally Accepted behavior for handling a bad sector
        - log addreess and write 0s for data

- Steps 
    - Read from Source
    - Write to forensic devices destination

- Issues
    - How to:
        - Access Data
        - Handle Errors
        - Reduce Risk of writing suspect drive


### Read data from source
- Direct Access or BIOS Access
- Direct
    - OS or toolkit accesses device directly
- BIOS 
    - Go through BIOS systems
    - Go through API like open, read, write
    - BIOS uses interrupt 0x13
- Can get different sizes
    - I think BIOS would return at end of data


### Write Data
- File, Hard disk, CD/DVD, Cloud
- File format 
    - Raw image
    - Embedded: data plus meta data like hash and time
    - Raw image and separate file for meta data

### Removable Media
- Have partitions
- Floppy is FAT12 with no table
    - treated as 1 partition
- USB Storage tokens (like hardware token)
    - No partitions but can have them
- Flash cards
    - Have partition table
    - Use FAT
- CD-ROM
    - ISO 9660 Format
    - Can be written once
    - Some allow more 
    - create sessions with small portion of disk
        - Could view all sessions?


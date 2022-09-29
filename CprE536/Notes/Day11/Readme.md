
# Memory Imaging

### Useful Memory contents
- Worms existing only in memory
    - Code-Red and SQL slammer worm
    - MS IIS bufferoverflow and SQL server 2000
    - Gen list of IPs to probe
    - Code red defaces webpage
- Produce a lot of traffic
    - May only exist in memory
- File/Socket descriptors

### Monitor Memory
- Only reliable source of current state is memory
- Endless data
    - Running and some killed processes
    - Encryption keys and decrypted data
    - Network sockets and data
    - OS accounting
    - User input (key strokes)
    - Secreen captures and grpahics

### Physical Memory device
- Unix is /dev/mem and /dev/kmem
- Windows 2000 and XP \\.\PhysicalMemory object
- Use dd to read

- Disadvantage
    - Tust host system
    - Modify system by creating process on system trying to analyze

### Sparc OpenBoot
- Custom Key command to suspend system
- Sync command dumps memory and registers swap space
- Savecore command copies mem to files
- Disadvantage
    - Overwrites swap 
    - Reboot system


### PsuedoFile System
- Unix, /proc is mounted and has info about kernel and running processes
- Can analyze which ones are needed
- Can easily get what is only needed

- Disadvantage
    - Identify suspect process

### Virtual Machines and Hibernate
- Suspend and save memory
- No special processes are run
- Disadvantage
    - Impact system preformance

- Hibernate
    - Power management disable process, hdd, .. and save mem to disk
    - Not clear if exact content or all memory is saved
    - My thought is it might be compressed in some way


### Limitations of Software procedures
- Trusting the OS / kernel and utils on machine of suspect
- Runs new process
    - Requires kernel mem to execute
    - Could overwrite eveidence
    - Could write to swap space

### PCI System
- Northbridge 
    - Host/PCI bridge
- Southbridge
    - PCI to ISA
    - Interrupt controller
    - IDE controller
    - USB controller
    - DMA controller
- DMA 
    - Direct memory to system memory without use of CPU


### PCI acquistion Card
- Own processor
- Used to communicate with northbridge through PCI bus to get memory access
- Writes to external connection


### Imaging procedure
- Acq card is powered 
- When external switch accessed
    - streams memory to external device

### Testing
- Compare to dd command
- Only difference is dd process memory

### Hardware Issues
- Needs plug in card while power is 
- Can't be done as we need to power off to do so


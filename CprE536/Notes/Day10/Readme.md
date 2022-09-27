

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


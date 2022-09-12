
### Memory Protection
- Addressing Limits
    - 32 address
    - 2^32 bytes possible 
    - 4 GiB possible for CPU to handle
    - IBM computer only 64KiB handle in reg
    - could have 640KiB (used more registers and segmented)
    - Run out and annoying to use

- Address Space
    - Large block set aside for the kernel
    - Quarter address space given to kernel (First two bits high, 1Gib)
    - Kernel memory usual at the top
    - Userland at the bottom
    - Used for the hardware detect what is from/to kernel address
    - Kernel vulns or bad syscall - change kernel code

- Virtual Memory
    - Physical Memory
        - DIMMS on motherboard
        - Hardware
        - Mapped from low, 0, to high, n,
    - Memory Management Unit
        - Reads and writes physical address
        - "Between" CPU and Physical memory
    - 2 identical process
        - variable i in both are given same virtual address
        - Loader gives this (exec and fork)
        - Both link to separate physical memory
    - Virtual Memory Table
        - Create one for each process
        - When process 1 is executing register points to proc 1 table
        - Reading 10 by CPU tells MMU to look up 10 in proc 1 VMT
        - Points to physical addr and passes it to MMU
        - Proc 2 loaded up changes table address
    - Shared memory 
        - Both tables get same VM to PM 
        - Syscall memmap

- 



# Exam Review

### TCB
- login is part of TCB
- Compiler not in TCB
    - Not part of what is shipped with system
- Modified is part of TCB though


### Non-repudiation
- can't refute action


### Ring Levels 
- 3 rings
- Order
    - Hardware
        - Hypervisor (0)
            - vmA == Linux (1)
                - AppA (2)
            - vmB == BSD (1)
                - AppB (2)


- WriteSock (q1)
    - Ring 2 goes to Ring 1 (Kernel Handler)
        - Syscall
    - Ring 1 goes to Ring 0 (Hypervisor Handler)
        - Hypervisor / Driver interrupt

- Data Transfer (q2)
    - Hypervisor holding data
    - Linux gets to ring 0 and writes data
    - BSD gets to ring 0 and reads
    - Both jump back

- ReadSock (q3)
    - Reading in from hypervisor
    - Waiting
    - Copy memory from hypervisor to usable in app


- Page fault
    - Read/write to what you aren't allow
    - Handled as interrupt by hypervisor




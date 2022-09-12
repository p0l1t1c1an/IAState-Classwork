

### A Litte before 
- Stats on small groups reveal detail
- Professor in small county
- Can pinpoint who that is

### Computer
- CPU w/ cache
- Memory
    - Volatile / RAM
    - Nonvolatile / HDD or SSD
- Peripherals
    - Network
    - USB
    - Etc
- Bus 
    - Memory Bus
        - Northbridge controller
    - Peripheral Bus
        - Southbridge controller


### CPU
- Cache memory
- ALU
    - Math
- Registers
    - Program Counter - current instruction
- Inst
    - jump / where to go next
    - Arithmetic
    - Compare 
    - memory access / mov / rw memory 
- No model of virtual memory in this model
![bad_design](./cpu_model_insecure)


- Wall instruction 
    - Privilege
    - Only OS should do it
    - Not user program
- OS control
    - Peripherals
    - How to protect memory
    - MMU
    - This this the wall 


### Rings Protection
- Prevent unprivileged processes from executing privileged instructions
- Kernel is privileged
    - So is hypervisor
- Ring Idea: Register / 4 levels or rings 
    - 4 == User Code
    - 3 == Device Drivers (Southbridge)
    - 2 == Device Drivers (Northbridge)
    - 1 == Kernel / Full privilege
- Mode CPU is running in by register value
- Setting Ring Instruction
    - Can't set ring to lower number (high privilege)
    - Can set to higher number (lower privilege)

#### One off
- Most time only need two layers
- Virtualization boom changed that

### Get Lower Ring
- 2 ways
 - Interrupts
    - OS sets up handler 
    - Sets code cpu with load
    - That lauches in privilege ring
    - Drops privilege when done
 - Syscalls
    - syscall instruction
    - or just an interrupt again
















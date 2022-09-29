
### POS devices 
- Prilex Hackers
- Steal card and pin from card reader
- Send encrypted transactaction to prilex C2 machine
    - Keep legit transaction to operate normally
- Take encrypted transaction and go to fake POS device
    - Charges card to shell company
    - Fradualent transaction approved


### VMM Forensics
- The 80s and PC killed interest in VMs
    - Multiplexing users
    - But don't need to do that when we each own computers
- Suspendability of VMs is super useful
- Memory state is frozen and vcpu stops operating
- Useful in cases where forensics in needed on VM when servers are attacked


### Monitoring memeory 
- Passive
    - Viewing mem in A from S without time sync
- Active
    - Viewing mem in A from S with even notifications from A
    - Only monitoring at needed times
- Location Valuable
    - Applying models obtained from supervised learning
    - Finds critical data structures with raw mem view
- A is the VM
- S is security/monitor server from the hypervisor


### Solid State Drives
- Physically different than hard drives
- NAND flash memory
- Cell structure
    - Charge applied, the electrons tunnel into cell through barrier
    - charge stopped, electrons trapped
    - Positive / negative charge can be measured
- Overtime, may lose charge 
    - Many years could become hard to measure


### SSD Arch
- OS file system
- Flash files systems
- Flash Translation Layer
    - Implement in HW and/or SW
        - SSD do in HW
        - xD mem card in SW
    - Block manager
    - Dynamic Wear leveling
        - Manages which cells will be written to
        - Makes sure to evenly distribute cell usage 
            - Limited number of writes
        - Records number of read and writes
    - Garbage collector
        - Wipes unused cells
        - Handling this on its own so can lose information for forensics if SSD has power
- Driver
- Flash Technology / Cells

### Multilayer vs Singlelayer
- Has multiple bits in a cell
- 2 states of voltage for single cell
- 4 states of voltage for multiple
- Harder to keep quality cells of multiple
    - Harder to read


### NAND operations
- Read disturb 
    - When reading, cells near could get interference and change state
    - Need to reinfornce states to keep state


















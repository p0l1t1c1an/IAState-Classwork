
# Clark-Wilson


### Different Policies


### Clark-Wilson Integrity Model

- Uses transactions as the basic operation which models commercial systems IRL
- Commercial Features
    - The integrity of the data in the system and of the actions performed on the data
    - Integrity of the transactions themselves


- Set consistency contraints
    - Banking examples
    - Depsoits, Withdrawals, Yesterday balance
    - Today balance must be D+YB-W
    
    - Other example
    - Can have unauthed writes to password file


### Entities
- Constrained Data items (CDI)
    - Subject to integrity controls

- Unconstrained data items (UDI)
    - Not on the integrity controls

- Integrity Verification Procedures (IVPs)
    - Test that CDIs conform to the integrity constraints

- Transaction procedures (TP)
    - Take the system from one valid state to another


### Preforming a Transaction Procedure
- At current state
    - Need User Auth
    - CDIs
        - If there exists (User, TP, CDI)
        - Access control
        - Say the user can preform an action to data
    - Inputs 
        - UDI 
        - Filter to CDI
        - PReform action 
    - run TP    
        - Take to new valid state
        - Can verify


### Certification Rules
- CR is a certification rule
    - Person must certify  
- CR1 == when any IVP is run, ensure all CDIs are in a valid state
- CR2 == For some assocated set of CDIs, a TP must transform those CDIs to another valid state

- Users
- CR3 == The allowed relations must meet the requirements by the priciple of separation of duty
    - Separate users roles, can't verify own work 

- Logging
- CR4: All TPs must append enough info to reonstruct the operation to an append only CDI


- Untrusted input
- CR5 == Takes UDI as input can only do vald transformations or none. For all possible values of the UDI
    - Either rejects the UDI or transforms into a CDI

### Enforcement Rules
- ER1 == system must maintain the certified relations and ensure only TPs certified to run on CDI manipulate that CDI
- ER2 == sytem must associate user wth each TP and CDIs
    - TPs utilized by user to access CDIs

- Users
- ER3 == The system must authenticate each user attempting to execute a TP

- Separation of Duty
- ER4 == Only the certifier of a TP may change the list of entities associated with that TP.
    - No cerifier of a TP or of an entity associated with that TP, may ever have execute permissions with respect to that entity
    - **Same user can't be the one add it and utilize it**


### Certified Relations
- Access control
- User, TP, {CDIs}
- Table


### All about integrity
- CDIs are high integrity
- UDIs are low
- Filter that translate low integrity into high


### Advantage
- Commercial
- Distinct notation of cerification an enforcement

### Drawback
- Formal verification, burden, not necessary
- unaware of dynamically changing situations that are external to the system
- Black box the system



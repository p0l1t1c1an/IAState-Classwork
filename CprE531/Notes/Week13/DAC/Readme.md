
# Discretionary Access Control
- User sets permissions to their files

### Ideas
- MAC == Mandatory
- system has security model that overrides DAC


### Unix-like Access Control
- Subjects are the processes
- Identity: 
    - PID == Number (Not for access control)
    - UID == User ID 
    - EUID == Effective UID
        - From /etc/passwd
    - GID == Group ID
    - EGID == Effective GID
        - From /etc/group
- Effective actually used for Access control
- Process Table
    - Controlled by kernel
    - All info on a process
        - PID, UID, EUID, ... 
        - Virtual mem
        - etc
    - in kernel memory
    - System calls


### Unix Files

- Objects are files
    - Everything is a file
- Easy is done same as files
- Files and Directories are handled differently
- Files
    - Access control data is stored in files's directory entry
    - Directory is table 
    - AC in entry in that table
    - Modify that table to change permissions
- Permissions
```
rwx   rwx     rwx
user  group   other
```
- Owners
```
User owner      Group owner 
uid             gid
```


### AC for File
- Process p w/ p.euid and p.egid
- File f w/ f.uid and f.gid
```
if p.euid == 0 then allow full access (root)
else
    if p.euid == f.uid then grant user perms (bits above)
    else if p.egid == f.gid then grant group perms 
    else
        grant other perms
```


### Permissions Displayed
ls command output


### Who can change permissions?
- Own the file
- and write to the immediant parent directory
- or Root


### Who can change ownership?
- root


### Directories
- Same permissions and user owner and group owner
- read - list directory
- exec - enter directory and access the files in the directory
- write - create, delete, and modify the directory table entries
    - still need to own / have permissions to the files

- sticky bit
    - must own enclosed files/directories to delete them






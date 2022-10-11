

### Interesting Thing of The Day
- White House Bill of Rights regarding AI
    - Protected from unsafe / protect from harm
    - Discriminatory use of AI prohibited / need to focus on equality
    - Privacy Protections in to productions / stop abusive data practices / users have agency over data
    - Transparent to users / know that automated system is used and what that means or impact to them
    - Opt out of automated system in wanted


# Continuing File System Analysis

### Stegonagraphy
- In documents
    - Character font size 
    - increase or decrease .5
    - Slightly change colors
    - Invisible to people who look at the document
- File names
    - change the names to make them less specific
    - Change file extensions (Windows)


### Copying files
- Copy to another disk without enough space
    - removes the pointer to the file
    - inode removed so blocks thought of as not used
    - however some data was copied to whatever remaining space their was
- Computer crashes while copying file
    - File contents copied to unallocaated sectors
    - Pointer/inode removed thinking sectors aren't used
- Could be question on test


### Printing a File
- When printing, spooled to hdd before printing
- Spooling involves copying the file to temp, printing, and deleting 
- Deleting only unlinks the file
    - data is still on the disk

### Formatting a Disk
- Only the file table on the disk is cleared
    - Data on the disk is left in place
- Similar to deleteing

### Hiding Folders (Win)
- Create files or diretories with non-printable characters
- DOS use Alt-255 in numpad for name
    - Makes blank space but not a space
- If you show directory, you see the file but might not know the nonpritable char
- Accessibly in explorer
- Hidden attribute in file/directory
- Viewable if user sets show hidden file and contents

### Hiding in Unix
- file/directory starts with .
- could also call the file with unusable character or looks like another
- People won't know how to access the file
- Exploxer application could access it

- Mount file system over a directory with files in it
- Can only view files in the mounted FS
- Files originally in the directory are hidden
- umount to view the directory contents
- Might detect by using dh or similar
    - / is 10GB, space used is 2GB, only 4GB used
    - dh only reads 2GB
    - 

### Swap Space
- Increases amount of memory available to OS by using FS
- Total mem (ram + swap) is virtual memory
- Info is constantly written to mem and hdd
- Info can be extracted from swap
- Might use to store stuff removed from mem when loading info in to mem
    - Not enough space
- Memory management as well


### Core Dumps
- Unix when process generates a fault
- Core dump will contain all the data from CPU registers and memory of process
- Information useful from files that fail
- gdb and other debuggers

### Slack Space
- Also from slack space 


# File Systems

### FAT 
- Simple FS for DOS and early windows
- Each file/directory allocated as directory entry that contains:
    - names, size, starting address, meta
    - Chain of pointers from each block
    - First block points to second, second to third ...
    - Slow and jumps around
- Three versions
    - 12, 16, and 32

### Unix File System
- Multileveled page table entries 
- Initial table points block used 
    - which points to table of used blocks
    - and so on until you reach data
- Multiple disk reads
- inodes (files)
    - Owner, group, type, perms
    - atime, mtime, inode mtime
    - size, addresses
- chmod only changes inode 
    - only inode modifed time changes
- Directory representation 
    - Table of name to inode numbers
    - Hard links point to same inode
        - Can delete original and still pointed to
    - Sym link just points to original file
        - Is own file 
    - Table points to table points to table
        - Its a tree of tables
- MAC times
    - Access, modified, created, and delete


### Windows Registry
- Table of information
- Keys, users, software, configurations
- five root keys
    - HKEY_CLASSES_ROOT
    - HKEY_CURRENT_USER
    - HKEY_LOCAL_MACHINE
    - HKEY_USERS
    - HKEY_CURRENT_CONFIG









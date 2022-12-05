

# SELinux

### AC Types
- DAC == 
- MAC == Addition controls users must follow (confidentiality and integrity ones from before)
- RBAC == give roles which are used for permissions
    - fancy groups
- Identity-Based AC == identity of the actual user

### Flask Security Arch
- Client object request
- Object manager
    - query to security server
    - Returns decision
- Object manager enforces
    - Separates enforcement and policy

### OS Flask
- Security server is kernel module
- obj manager sys calls sec server





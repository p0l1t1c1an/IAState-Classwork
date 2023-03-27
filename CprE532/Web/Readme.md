
# Web Security

### URL
- Scheme:// [user:password]@hostname:[port]/path
- Scheme used for http and ftp
- By default port 80 is the standard for web services
- 443 is the port for secure web traffic
- Http is really nothing more then a file transport protocol
- Browser interprets the files to put text and pictures up on the screen


### CGI
- Provides interface to allow tasks to be done, server side applications
- Anytime input is used from user to run server side programs, security risks are present
- Outputs html
- Common gateway interface


### Web Server
- Directories
    - CGI-bin
        - CGI exec files
    - Conf
        - Config file
    - Html
        - .html doc files
    - Logs
        - server logs

### Config Files
- httpd.conf
    - Primary config
    - Many options
        - User and group
        - Identity check
            - check identity from client
        - Directives for document root
            - Concepts
                - Directory indexes, managing paths to files
            - Access control to files 

- Document root
    - Where we start looking for files
- User dir
    - Defines subdirectory that is default in user dir
    - /~user, goes to users directory
- Accessfilename
    - specify user access on directory basis
    - .htaccess file allow logins
- Alias
    - allows access outsides document root
- Script alias
    - alternate directory for CGI
- Indexignore
    - ignore certain files and control what user can see



### Access 
- Need to set users directory to 711
- Need read access to directories


### Basic Auth
- Static password
- Can be from passwd file or separate
- Global access needs to be enabled
    - Many password db
    - One for each directory
    - Groups work as well



### Access control
- Htpasswd
    - Create own password file
    - Anyone with write access to file can modify
- Can't make group file


### Rules for access control
- Do not put them in document tree
    - But in config directory or /etc 
- Just as secure as Unix passwords
- Control current directory and below 
- Restrict on User, group, or IP


### Format of .htaccess
- Starts out with <> and ends with </>
    - <Limit Get> limit get request
    - <Limit Post> limit server replies
    - Directives
        - AuthType
            - Basic, standard authentication
        - AuthUserFile
            - Path to password file
        - AuthGroupFile
            - Path to group file
        - AuthName
            - Gets printed on top of dialog box
        - Options
            - Look at options later
    - </Limit> ends section
- Like an html doc

### Restrictive Rules
- Order deny, allow
    - Deny from all
    - Allow from H1 D1
    - Require user Mary Fred
    - Require group Admins
- What does this do
    - Mary and Fred are allowed if they came from H1 or D1 and they must be in Admins group



### Directory Options
- One can specify additional security once the users have been authenticated
    - Exec CGI
        - Allow users to run CGI
    - Indexes
        - Allows user to see a directory listing without index file
    - Includes
        - Usually not allowed due to security risks
    - All
        - Turn on all options
    - None
        - Turn off all options


### Global ACL
- Global ACL allows certain access controls to various pieces of the tree
    - How do users override global ACL
        - Allow override must be given then user on a per directory basis can make distinct ACLs
        - Limit
        - Options
            - These are the options in previous slide
        - All
            - Allows everything to be overridden
        - None
            - Allows nothing to be overridden











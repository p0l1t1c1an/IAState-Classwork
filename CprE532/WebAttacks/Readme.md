
# Web Attacks


### Types
- Server executable vulnerabilities
- Sample Files
- Source code disclosure
- Canonicalization
- Server Extensions
- Input validation


### Executables
- Many vulnerabilities in the server code of the years
- Automated tools to detect these
- Not a focus of our discussion 
    - network application vulnerabilities

### Sample Files
- These are files included in the distribution of a web server 
    - template by developers
- Some files may execute something (default cgi-bin scripts etc)
- Some may support uploading or debugging


### Source Code Disclosure
- Allows users to view source code
- Mostly patched
- Canonicalization Attacks
- The way the URL is created
- If the web site wants c:\text.html but gets ../text.html
- Mostly has been patched

### Extensions
- Server extensions are additions to the web server to help to do extra things (php, ssl,etc.)
- These extensions can have bugs


### Input Attacks
- Buffer overflow 
    - CGI or backend
- Injections (Like SQL)


### Web Vulnerability Scanners
- Nikto
- Whisker 2.0
- I think of OWASP Zap or Burpsuite

### Google Attacks
- Google can find password files by scraping
- Can find targets

### Crawling
- Download site and look for attacks
    - Wget and explorer

### Web App Assessment
- Probing of app
    - Auth
    - Session management
    - Database interaction
    - Input Validation

### Common Vulns
- Invalidated input
- XSS
- Injection Flaws


### CGI
- Executed on server
    - Like shell script
    - Parameters from browser and output
    - Input validation issues
- Placed in CGI-bin
- Admins don't allows users to create scripts

- Websites used to have a finger CGI script to know who is logged in
- Hackers could send nobody; /bin/cat /etc/password
    - Would print passwd file

### SQL Injection
- Putting raw SQL into form fields
- Depends on SQL implementation

### Cross Site Scripting
- Post malicous code to web site
    - Message board
- Runs on client browsers

### Cookies
- Way for websites to store data  
    - Keep state information
- Used to be that any site could read all cookies
- Now only site that created it can read it


### Clear Gifs 
- 1 pixel 
- Tracking who downloads image


### Web Service 
- Popular to attack
- Logs get filled
- Simplest
    - Comments in HTML documents
- CGI 
    - Massive scans on web server
    - Attacks all servers
        - Apache, IIS ...
- DOS
    - Webhug

### Encryption
- SSL/TLS
    - Between HTTP and TCP
    - 443
    - Browser comes with certs


### Logs
- Access Logs
    - More of a privacy concern
    - Show files Transferred and to who (IP)
        - Time
    - If root, could fill directory
- Referer Log
    - Tells where user came from
    - Like what site had hyperlink to this one
- Agent Log
    - What browser
- Error Log 
    - Access file that doesn't exist gets logged here



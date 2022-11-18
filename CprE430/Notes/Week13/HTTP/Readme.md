
# World Wide Web

### Basics
- All of it is a file transfer
- URL is the file ID
    - Retrieve any file if you know url

![transfer](./transfer.png)

![complex](./complex.png)

![server](./server.png)

- Server Side
    - Authentication rarely used 
        - Cumbersome
    - Exe mean client tells server to run code and sends the output

- Client Side
    - Interprets data recieved from server
    - Able to execute code (javascript)
    - Caching

# HTTP

### Hypertext Transfer Protocol
- Simple command/response protocol
- ASCII based commands
- Typically a new connection for each command/response exchange
- Server runs on port 80 default

### Request and Response

![randr](./RandR.png)

- Requests
    - Three parts:
        - Request line
        - Headers
        - Blank line
        - Body (optional)
    - Request line looks like this:
        - Request type <sp> URL <sp> HTTP version
        - Example: GET http://www.ibm.com HTTP/1.1

### URL
- Uniform Resource Locator
- A URL follows this format: 
    - method://host:port/path
- The host can be a machine name or IP 
- The port must be specified if the server is not on 80
- The path is the directory where data is stored

### Request Types
- GET Retrieve a document specified by the URL.
- HEAD Retrieve the headers from the document specified by the URL. 
    - (Response does not contain the body.)
- POST Provide data to the server.
- PUT
- PATCH 
- COPY
- MOVE
- DELETE
- LINK
- UNLINK
- OPTION

- Many of these types can pose security problems 
- Since they involve modifying or deleting data
- Most servers only implement: GET, HEAD, POST


### Response Message
- Four parts:
- Status line
- Headers
- Blank line
- Body
- The status line looks like this:
    - HTTP version <sp> status code <sp> status phrase
    - Examples:  HTTP/1.1 404 File not found
    - HTTP/1.1 200 OK

### Response Codes
- Status codes follow a similar format to FTP and SMTP status codes
- 3 digit ASCII
    - 1xx  informational
    - 2xx  success– 2xx  success
    - 3xx  redirection
    - 4xx  client error
    - 5xx  server error

![response](./responses.png)


### HTTP headers

![header](./headers.png)

- Headers have three parts:
    - General header
    - Request or response header, depending on whether the header precedes a request or a response
    - Entity header
- The general header contains the following fields:
    - Cache control / Used to specify information about the client side cache.
    - Connection / Indicates whether the connection should be closed.
    - Date / Provides the current date.
    - MIME version / Indicated the MIME version being used.
    - Connection / Use to determine connection type. 
    - Keep Alive / Used to manage keep-alive  connection.

### Request and Response Headers

- Request 

![req_header](./Reg_head.png)

- Response

![resp_header](./Resp_head.png)


### Entity Header

![entity](./entity.png)

### Diagram

![diagram](./diagram.png)

![request](./Req_example.png)

![response](./Resp_example.png)


# Attacks

### Header Attacks
- Buffer overflow problems
- Server can pass HTTP requests to back-end servers and applications
    - Header problems are not just with the WEB server
- Some header-based attacks facilitate authentication-based attacks
- Accessing hidden pages

### Protocol Attacks
- Null, simple command and response protocol

### Authentication Attacks
- This is the most common method of attack in the WEB 
- The web server uses HTTP to request user credentials
- Authentication can also be directly with the server side application
- Authentication is used to access pages within a directory on the server


### Web Auth
- Server challenge: 
    - WWW-Authenticate: Basic realm=“Text String" 
- Client Challenge:
    - user-ID and password, separated by a single colon
    - (":") character, within a base64 encoded string. 
- For example:
    - Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==


![auth](./authentication.png)

- .htaccess file
    - points to password file
    - rules for authentication
        - IP and User/PW
    - won't be given to user

![auth2](./auth_header.png)

- Weaknesses
    - Can be sniffed (traffic based attack)
    - Can be guessed
- Countermeasures:
    - Encrypted sessions
    - Good passwords

### Traffic Attacks
- Very common attacks
    - Flooding
    - Web Hugging
- HTTP is clear text
    - HTTP does not support encrypted sessions.
    - Encrypted sessions are supported using transport layer encryption

### HTTPS
- Use SSL / TLS
- Port 443
- Use public key Certificates

### Certificates
- Server sends to browser
- Browsers has list of verified certifcates
    - Root self signed one
- Servers cert must be signed by one of roots provided by browser
    - Or in chain of signatures

![chain](./chain.png)



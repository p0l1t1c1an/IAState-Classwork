

// this calls the DNS system 
h_name = gethostbyname("vulcan.ee.iastate.edu");
/* s_name = getservbyname("phone", "udp");*/
/* sin.sin_port = s_name->s_port; */
sin.sin_family = AF_INET;
sin.sin_port = htons(2000);  // port to connect to
sin.sin_addr.s_addr = *(u_long *)h_name->h_addr;
printf("port = %d  %s\n",ntohs(sin.sin_port), 
inet_ntoa(sin.sin_addr));
// open socket
sockFD = socket(AF_INET, SOCK_STREAM, 0);
// open connection to server
if (connect(sockFD, &sin, sizeof(sin)) < 0) {
perror("connect request");
(void) close(sockFD);
exit(1);
}

/*
 * Data Transfer
 */


strcpy(buf,"from client");
// client sends first
if (send(sockFD, buf, strlen(buf),0) != strlen(buf)) {
perror("send request");
(void) close(sockFD);
exit(1);
}}
// Client waits for answer
cp = answer;
if ((n = recv(sockFD, cp, 100, 0)) < 0){
perror("SendRequest");
(void) close(sockFD);
}
cp[n] = 0;
printf("===<%s>===\n",cp);
(void) close(sockFD);




nsaddr.sin_family = AF_INET;
nsaddr.sin_addr.s_addr = INADDR_ANY;  // Accept connection from all
/* nsaddr.sin_addr.s_addr = inet_addr("129.186.5.101"); */
nsaddr.sin_port = htons(2000);
// Open stream port.
if ((vs = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
printf("socket(SOCK_DGRAM):  %d\n",errno);
exit(1);
}
// bind stream to port 2000 from any address
if (bind(vs, (struct sockaddr *)&nsaddr, sizeof(nsaddr)) < 0) {
printf("bind(vs, %s[%d]) errno = %d\n "
,inet_ntoa(nsaddr.sin_addr), ntohs(nsaddr.sin_port),errno);
perror("bind error");
exit(1);
}
fprintf(stderr,"SERVER: bind(vs, %s[%d]):\n ",
inet_ntoa(nsaddr.sin_addr), ntohs(nsaddr.sin_port));

printf("SERVER: listen waiting\n");
// allow 5 pending connection requests to this port
if ((listen(vs,5)) < 0 ) {
perror("listen");
exit(1);
}
printf("SERVER: waiting  buf size = %d\n",sizeof(buf));
from_len = sizeof(from_addr);
// wait for incoming connection
if ((ns = accept(vs, &from_addr, &from_len)) < 0) perror("accept");

/*
 * Data Transfer
 */


printf("SERVER: accepted call\n");
// print where the connection came from
fprintf(stderr,"SERVER: from_addr(ns, %s[%d]):\n ",
inet_ntoa(from_addr.sin_addr), ntohs(from_addr.sin_port));
// get the data from the client
blen = recv(ns,buf,sizeof(buf), 0);
buf[blen] = 0;buf[blen] = 0;
printf("SERVER: --<%s>--\n",buf);
strcpy(buf,"hello");
printf("SERVER: sending\n");
// send response to client
if (send(ns, buf, strlen(buf), 0) != strlen(buf)) {
perror("Sendto");
}
// shutdown connection, leaves socket open
shutdown(ns,2);


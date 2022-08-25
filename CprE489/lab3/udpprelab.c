#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>
#include <arpa/inet.h>

int main(void){

	int sock;
	if((sock = socket(PF_INET,SOCK_DGRAM,0)) < 0)
	{
		perror("Failed Socket Declaration");
		return -1;
	}

	struct sockaddr_in addr;
	addr.sin_family = PF_INET;
	addr.sin_port = htons(3022);
	addr.sin_addr.s_addr = inet_addr("127.0.0.1"); //might be wrong

	if (bind(sock, &addr,sizeof(addr)) < 0){
		perror("Failed Bind");
		return -1;
	}
}

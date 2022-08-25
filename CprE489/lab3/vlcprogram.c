#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>
#include <arpa/inet.h>

#define MAX_BUFF 1328
int main(int argc, char *argv[]){
	if(argc != 6) {
		return;
	}
    //initialize arguments to useable variables
	char * sourceIP = argv[1];
	int sourcePort = atoi(argv[2]);
	char * destIP = argv[3];
	int destPort = atoi(argv[4]);
	int lossRate = atoi(argv[5]);
	
	srand(time(NULL));
    
    //Create a socket for the source
	int sourceSock;
	if((sourceSock = socket(PF_INET,SOCK_DGRAM,0)) < 0)
	{
		perror("Failed Socket Declaration");
		return -1;
	}
	
	//Create a socket for destination
	int destSock;
	if((destSock = socket(PF_INET,SOCK_DGRAM,0)) < 0)
	{
		perror("Failed Socket Declaration");
		return -1;
	}
	
	//Initialize Source address and Destination Address using the arguments provided for destination and source
	struct sockaddr_in sourceAddr;
	sourceAddr.sin_family = PF_INET;
	sourceAddr.sin_port = htons(sourcePort);
	sourceAddr.sin_addr.s_addr = inet_addr(sourceIP);

	struct sockaddr_in destAddr;
	destAddr.sin_family = PF_INET;
	destAddr.sin_port = htons(destPort);
	destAddr.sin_addr.s_addr = inet_addr(destIP);

	struct sockaddr_in blank;
	
    //Bind the source Socket
	if (bind(sourceSock, &sourceAddr,sizeof(sourceAddr)) < 0){
		perror("Failed Bind");
		return -1;
	}
	char buff[MAX_BUFF] = {0};
	int sourceSize = sizeof(sourceAddr);
	
	//Continuosly run source listening on provided port
	while(1){
		ssize_t mes_len;
		
		//Recieve the data from the vlc video capture
		if ((mes_len = recvfrom(sourceSock,&buff,MAX_BUFF,0,&blank, &sourceSize)) < 0){
			perror("Failed recvfrom");
			return -1;
		}
		
		//Randomize at the loss rate while sending packets over to destination
		//lossRate is the passed in argument as a percentage of lost packets
		if((rand() % 100) >= lossRate) {
			if(sendto(destSock, buff, MAX_BUFF,0,&destAddr,sizeof(destAddr)) < 0){
				perror("Failed to send");
				return -1;
			}
		}		
	}
}

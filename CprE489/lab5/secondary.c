#include<stdio.h>
#include<sys/socket.h>
#include <string.h> 
#include "ccitt16.h"
#include "utilities.h"

void secondary(int client_sock) {
    int read_size;
	unsigned char msg[150];
	unsigned char expected = 0;

    
    while( (read_size = recv(client_sock , msg , PACKET_SIZE , 0)) > 0 )
    {
		printPacket(msg);
		if(calculate_CCITT16(msg, PACKET_SIZE, CHECK_CRC) == CRC_CHECK_SUCCESSFUL)
		{
			if(msg[1] == expected)
			{
				printPacket(msg);
				expected++;
				unsigned char ack[PACKET_SIZE];
				unsigned char buf[DATA_LENGTH] = {'\0'};
				buildPacket(ack, ACK_PACKET, buf, expected);
				
				printf("Sending ACK(%d)\n", expected);
				if(send(client_sock, ack, sizeof(ack), 0) < 0)
					perror("Send failed\n");
			}
			else
			{
				printf("Received packet %d out of order\n", msg[1]);
				unsigned char ack[PACKET_SIZE];
				unsigned char buf[DATA_LENGTH] = {'\0'};
				buildPacket(ack, ACK_PACKET, buf, expected);
				
				printf("Sending ACK(%d)\n", expected);
				if(send(client_sock, ack, sizeof(ack), 0) < 0)
					perror("Send failed");
			}
		}
		else
		{
			printf("Received corrupted packet. Sequence number (maybe) %d\n", msg[1]);
			unsigned char nak[PACKET_SIZE];
			unsigned char buf[DATA_LENGTH] = {'\0'};
			buildPacket(nak, NAK_PACKET, buf, expected);
			if(send(client_sock, nak, sizeof(nak), 0) < 0)
				perror("Send failed\n");
		}
    }
     
    if(read_size == 0)
    {
        puts("Client disconnected");
        fflush(stdout);
    }
    else if(read_size == -1)
    {
        perror("recv failed");
    }

}



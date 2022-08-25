#include <stdio.h> 
#include <string.h>    
#include <sys/socket.h>    
#include <stdlib.h>
#include "ccitt16.h"
#include "utilities.h"
#include "introduceerror.h"

#define N 3 

void primary(int sockfd, double ber) {
	int read_size;
	unsigned char last = 0, start;
    	char msg[100], srv_reply[150];

    //keep communicating with server
    while(1)
    {
	start = last; // Continues Seq number to 
	memset(msg, 0, 100);
    printf("Enter message : ");
	fgets(msg, 100 , stdin);    //get message

	for(char *c = msg; *c; c++) {
		if(*c == '\n') 
			*c = 0;     // removes newline from fgets at end
	}	

	int len = strlen(msg);
	int arr_len = len%DATA_LENGTH ? len/DATA_LENGTH + 1 : len/DATA_LENGTH;
	
	struct packet_buffer packets[arr_len];       // all packets to be sent
	struct packet_buffer buffer[N];             // current packet window 

	unsigned char sent[arr_len];                // All sent packets
	memset(sent, 0, sizeof(char)* arr_len);     // Zeroed
	
	// Build all packets to be sent in the future 
	for(char *i = msg; *i; i++) {       
        // i is current char	
        unsigned char buf[DATA_LENGTH] = {0};
            
		char *j = i + sizeof(char); // j is next char
		if(j) {                     // if j is not null term
			buf[DATA_LENGTH-2] = *i; // put i as first data in packet and j as second
			buf[DATA_LENGTH-1] = *j;
			i++; 
		} else {                    // j is null term
			buf[DATA_LENGTH-2] = *i; // i is only data to be sent
			buf[DATA_LENGTH-1] = 0; 
		}

        buildPacket(packets[last-start].packet, DATA_PACKET, buf, last);
		printf("Built: %d \n", last);
		printPacket(packets[last-start].packet);
		last++;
	}

	last = start;   // Keeping track of seq number (so I can send multiple messages while running program)
	for(int i = 0; i < N; i++) {
		memcpy(buffer[i].packet, packets[i].packet, sizeof(struct packet_buffer)); // Copy packet so we can add error
		printf("Sent: %d \n", last+i);
		printPacket(buffer[i].packet);
		IntroduceError(buffer[i].packet, ber);  // add error
		if( send(sockfd , buffer[i].packet, sizeof(struct packet_buffer), 0) < 0)
			perror("Send failed");
		sent[i] = 1;	// We sent the packet
	}

	while(last-start < arr_len) { // untill all are acknowledged
	        if( (read_size = recv(sockfd, srv_reply, 149, 0)) < 0) 
            		perror("recv failed");
		srv_reply[read_size] = '\0'; 
		
		printf("Packet: %d, %d\n", srv_reply[0], srv_reply[1]);
		if(srv_reply[0] == ACK_PACKET) {
			last = srv_reply[1];    // Update seq number
			printf("Packets up to %d have been acknowledged\n", last);

			for(int i = 0; i < N && last+i-start < arr_len; i++) { 
				if(!sent[last+i-start]) {	// has the packet been sent before
					memcpy(buffer[i].packet, packets[last+i-start].packet, sizeof(struct packet_buffer)); // Copies packet to add erro
					printf("Sent: %d \n", last+i);
					printPacket(buffer[i].packet);
					IntroduceError(buffer[i].packet, ber);
					if( send(sockfd , buffer[i].packet, sizeof(struct packet_buffer), 0) < 0)
						perror("Send failed");
					sent[last+i-start] = 1;
				}		
			}

		} else if(srv_reply[0] == NAK_PACKET) {
			last = srv_reply[1];	
			printf("Packet %d was negatively acknowledged\n", last);
			printf("Sending packets %d to %d\n", last, arr_len > last+N-start ? last+N-1 : arr_len-1);

			for(int i = 0; i < N && last+i-start < arr_len; i++) {  // We just send all in the window
				memcpy(buffer[i].packet, packets[last+i-start].packet, sizeof(struct packet_buffer)); // copy packet to add error
				printf("Sent: %d \n", last+i);
				printPacket(buffer[i].packet);
				IntroduceError(buffer[i].packet, ber);
				if( send(sockfd , buffer[i].packet, sizeof(struct packet_buffer), 0) < 0)
					perror("Send failed");
				sent[last+i-start] = 1;
			}
		}	
	}	

    }
}	


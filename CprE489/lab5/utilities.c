#include "ccitt16.h"
#include "utilities.h"

int buildPacket(unsigned char packet[], unsigned char type, char data[], unsigned char num)
{	
	int i, j;
	//Set type and sequence number
	packet[0] = type;
	packet[1] = num;
	
	//set data field
	for(i = DATA_OFFSET, j = 0; i < DATA_LENGTH + DATA_OFFSET; ++i, ++j)
	{
		packet[i] = data[j]; //packet[2] = data[0]; packet[3] = data[1]
	}
	
	//union to split the CRC output into two bytes.
	//Suggestion taken from GedasL at http://www.avrfreaks.net/forum/c-programming-how-split-int16-bits-2-char8bit
	union short_split
	{
		short int CRC;
		unsigned char bytes[2];
	}splitter;
	
	splitter.CRC = calculate_CCITT16(packet, PACKET_SIZE - 2, GENERATE_CRC);
	//set the two CRC bytes
	packet[PACKET_SIZE - 1] = splitter.bytes[0];
	packet[PACKET_SIZE - 2] = splitter.bytes[1];

	return num;
}

void printPacket(unsigned char packet[])
{
	int i;
	
	switch(packet[0])
	{
		case DATA_PACKET:
			printf("Type: Data\n");
			break;
		case ACK_PACKET:
			printf("Type: ACK\n");
			break;
		case NAK_PACKET:
			printf("Type: NAK\n");
			break;
		default:
			printf("UNDEFINED");
	}
	
	printf("Sequence Number: %d\n", packet[1]);
	printf("Data: ");
	for(i = DATA_OFFSET; i < DATA_OFFSET + DATA_LENGTH; ++i)
	{
		printf("%c", packet[i]);
	}
	printf("\n");	
	printf("Checksum: %x%x\n\n", packet[PACKET_SIZE - 2], packet[PACKET_SIZE - 1]);
			
}

int contains(int arr[], int arrSize, int val)
{
	int i; 
	for(i = 0; i < arrSize; i++)
	{
		if(arr[i] == val)
			return i;
	}
	return -1;
}

void shiftWindow(int arr[], int size, int amt)
{
	int i;
	for(i = 0; i < size; ++i)
	{
		arr[i] += amt;
	}
}

int shiftBuf(struct packet_buffer arr[], int size, int amt)
{
	int i;
	for(i = amt; i < size; ++i)
	{
		arr[i - amt] = arr[i];
	}
	//Return the amount to subtract from the buffer index.
	return amt;			
}

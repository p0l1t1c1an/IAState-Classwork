#ifndef HELPERS

#define PACKET_SIZE 6
#define DATA_LENGTH 2

#define DATA_OFFSET 2

#define WINDOW_SIZE 3

#define DATA_PACKET 1
#define ACK_PACKET 2
#define NAK_PACKET 3

/**
*Struct so I could make an array to use as a buffer of whole packets
*/
struct packet_buffer
{
	unsigned char packet[PACKET_SIZE];
};

/**
*Builds a packet by editing the empty packet array. 
*The packet array is expected to be of defined size PACKET_SIZE. 
*The type field is expected to be one of the defined packet types.
*The data array is expected to be the size of the defined DATA_LENGTH.
*num is the packet number of the current packet.
*Returns the packet number.
*
*Defined values above can be changed to affect packet size. 
*/
int buildPacket(unsigned char packet[], unsigned char type, char data[], unsigned char num);

/**
*Prints the given packet based on the defined constants.
*/
void printPacket(unsigned char packet[]);

/**
*Used to search an array for a value. If it exists, returns the index, else -1
*/
int contains(int arr[], int arrSize, int val);

/**
*Used to shift the send window frame by amt
*/
void shiftWindow(int arr[], int size, int amt);

/**
*Used to shift the packet buffer frame by amt.
*Returns the amount the buffer index should be changed by.
*/
int shiftBuf(struct packet_buffer arr[], int size, int amt);



#endif

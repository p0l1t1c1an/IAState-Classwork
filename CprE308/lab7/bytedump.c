#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>

int main(int argc, char ** argv)
{
	if (argc < 3)
	{
		printf("Usage: ./bytedump <disk_image> <offset>\n");
		printf("For example: ./bytedump image 100\n");
		return -1;
	}
	
	char *fileName = argv[1];
	int offset = atoi(argv[2]);
	
	int fd = open(fileName, O_RDONLY);
	
	unsigned char buf[32];

	
	printf("Trying to read 32 bytes in \"%s\" starting from offset %d.\n", fileName, offset);
	
	lseek(fd, offset, SEEK_SET);
	ssize_t actual_read_count = read(fd, buf, 32);
	
	printf("Actually read %ld bytes:\n", actual_read_count);
	
	printf("================================\n");
	printf("Address  Hex    Decimal  ASCII\n");
	
	int i;
	for (i=0; i<actual_read_count; i++)
	{
		int addr = offset+i;
		unsigned char value = buf[i];
		printf("0x%04x   0x%02x   %-7d  %c\n",addr, value, value, value);
	}
}

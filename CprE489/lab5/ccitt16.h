#include <stdio.h>
#include <assert.h>

#ifdef  __cplusplus
extern "C" {
#endif


#define GENERATE_CRC     1
#define CHECK_CRC          2

#define CRC_CHECK_SUCCESSFUL	0
#define CRC_CHECK_FAILURE         1

#define CCITT_POLYNOMS   0x8408
#define FEEDBACK(a)  (a = (a^CCITT_POLYNOMS))

short int calculate_CCITT16(unsigned char cData[],unsigned int iLen, unsigned int iAction);

#ifdef  __cplusplus
}
#endif

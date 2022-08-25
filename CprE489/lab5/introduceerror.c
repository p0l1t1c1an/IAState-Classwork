/*****************************************************************************/
/*                                                                           */
/* This routine is called with a pointer to a null terminted string of       */
/* characters, data, and a probability of bit error, p, and introduces       */
/* errors in the bits of the string according to this probability.           */
/* The routine does not return anything.                                     */
/*                                                                           */
/*****************************************************************************/

long M = 2147483647;

void IntroduceError(char *data, double p)
{
	char c, *pointer = data;
	int i;
	while (*pointer != '\0') {
		c = 0x01;
		for ( i = 0; i < 8; i++) {
			if ((double)random()/M <= p)
				*pointer ^= c;
			c <<= 1;
		}
		pointer++;
	}
}

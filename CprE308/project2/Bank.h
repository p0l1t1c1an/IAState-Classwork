/**  Do not modify this file  **/
#include <unistd.h>
/*
 *  These functions do not provide any error checking.
 *  If an invalid input is supplied the behavior is
 *  undefined.
 */

/*
 *  Intialize n bank accounts with IDs from 1 to n and values of 0.
 *  Input:  int n - Number of bank accounts, must be larger than 0
 *  Return:  1 if succeeded, 0 if error
 */
int initialize_accounts( int n );

/*
 *  Read a bank account
 *  Input:  int ID - Id of bank account to read
 *  Return:  Value of bank account ID
 */
int read_account( int ID );

/*
 *  Write value to bank account
 *  Input:  int ID - Id of bank account to write to
 *  Input:  int value - value to write to account
 */
void write_account( int ID, int value);

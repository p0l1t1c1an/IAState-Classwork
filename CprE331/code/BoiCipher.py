#! env python3

import sys, getopt

'''
Shift cipher but with a twist.
Each character in the text is shifted by
(index of text + 1) * (shift value)
and the text is flipped beforehand
'''
    
def flip(text : str) -> str:
    text = text[::-1]
    return text


def encrypt(text : str, key : int) -> str:
    result : str = ''
    i = 0
    text = flip(text) # Flip text
    for char in text:
        if char.islower():
            move = (i+1) * key # Value to shift by
            result += chr((ord(char) + move - ord('a')) % 26 + ord('a')) # shift text
            i += 1 # Adjust index for characters (skipping non-lower chars)
    return result

def decrypt(text : str, key : int) -> str:
    result : str = ''
    i = 0
    for char in text:
        if char.islower():
            move = (i+1) * key 
            result += chr((ord(char) - move - ord('a')) % 26 + ord('a')) # shift in reverse
            i += 1
    return flip(result) # Flip back text at end


def main(argv):  
    
    text : str = ''
    key : int = 0
    to_enc : bool = True

    '''
    Options: -h: print help information
             -e: encrypt text (needs -t and -k set)
             -d: decrypt text (needs -t and -k set)
             -k: the shift value (integer)
             -t: text to encrypt/decrypt (string)
    '''

    try: 
        opts, args = getopt.getopt(argv, "hedk:t:", [])
    except getopt.GetoptError:
        print('BoiCipher.py -e -k <shift number> -t <plaintext>\nBoiCipher.py -d -k <shift number> -t <plaintext>')
        sys.exit(2)

    for opt, arg in opts:
        if opt == '-h':
            print('BoiCipher.py -e -k <shift number> -t <plaintext>\nBoiCipher.py -d -k <shift number> -t <plaintext>')
            sys.exit(2)
        elif opt in "-d":
            to_enc = False
        elif opt in "-e":
            to_enc = True
        elif opt in "-k":
            key = int(arg)
        elif opt in "-t":
            text = arg.lower()

    
    if to_enc:
        print("Encrypted text: {0}".format(encrypt(text, key)))
    else: 
        print("Decrypted text: {0}".format(decrypt(text, key)))


if __name__ == "__main__":
   main(sys.argv[1:])



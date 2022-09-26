
# Cryptography

### Vigenere Continued
- Algorithm for any alphabet
- Sigma is the alphabet
![vig](./vig.png)

- How to attack it
    - Frequency Analysis
        - Break it into columns 
        - Analysis the columns
        - They are all the same encrypted the same
        - Same action done 

### Sidebar General Substitution 
- A -> D, B -> Y ... randomly
- Number of keys
    - 26! or factorial
    - 4 * 10^26
- How to attack
    - Wheel of fortune technigue
        - Letter frequency
        - Not obfuscating plaintext message
    - Same characters has small plaintext it could be
        - They are looked up the same in table
        - ee, ss, tt, ...


### Vignere Index of Coincidence
- Counts frequency in cipher text
- Sum of repeating a pair
    - $\sum_{i=A}^{Z} \frac{c[i]}{n} * \frac{c[i]-1}{n-1}$
- Simplifies to sum of probabilities of each squared
    - $\sum_{i=A}^{Z} p(i)^2$
- Close but not equal 

- Take by size of alphabet 
    - Normalized IOC
    - Takes how big alphabet out of number


- Properties to text
    - Gets 1.73 for common uses of english language
    - Certain order or characters don't matter
    - Test the vignere columns to see if we get 1.73 
    - Know that it is probably right key length
    

### One Time Pad
- P == Plaintext w/ N chars
- k == Cryptographic random key w/ N chars
- Ci = (Pi + ki) mod |alphabet|
- Never reuses the same key
- Theoretically impossible to break
- Will have to go through every key to break it
    - In automated process





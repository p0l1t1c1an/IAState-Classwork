#! env python3

import itertools
import enchant
from collections import Counter

text="qkctklwauonazvtldmqgjgqgysnivtajovtldmcgjgjhzeaiazaimfumppblldmeonazlumobbvsjllumdwyjwrmeeldqamsxiajaojyuoevtjatqrjgqjykxkcezaimlumbmrrshzvmzpeukfuwhnwwzgnwicfounqamoatytgsgbakpmcofoqqktqblumzwazkamgugiiaexwkryukuvtyevbalkngnwnivtldmlnwwzgnwfimfyklbcfywzvwpqgogjqauldmevdwkrymdjhzldmuujjagnwujyuoevgnspabafzenegjlbcfowhzzsilufzwjtkkcgndkvqufpwjtqkcpnwysbalccvzsnorujcmukcjwjyshtgnwypbxvouvtvebfylnqpzduzuelduukvkmftlsiazlkunqwebpxqkzfofcbukqoivjsjwyjyqqggjeanrddmpgfwnsujzeukfdmtkloccafzmezzatvmzpaguhhilnaobuofciajzwzeevkmftliqajabprjgaaazewsrzzaapkfapryykbnjsubvswfwonwolbofciyxacpgnwyiavdwggnwdwaqqpwaqdesrgfubuofcanbajqgahbweljelnefeouzoebuzzaahrlwvfcwnmgnwocyzsjablksqamldmagunwjjsuwhtyxwlyldmlxwwnbudevnxgqvqofpprignvrxvncaqsjlqxwoarjajbukanjrylxzbcfxitmaaantvpprojltnzxkzzyghmfzzagqufpovbwwlnsfwjbalwvlzjqucklltneajjntvebnofpeuglppreuwtyxgysntvnwyrsjlgnwocyzsjalksdbukkqtggfobukqltneunmbrwyzrudaiajldmazzauntzaagkhozvmzpcczgpprsayzbvzkvrgfzanekwbygkprhylwagnwpqzktatyxajofmgklaoydbauoebfzaimguykpbswpprtzaunqwoqglsobjoldwakekzrzzevt"

distances = []
factors = Counter()
counts = []

# Rework of my shift from last python program
def decrypt_shift(text : str, key : int) -> str:
    result : str = ''
    i = 0
    for char in text:
        if char.islower(): 
            result += chr((ord(char) - key - ord('a')) % 26 + ord('a')) # shift in reverse
            i += 1
    return result

def main():  
    # Generates each pattern of length 2-7 in text
    for i in range(2, 8): 
        for j in range(len(text)-i+1):
            p = ""
            for n in range (j, j+i):
                p += text[n]

            # For the pattern check if it repeats in the remainder of text and store distance
            for k in range(j, len(text)-len(p)):
                if p == text[k+1:k+1+len(p)]:
                    distances.append(1+k-j)

    # Counts the factors of distances
    for i in range(0, len(distances)):
        for j in range(2, distances[i] + 1):
            if distances[i] % j == 0:
                factors.update([j])
   
    
    # Prints out Top 10 most common factors #1 is 5 
    print("Top Ten Most Common Factors with # of occurances")
    print(factors.most_common(10))

    for i in range(0,5):
        count = Counter()
        j = i
        while(j < len(text)):
            count.update(text[j])
            j += 5
        
        print()
        print("Most common in index {0}".format(i))
        print(count)
        count = list(count)
        del count[5:]
        counts.append(count)

    # Comment code below if you can't get PyEnchant to work 
    #'''

    # Shift back top 5 most common by e 
    generated_shifts = ("HSBOW", "ZGLSW", "IXSWE", "CWNJX", "VQGPJ")

    # Generates all possible keys using one letter from each string in generated_shifts
    # Then uses PyEnchant to check if they are english words

    print("\nAll most common possible keys")
    diction = enchant.Dict("en_US")
    words = []
    for i in list(itertools.product(*generated_shifts)):
        temp_string = ""
        for j in i: 
            temp_string += str(j)
        if diction.check(temp_string):
            print(temp_string)
            words.append(temp_string)

    #'''
    # Comment code above if PyEnchant does not work

    # Uncomment words if PyEnchant won't work
    #words = ("SLING", "SWING", "BLING", "OWING")

    # Uses found english words as possible keys 
    for i in words:
        decrypted = ""
        for j in range(len(text)):
            decrypted += decrypt_shift(text[j], ord(i[j % len(i)]) - ord('A'))

        print("\nDecrypted Text with key of value {}".format(i))
        print(decrypted)


if __name__ == "__main__":
   main()



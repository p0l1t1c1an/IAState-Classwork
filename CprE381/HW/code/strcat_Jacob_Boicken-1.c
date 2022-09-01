
#include <stdio.h>

char *strcat(char* dst, const char* src) 
{
    char *c;
    for(; *dst; ++dst) { } 
    for(c = src; *c; ++c) 
    {
        *dst = *c;
        dst++;
    }
    *dst = 0;

    return dst;
}


int main()
{
    char text[10] = {'A', 'r', 'k', 's', 0};
    
    printf("Original string: %s\n", text);

    strcat(text, "? No!");

    printf("New string: %s\n", text);
    
}



#include <iostream>
#include <vector>
#include <array>
#include <algorithm>

using namespace std; // Being Lazy

#define LEN 5

bool matrix[LEN][LEN] = {
    {0, 0, 1, 1, 1},
    {0, 0, 1, 0, 1},
    {1, 1, 0, 1, 1},
    {1, 0, 1, 0, 1},
    {1, 1, 1, 1, 0},
};

bool 
is_adjacent(int i, int j) 
{
    return matrix[i][j];
}


// Paths can't go over the same edge so need a travelled list of edges
int 
find_num_paths(int len, int start, int stop, vector< array<int, 2> > &travelled)
{
    if(len == 0) { return 0; }

    else 
    {
        // len 1 and haven't travelled the path
        if( len == 1 && find(travelled.begin(), travelled.end(), array<int, 2>{{start, stop}}) == travelled.end() ) 
        {
            return is_adjacent(start, stop);
        }
        
        int paths = 0;
        for(int i = 0; i < LEN; ++i)
        {
            // Go to every adjacent vertex without going over a repeating edge
            if(is_adjacent(start, i) && 
               find(travelled.begin(), travelled.end(), array<int, 2>{{start, i}}) == travelled.end() )
            {
                vector< array<int, 2> > copy = travelled;
                
                copy.push_back(array<int, 2>{{start, i}}); // Add both directions as it is undirected graph
                copy.push_back(array<int, 2>{{i, start}});               

                paths += find_num_paths(len-1, i, stop, copy); // Find number of paths starting from adjacent point (length -1)
            }
        }

        return paths;
    }
}

// A^pow
array<array<int, LEN>, LEN>
power(int pow) 
{
    vector< array<int, 2> > travelled = vector< array<int, 2> >();
    array<array<int, LEN>, LEN> powered_matrix = array<array<int, LEN>, LEN>{};

    for(int i = 0; i < LEN; ++i)
    {
        array<int, LEN> row = array<int, LEN>{}; 
        
        for(int j = 0; j < LEN; ++j)
        {
            row[j] = find_num_paths(pow, i, j, travelled);  // Each (i, j) is num paths for pow=length
        }

        powered_matrix[i] = row;
    }

    return powered_matrix;
}


int 
main(void) 
{
    auto pow_one = power(1);  
    auto pow_three = power(3);
    auto pow_four = power(4);

    cout << "Power of 1" << endl;

    for(int i = 0; i < LEN; ++i) 
    {
        for(int j = 0; j < LEN; ++j)
        {
            cout << "[" << pow_one[i][j] << "]  ";
        }

        cout << endl;
    }

    cout << endl << "Power of 3" << endl;

    for(int i = 0; i < LEN; ++i) 
    {
        for(int j = 0; j < LEN; ++j)
        {
            cout << "[" << pow_three[i][j] << "]  ";
        }

        cout << endl;
    }
    
    cout << endl << "Power of 4" << endl;

    for(int i = 0; i < LEN; ++i) 
    {
        for(int j = 0; j < LEN; ++j)
        {
            cout << "[" << pow_four[i][j] << "]  ";
        }

        cout << endl;
    }


    return 0; 
}



package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 * Default constructor reads from a file
	 * @param inputFileName:	name of file to read a grid from
	 * @throws FileNotFoundException
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
		File file = new File(inputFileName);
		Scanner sc = new Scanner(file);
		
		int count = 0;

		// Read the number of lines in the file to get width 
		// since the grid is a square
		while (sc.hasNextLine())
		{
			count++;
			sc.nextLine();
		}
		sc.close();

		width = count;
		count = 0;

		grid = new Living[width][width];

		// Reset the scanner after getting width 
		sc = new Scanner(file);	
		
		// Loop through the strings seperated by whitespace
		while(sc.hasNext()) 
		{
			int age = 0;
			int x = count % width;
			int y = count / width;

			String s = sc.next();		
			
			// if the string has more than one char, it must
			// have an age number in the first index
			if(s.length() > 1)	
			{
				age = Character.getNumericValue(s.charAt(1));  
			}

			// Get the first char in the string to determine the
			// type of Livng is assigned to the grid at (x, y)
			switch(s.charAt(0))
			{
				case 'B':
					grid[y][x] = new Badger(this, y, x, age); 
					break;
				
				case 'F':
					grid[y][x] = new Fox(this, y, x, age); 
					break;
				
				case 'R':
					grid[y][x] = new Rabbit(this, y, x, age);
					break;
				
				case 'G':
					grid[y][x] = new Grass(this, y, x);
					break;

				case 'E':
					grid[y][x] = new Empty(this, y, x);
					break;
			}
			count++;
		}
	
		sc.close();		
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		width = w;
		grid = new Living[w][w];
	}
	
	
	public int getWidth()
	{ 
		return width;
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		
		int i, j;
		for(i = 0; i < width; i++)
		{
			for(j = 0; j < width; j++)
			{	
				// Random number to determine the Living object to create
				int next = generator.nextInt(20);	
				
				// 0 - 3 makes a badger
				if(next <= 3) 
				{
					grid[i][j] = new Badger(this, i, j, 0);
				}

				// 4 - 8 makes a fox
				else if(next <= 8) 
				{
					grid[i][j] = new Fox(this, i, j, 0);
				}
				
				// 9 - 13 makes a rabbit
				else if(next <= 13) 
				{	
					grid[i][j] = new Rabbit(this, i, j, 0);
				}
				
				// 14 - 17 makes grass
				else if(next <= 17) 
				{
					grid[i][j] = new Grass(this, i, j);
				}

				// Remaining, 18 and 19 make an empty space
				else
				{
					grid[i][j] = new Empty(this, i, j);
				}
			}
		}
	}	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.
	 * @return String	visual representation of the grid
	 */
	public String toString()
	{
		String plainString = ""; // Empty initial string
		
		int i, j;
		for(i = 0; i < width; i++)
		{
			for(j = 0; j < width; j++)
			{	
				// Check the state of the Living object at grid position (j, i)
				// to determine what characters will be shown
				switch(grid[i][j].who().ordinal())
				{

					case Living.BADGER:
						plainString += "B" + ((Animal)grid[i][j]).myAge() + " ";
						break;
					
					case Living.FOX:	
						plainString += "F" + ((Animal)grid[i][j]).myAge() + " ";
						break;
					
					case Living.RABBIT:
						plainString += "R" + ((Animal)grid[i][j]).myAge() + " ";
						break;
					
					case Living.GRASS:
						plainString += "G  ";
						break;
					
					case Living.EMPTY:
						plainString += "E  ";
						break;
				}
			}
			plainString += "\n"; // When moving down a row, adds a new line to string
		}
		return plainString; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @param outputFileName:	name of file to print the grid to	
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		File file = new File(outputFileName);
		
		// This creates a file if it isn't found, exception shouldn't be needed
		PrintWriter pw = new PrintWriter(file); 
		
		pw.print(this.toString()); 
		
		pw.close();
	}
}

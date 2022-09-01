package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		int i, j;
		for(i = 0; i < pOld.getWidth(); i++)
		{
			for(j = 0; j < pOld.getWidth(); j++)
			{
				pNew.grid[i][j] = pOld.grid[i][j].next(pNew);
			}
		}
	}

	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		int trial = 1, input = 0; // the number of trials and user input to determine grid type

		System.out.println("Simulation of Wildlife of the Plain\n"+
				"Keys: 1 (random plain) 2 (file input) 3 (exit)\n");

		Scanner sc = new Scanner(System.in); // Scanner to get user input

		while(input != 3)
		{
			int cycles, width; // the number of cycles and width of the grid
			String fileName;   // the name of the file containing a grid

			System.out.print("Trial " + trial + ": ");
			input = sc.nextInt();

			Plain even;		// the plain after an even number of cycles
			Plain odd;		// the plain after an odd number of cycles

			switch(input)
			{
				case 1:
					System.out.println("Random Plain");
					System.out.print("Enter grid width: ");
					width = sc.nextInt();
					even = new Plain(width);
					even.randomInit();
					break;

				case 2:
					System.out.println("Plain generated from file");
					System.out.print("Enter file name: ");
					fileName = sc.next();
					even = new Plain(fileName);
					break;

				case 3:
					continue; // Continue returns back to the while loop

				default:
					System.out.println("Invalid input\n");
					continue;
			}

			trial++;

			odd = new Plain(even.getWidth());

			System.out.print("Enter number of cycles: ");
			cycles = sc.nextInt();
			
			System.out.println("Initial Plain:\n" + even.toString());

			int i;
			for(i = 0; i < cycles; i++)
			{
				if(i % 2 == 0) 
					updatePlain(even, odd);
				
				else 
					updatePlain(odd, even);
			}
			
			System.out.println("Final plain:");

			if(cycles % 2 == 0) 
				System.out.println(even.toString());
		
			else 
				System.out.println(odd.toString());
			

			// Save grids after they going through all cycles
			// Askes first
			
/*			System.out.print("Do you want to save this plain (y/n): ");
			char save = sc.next().charAt(0);

			if(save == 'y' || save == 'Y')
			{
				System.out.print("Enter File Name: ");
				fileName = sc.next();
				
				if(cycles % 2 == 0) 
					even.write(fileName);

				else 
					odd.write(fileName);
			}
*/
		}
		sc.close();
	}
}

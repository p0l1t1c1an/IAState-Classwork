package edu.iastate.cs228.hw2;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		int trials = 0, input = 0;

		RotationalPointScanner[] scanners = new RotationalPointScanner[Algorithm.values().length]; 
		// Disliked having to define array length with a literal, if there needed to be more
		// algorithms added in the future then you would have to manually change this length
		
		Random generator = new Random();
		Scanner sc = new Scanner(System.in);	

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning\n"+
						   "keys: 1 (random integers) 2 (file input) 3 (exit)");
	
		while(input != 3) // Entering 3 quits
		{
			System.out.print("Trial "+ trials + ": ");

			if(sc.hasNextInt())
			{
				input = sc.nextInt();
			}
			else
			{
				sc.nextLine();
				continue;
			}
			
			if(input == 1) // Random Points
			{
				System.out.print("Randon Points\nEnter the number of random points: ");
				if(sc.hasNextInt())
				{
					int numPts = sc.nextInt(), count = 0;	
					Point pArr[] = generateRandomPoints(numPts, generator);

					// iterates through each value of in Algorithm enum
					for(Algorithm a : Algorithm.values())
					{
						scanners[count++] = new RotationalPointScanner(pArr, a);
					}
				}
			}	
			else if(input == 2) // Points from a file
			{
				System.out.print("Points from a file\nEnter the file name: ");
				if(sc.hasNext())
				{
					String file = sc.next(); 
					int count = 0;	
					
					// iterates through each value of in Algorithm enum
					for(Algorithm a : Algorithm.values())
					{
						scanners[count++] = new RotationalPointScanner(file, a);	
					}
				}
			}
			else if(input == 3) // Quit
			{
				continue; // Back to while loop condition
			}
			else
			{
				System.out.println("Invalid input. \nEnter a number from 1 - 3.");
				continue;
			}
		
			System.out.println("algorithm size time (ns)\n");
			
			for(RotationalPointScanner rps : scanners) // Not Rock Paper Scissors 
			{
				rps.scan();	
				rps.draw();
				System.out.println(rps.stats());

				//rps.writePointsToFile(); // Uncomment to test writing
			}
		
			System.out.println();
			trials++;
		}
		sc.close();
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * ([-50,50] , [-50,50]). Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if(numPts < 1)
			throw new IllegalArgumentException();

		Point pArr[] = new Point[numPts];
		
		int i;
		for(i = 0; i < numPts; ++i)
		{
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;
			
			pArr[i] = new Point(x, y);
		}

		return pArr;  
	}
}


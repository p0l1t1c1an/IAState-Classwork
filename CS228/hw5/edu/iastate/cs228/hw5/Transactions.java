package edu.iastate.cs228.hw5;


import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions 
{
	
	/**
	 * The main method generates a simulation of rental and return activities.  
	 *  
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println( "Transactions at a Video Store\n" +
				"keys:\t1 (rent)\t2 (bulk rent)\n" +          
				"\t3 (return)\t4 (bulk return)\n" +
				"\t5 (summary)\t6 (exit)");


		VideoStore vs = new VideoStore("videoList1.txt");
		int input = 0;

		while(input != 6)
		{
			System.out.print("\nTransactions: ");

			Scanner sc = new Scanner(System.in);
			if(sc.hasNextInt())
				input = sc.nextInt();
			else
			{
				sc.nextLine();
				continue;
			}

			sc.nextLine();
			String inputLine = ""; 

			try
			{
				switch(input)
				{
					case 1:
						System.out.print("Film to rent: ");
						inputLine = sc.nextLine();

						vs.videoRent(VideoStore.parseFilmName(inputLine), VideoStore.parseNumCopies(inputLine));	
						break;

					case 2:
						System.out.print("Video file (rent): ");
						inputLine = sc.nextLine();

						vs.bulkRent(inputLine);
						break;

					case 3:
						System.out.print("Film to return: ");
						inputLine = sc.nextLine();

						vs.videoReturn(VideoStore.parseFilmName(inputLine), VideoStore.parseNumCopies(inputLine));	
						break;

					case 4:
						System.out.print("Video file (return): ");
						inputLine = sc.nextLine();

						vs.bulkReturn(inputLine);
						break;

					case 5:
						System.out.print(vs.transactionsSummary());

					default:
						continue;
				}
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}

		}
	}
}

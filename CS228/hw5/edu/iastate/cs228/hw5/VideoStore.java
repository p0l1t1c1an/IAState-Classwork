package edu.iastate.cs228.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.iastate.cs228.hw5.Video; 

/**
 * 
 * @author Jacob Boicken
 *
 */

public class VideoStore 
{
	protected SplayTree<Video> inventory;     // all the videos at the store
	
	// ------------
	// Constructors 
	// ------------
	
    /**
     * Default constructor sets inventory to an empty tree. 
     */
    public VideoStore() 
	{
		inventory = new SplayTree<Video>();
	}
    
	/**
	 * Constructor accepts a video file to create its inventory.  Refer to Section 3.2 of  
	 * the project description for details regarding the format of a video file. 
	 * 
	 * Calls setUpInventory(). 
	 * 
	 * @param videoFile  no format checking on the file
	 * @throws FileNotFoundException
	 */
    public VideoStore(String videoFile) throws FileNotFoundException  
    {
		this();
    	setUpInventory(videoFile);
    }
    
    
   /**
     * Accepts a video file to initialize the splay tree inventory.  To be efficient, 
     * add videos to the inventory by calling the addBST() method, which does not splay. 
     * 
     * Refer to Section 3.2 for the format of video file. 
     * 
     * @param  videoFile  correctly formated if exists
     * @throws FileNotFoundException 
     */
    public void setUpInventory(String videoFile) throws FileNotFoundException
    {
		inventory.clear();
		bulkImport(videoFile);
    }
	
    
    // ------------------
    // Inventory Addition
    // ------------------
    
    /**
     * Find a Video object by film title. 
     * 
     * @param film
     * @return
     */
	public Video findVideo(String film) 
	{
		Video comp = new Video(film);
		for(Video v : inventory)
		{
			if(v.compareTo(comp) == 0)
				return v;
		}
		return null; 
	}


	/**
	 * Updates the splay tree inventory by adding a number of video copies of the film.  
	 * (Splaying is justified as new videos are more likely to be rented.) 
	 * 
	 * Calls the add() method of SplayTree to add the video object.  
	 * 
	 *     a) If true is returned, the film was not on the inventory before, and has been added.  
	 *     b) If false is returned, the film is already on the inventory. 
	 *     
	 * The root of the splay tree must store the corresponding Video object for the film. Update 
	 * the number of copies for the film.  
	 * 
	 * @param film  title of the film
	 * @param n     number of video copies 
	 */
	public void addVideo(String film, int n)  
	{
		Video v = new Video(film, n);

		if(!inventory.add(v))
		{
			Video entry = inventory.findElement(v);
			entry.addNumCopies(n);
		}
	}
	

	/**
	 * Add one video copy of the film. 
	 * 
	 * @param film  title of the film
	 */
	public void addVideo(String film)
	{
		addVideo(film, 1);
	}
	

	/**
     * Update the splay trees inventory by adding videos.  Perform binary search additions by 
     * calling addBST() without splaying. 
     * 
     * The videoFile format is given in Section 3.2 of the project description. 
     * 
     * @param videoFile  correctly formated if exists 
     * @throws FileNotFoundException
     */
    public void bulkImport(String videoFile) throws FileNotFoundException 
    {
		File file = new File(videoFile);
		Scanner sc = new Scanner(file);

		while(sc.hasNextLine())
		{
			int num;
			String line = sc.nextLine();
			
			if(line.indexOf('(') == -1 || line.indexOf(')') == -1)
			{
				num = 1;
			}
			else
			{
				num = parseNumCopies(line);
			}
	
			inventory.addBST(new Video(parseFilmName(line), num)); 			
		}
    }

    
    // ----------------------------
    // Video Query, Rental & Return 
    // ----------------------------
    
	/**
	 * Search the splay tree inventory to determine if a video is available. 
	 * 
	 * @param  film
	 * @return true if available
	 */
	public boolean available(String film)
	{
		Video entry = inventory.findElement(new Video(film));
		if(entry == null)
			return false;

		return entry.getNumAvailableCopies() > 0; 
	}

	
	
	/**
     * Update inventory. 
     * 
     * Search if the film is in inventory by calling findElement(new Video(film, 1)). 
     * 
     * If the film is not in inventory, prints the message "Film <film> is not 
     * in inventory", where <film> shall be replaced with the string that is the value 
     * of the parameter film.  If the film is in inventory with no copy left, prints
     * the message "Film <film> has been rented out".
     * 
     * If there is at least one available copy but n is greater than the number of 
     * such copies, rent all available copies. In this case, no AllCopiesRentedOutException
     * is thrown.  
     * 
     * @param film   
     * @param n 
     * @throws IllegalArgumentException      if n <= 0 or film == null or film.isEmpty()
	 * @throws FilmNotInInventoryException   if film is not in the inventory
	 * @throws AllCopiesRentedOutException   if there is zero available copy for the film.
	 */
	public void videoRent(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException,  
									     			 AllCopiesRentedOutException 
	{
		if(n <= 0 || film == null || film.isEmpty())
			throw new IllegalArgumentException("Film " + film + " has an invalid request\n");

		Video entry = inventory.findElement(new Video(film));
		if(entry == null)
			throw new FilmNotInInventoryException("Film " + film + " is not in inventory\n");
		else if(entry.getNumAvailableCopies() <= 0)	
			throw new AllCopiesRentedOutException("Film " + film + " has been rented out\n");
		else
			entry.rentCopies(n);
	}

	
	/**
	 * Update inventory.
	 * 
	 *    1. Calls videoRent() repeatedly for every video listed in the file.  
	 *    2. For each requested video, do the following: 
	 *       a) If it is not in inventory or is rented out, an exception will be 
	 *          thrown from videoRent().  Based on the exception, prints out the following 
	 *          message: "Film <film> is not in inventory" or "Film <film> 
	 *          has been rented out." In the message, <film> shall be replaced with 
	 *          the name of the video. 
	 *       b) Otherwise, update the video record in the inventory.
	 * 
	 * For details on handling of multiple exceptions and message printing, please read Section 3.4 
	 * of the project description. 
	 *       
	 * @param videoFile  correctly formatted if exists
	 * @throws FileNotFoundException
     * @throws IllegalArgumentException     if the number of copies of any film is <= 0
	 * @throws FilmNotInInventoryException  if any film from the videoFile is not in the inventory 
	 * @throws AllCopiesRentedOutException  if there is zero available copy for some film in videoFile
	 */
	public void bulkRent(String videoFile) throws FileNotFoundException, IllegalArgumentException, 
												  FilmNotInInventoryException, AllCopiesRentedOutException 
	{	

		File file = new File(videoFile);
		Scanner sc = new Scanner(file);
		String msg = "";
		boolean most_most_important[] = { false, false, false, false };

		while(sc.hasNextLine())
		{
			int num;
			String line = sc.nextLine();

			if(line.indexOf('(') == -1 || line.indexOf(')') == -1)
			{
				num = 1;
			}
			else
			{
				num = parseNumCopies(line);
			}
			
			try
			{
				videoRent(parseFilmName(line), num); 
			}
			catch(Exception e)
			{
				msg += e.getMessage();
				String except = e.getClass().getName();

				if(except.contains("File"))
					most_most_important[0] = true;		 

				else if(except.contains("Illeg"))
					most_most_important[1] = true;		 

				else if(except.contains("Film"))
					most_most_important[2] = true;	

				else if(except.contains("AllCo"))
					most_most_important[3] = true;		 
			}
		}


		if(most_most_important[0])
			throw new FileNotFoundException(msg);

		else if(most_most_important[1])
			throw new IllegalArgumentException(msg);

		else if(most_most_important[2])
			throw new FilmNotInInventoryException(msg);

		else if(most_most_important[3])
			throw new AllCopiesRentedOutException(msg);
	}


	/**
	 * Update inventory.
	 * 
	 * If n exceeds the number of rented video copies, accepts up to that number of rented copies
	 * while ignoring the extra copies. 
	 * 
	 * @param film
	 * @param n
	 * @throws IllegalArgumentException     if n <= 0 or film == null or film.isEmpty()
	 * @throws FilmNotInInventoryException  if film is not in the inventory
	 */
	public void videoReturn(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException 
	{
		if(n <= 0 || film == null || film.isEmpty())
		{
			throw new IllegalArgumentException("Film " + film + " has an invalid request\n");
		}

		Video entry = inventory.findElement(new Video(film));

		if(entry == null)
			throw new FilmNotInInventoryException("Film " + film + " is not in inventory\n");
		else
			entry.returnCopies(n);
	}


	/**
	 * Update inventory. 
	 * 
	 * Handles excessive returned copies of a film in the same way as videoReturn() does.  See Section 
	 * 3.4 of the project description on how to handle multiple exceptions. 
	 * 
	 * @param videoFile
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException    if the number of return copies of any film is <= 0
	 * @throws FilmNotInInventoryException if a film from videoFile is not in inventory
	 */
	public void bulkReturn(String videoFile) throws FileNotFoundException, IllegalArgumentException,
		   FilmNotInInventoryException												
		   {
			   File file = new File(videoFile);
			   Scanner sc = new Scanner(file);
			   String msg = "";
			   boolean most_most_important[] = { false, false, false };


			   while(sc.hasNextLine())
			   {
				   int num;
				   String line = sc.nextLine();

				   if(line.indexOf('(') == -1 || line.indexOf(')') == -1)
				   {
					   num = 1;
				   }
				   else
				   {
					   num = parseNumCopies(line);
				   }

				   try
				   {
					   videoReturn(parseFilmName(line), num); 
				   }
				   catch(Exception e)
				   {
					   msg += e.getMessage();

					   String except = e.getClass().getName();

					   if(except.contains("File"))
						   most_most_important[0] = true;		 

					   else if(except.contains("Illeg"))
						   most_most_important[1] = true;		 

					   else if(except.contains("Film"))
						   most_most_important[2] = true;	
				   }
			   }

			   if(most_most_important[0])
				   throw new FileNotFoundException(msg);

			   else if(most_most_important[1])
				   throw new IllegalArgumentException(msg);

			   else if(most_most_important[2])
				   throw new FilmNotInInventoryException(msg);
		   }		


	// ------------------------
	// Methods without Splaying
	// ------------------------

	/**
	 * Performs inorder traversal on the splay tree inventory to list all the videos by film 
	 * title, whether rented or not.  Below is a sample string if printed out: 
	 * 
	 * 
	 * Films in inventory: 
	 * 
	 * A Streetcar Named Desire (1) 
	 * Brokeback Mountain (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (5) 
	 * Taxi Driver (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	public String inventoryList()
	{
		String retStr = "Films in inventory:\n\n";
		for(Video v : inventory)
		{
			retStr += v.getFilm() + " (" + v.getNumCopies() + ")\n";
		}
		return retStr; 
	}


	/**
	 * Calls rentedVideosList() and unrentedVideosList() sequentially.  For the string format, 
	 * see Transaction 5 in the sample simulation in Section 4 of the project description. 
	 *   
	 * @return 
	 */
	public String transactionsSummary()
	{ 
		return rentedVideosList() + "\n" + unrentedVideosList(); 
	}	

	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * Rented films: 
	 * 
	 * Brokeback Mountain (1)
	 * Forrest Gump (1) 
	 * Singin' in the Rain (2)
	 * The Godfather (1)
	 * 
	 * 
	 * @return
	 */
	private String rentedVideosList()
	{
		String retStr = "Rented films:\n\n";
		for(Video v : inventory)
		{
			if(v.getNumRentedCopies() > 0)
				retStr += v.getFilm() + " (" + v.getNumRentedCopies() + ")\n";
		}
		return retStr; 
	}


	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * Prints only the films that have unrented copies. 
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Films remaining in inventory:
	 * 
	 * A Streetcar Named Desire (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Slumdog Millionaire (4) 
	 * Taxi Driver (1) 
	 * 
	 * 
	 * @return
	 */
	private String unrentedVideosList()
	{
		String retStr = "Films remaining in inventory:\n\n";
		for(Video v : inventory)
		{
			if(v.getNumAvailableCopies() > 0)
				retStr += v.getFilm() + " (" + v.getNumAvailableCopies() + ")\n";
		}
		return retStr; 
	}	


	/**
	 * Parse the film name from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static String parseFilmName(String line) 
	{
		if(!line.contains("("))
			return line.trim();

		return line.substring(0, line.indexOf('(')).trim(); 
	}


	/**
	 * Parse the number of copies from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static int parseNumCopies(String line) 
	{ 
		if(line.contains("("))
			return Integer.parseInt(line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim()); 
		return 1;
	}
}

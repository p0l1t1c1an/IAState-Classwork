package edu.iastate.cs228.hw1;

/**
 *
 * @author Jacob Boicken
 *
 */

/**
  *
 * Living refers to the life form occupying a square in a plain grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	protected Plain plain; // the plain in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	/**
     * Constructor
     * @param p: plain
     * @param r: row position
     * @param c: column position
     */

	public Living(Plain p, int r, int c)
	{
		plain = p;
		row = r;
		column = c;
	}

	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain. 
	 * @param population  counts of all life forms
	 */
	protected void census(int[] population)
	{		
		// Counts the numbers of Badgers, Empties, Foxes, Grasses, and Rabbits  
		// in the 3x3 neighborhood centered at this Living object. Stores the 
		// counts in the array population[] at indices 0, 1, 2, 3, 4, respectively. 
		
		int i, j;
		for(i = row-1; i <= row+1; i++)
		{
			if(i >= 0 && i < plain.getWidth())
			{
				for(j = column-1; j <= column+1; j++)
				{
					if(j >= 0 && j < plain.getWidth())
					{
						int index = plain.grid[i][j].who().ordinal();
						population[index] += 1;
					}
				}
			}
		}
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  pNew  plain of the next cycle
	 * @return Living 
	 */
	public abstract Living next(Plain pNew);
}

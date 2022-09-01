package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	/**
     * Constructor
     * @param p: plain
     * @param r: row position
     * @param c: column position
     */

	public Empty (Plain p, int r, int c) 
	{
		super(p, r, c);
	}
	
	/**
	 * Empty Space occupies the square.
	 */

	public State who()
	{ 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);

		Living newLife = null;
		
		// Rabbits get first dibbs on empty land 
		if(pop[RABBIT] > 1)
		{
			newLife = new Rabbit(pNew, row, column, 0);
		}

		// Next the foxes get the space
		else if(pop[FOX] > 1)
		{
			newLife = new Fox(pNew, row, column, 0);
		}

		// Then the Badgers takes the empty space
		else if(pop[BADGER] > 1)
		{
			newLife = new Badger(pNew, row, column, 0);	
		}

		// if nonw of the animals are around, then grass gets the area
		else if(pop[GRASS] >= 1)
		{	
			newLife = new Grass(pNew, row, column);	
		}

		// If its surrounded by empty space then the space is still empty
		else
		{
			this.plain = pNew;
			newLife = this;
		}
		
		return newLife; 
	}
}

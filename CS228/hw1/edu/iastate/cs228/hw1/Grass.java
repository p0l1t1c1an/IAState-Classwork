package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	/**
     * Constructor
     * @param p: plain
     * @param r: row position
     * @param c: column position
     */

	public Grass (Plain p, int r, int c) 
	{
		super(p, r, c);
	}

	/**
	 * Only grass occupies the square. 	 
	 */
	public State who()
	{ 
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);

		Living newLife = null;
		
		// Rabbits are 3 times as populous as grass and overeat
		if(pop[RABBIT] >= (pop[GRASS] * 3))
		{
			newLife = new Empty(pNew, row, column);
		}

		// Enough rabbits take over the grass without overeating 
		else if(pop[RABBIT] >= 3)
		{
			newLife = new Rabbit(pNew, row, column, 0);
		}

		// The grass lives on 
		else
		{
			this.plain = pNew;
			newLife = this;
		}
		
		return newLife;  	
	}
}

package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{ 
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);

		Living newLife = null;
		
		// Dies of old age
		if(this.myAge() >= FOX_MAX_AGE)
		{
			newLife = new Empty(pNew, row, column);
		}

		// Badgers eat the foxes
		else if(pop[BADGER] > pop[FOX])
		{
			newLife = new Badger(pNew, row, column, 0);
		}

		// Badger and Foxes overeat their food source
		else if((pop[BADGER] + pop[FOX]) > pop[RABBIT])
		{
			newLife = new Empty(pNew, row, column);	
		}

		// Lives on and ages up
		else
		{
			this.age++;
			this.plain = pNew;
			newLife = this;
		}
		
		return newLife; 
	}
}

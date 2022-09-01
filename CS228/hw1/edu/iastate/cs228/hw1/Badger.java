package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
	
	/**
	 * A badger occupies the square. 
	 */
	public State who()
	{ 
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop); 

		Living newLife = null;
		
		// Dies of old age
		if(this.myAge() >= BADGER_MAX_AGE)
		{
			newLife = new Empty(pNew, row, column);
		}

		// Alone and killed by gang of foxes
		else if(pop[BADGER] == 1 && pop[FOX] > 1)
		{
			newLife = new Fox(pNew, row, column, 0);
		}

		// Foxes and badgers over eat their food source
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

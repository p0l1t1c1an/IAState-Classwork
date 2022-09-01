package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jacob Boicken
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit(Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
		
	// Rabbit occupies the square.
	public State who()
	{ 
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);

		Living newLife = null;
		
		// Dies of old age
		if(this.myAge() >= RABBIT_MAX_AGE)
		{
			newLife = new Empty(pNew, row, column);
		}

		// Dies without food
		else if(pop[GRASS] == 0)
		{
			newLife = new Empty(pNew, row, column);
		}

		// Foxes are more populous and
		// Foxes and Badgers outnumber rabbits
		else if((pop[BADGER] < pop[FOX]) && ((pop[BADGER] + pop[FOX]) >= pop[RABBIT]))
		{
			newLife = new Fox(pNew, row, column, 0);	
		}

		// Badgers are more populous than rabbits 
		else if(pop[BADGER] > pop[RABBIT])
		{
			newLife = new Badger(pNew, row, column, 0);	
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

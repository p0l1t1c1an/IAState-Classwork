package edu.iastate.cs228.hw3;

/**
 *  
 * @author Jacob Boicken
 *
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class PrimeFactorization implements Iterable<PrimeFactor>
{
	private static final long OVERFLOW = -1;
	private long value; 	// the factored integer 
	// it is set to OVERFLOW when the number is greater than 2^63-1, the
	// largest number representable by the type long. 

	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;

	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;

	private int size;     	// number of distinct prime factors


	// ------------
	// Constructors 
	// ------------

	/**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{	 
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		size = 0;
		value = 1;
	}


	/** 
	 * Obtains the prime factorization of n and creates a doubly linked list to store the result.   
	 * Follows the direct search factorization algorithm in Section 1.2 of the project description. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization(long n) throws IllegalArgumentException 
	{
		this();

		if(n < 1)
			throw new IllegalArgumentException();

		if(isPrime(n)){
			link(tail.previous, new Node((int)n, 1));
			++size;
			value = n;
			return;
		}

		int count = 0;
		while(n % 2 == 0)
		{
			n /= 2;
			++count;
		}

		if(count > 0)
		{
			link(tail.previous, new Node(2, count));
			++size;
		}

		long m = n;
		for(int i = 3; i*i <= n; i+=2)
		{
			if(m % i == 0)
			{
				count = 0;	
				do
				{
					m /= i;
					++count;
				}
				while(m % i == 0);

				link(tail.previous, new Node(i, count));
				++size;
			}
		}
		updateValue();
	}


	/**
	 * Copy constructor. It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		this();

		for(PrimeFactor p : pf)
		{
			link(tail.previous, new Node(p.prime, p.multiplicity));	
			++size;
		}	
		updateValue();
	}

	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		this();

		for(PrimeFactor p : pfList)
		{
			link(tail.previous, new Node(p.prime, p.multiplicity));
			++size;
		}
		updateValue();
	}


	// --------------
	// Primality Test
	// --------------

	/**
	 * Test if a number is a prime or not.  Check iteratively from 2 to the largest 
	 * integer not exceeding the square root of n to see if it divides n. 
	 * 
	 *@param n
	 *@return true if n is a prime 
	 * 		  false otherwise 
	 */
	public static boolean isPrime(long n) 
	{
		if(n == 1) return false;
		if(n == 2) return true;
		if(n % 2 == 0) return false;

		int i;
		for(i = 3; i*i <= n; i+=2)
		{
			if(n % i == 0)
				return false;
		}

		return true; 
	}   


	// ---------------------------
	// Multiplication and Division 
	// ---------------------------

	/**
	 * Multiplies the integer v represented by this object with another number n.  Note that v may 
	 * be too large (in which case this.value == OVERFLOW). You can do this in one loop: Factor n and 
	 * traverse the doubly linked list simultaneously. For details refer to Section 3.1 in the 
	 * project description. Store the prime factorization of the product. Update value and size. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public void multiply(long n) throws IllegalArgumentException 
	{
		if(n < 1)
			throw new IllegalArgumentException();

		int count = 0;
		PrimeFactorizationIterator iter = iterator();

		while(n % 2 == 0)
		{
			n /= 2;
			++count;
		}

		if(count != 0)
		{
			iter.add(new PrimeFactor(2, count)); // 2 is the first one
		}

		long m = n;
		for(int i = 3; i*i <= n; i+=2)
		{
			if(m % i == 0)
			{
				count = 0;
				do
				{
					m /= i;
					++count;
				}
				while(m % i == 0);

				if(count != 0)
				{	
					while(iter.cursor != tail && iter.cursor.pFactor.prime < i)
					{
						if(iter.hasNext())
							iter.next();
					}
				
					iter.add(new PrimeFactor(i, count));
				}
			}
		}
		updateValue();
	}

	/**
	 * Multiplies the represented integer v with another number in the factorization form.  Traverse both 
	 * linked lists and store the result in this list object.  See Section 3.1 in the project description 
	 * for details of algorithm. 
	 * 
	 * @param pf 
	 */
	public void multiply(PrimeFactorization pf)
	{
		if(pf.value == 1)
			return;

		PrimeFactorizationIterator iter1 = this.iterator();
		PrimeFactorizationIterator iter2 = pf.iterator();

		PrimeFactor curr2 = iter2.next();
		while(iter1.hasNext())
		{
			PrimeFactor curr1 = iter1.next();
			if(curr2.prime <= curr1.prime)
			{
				iter1.previous(); // pull cursor back one to add curr2 at curr1's position
				iter1.add(curr2);

				if(iter2.hasNext())
					curr2 = iter2.next();
				else
					break;
			}
		}
		updateValue();
	}


	/**
	 * Multiplies the integers represented by two PrimeFactorization objects.  
	 * 
	 * @param pf1
	 * @param pf2
	 * @return object of PrimeFactorization to represent the product 
	 */
	public static PrimeFactorization multiply(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		PrimeFactorization copy = new PrimeFactorization(pf1);
		copy.multiply(pf2); 
		return copy; 
	}


	/**
	 * Divides the represented integer v by n.  Make updates to the list, value, size if divisible.  
	 * No update otherwise. Refer to Section 3.2 in the project description for details. 
	 *  
	 * @param n
	 * @return  true if divisible 
	 *          false if not divisible 
	 * @throws IllegalArgumentException if n <= 0
	 */
	public boolean dividedBy(long n) throws IllegalArgumentException
	{
		if(value != -1 && value < n)
			return false;

		return dividedBy(new PrimeFactorization(n));
	}


	/**
	 * Division where the divisor is represented in the factorization form.  Update the linked 
	 * list of this object accordingly by removing those nodes housing prime factors that disappear  
	 * after the division.  No update if this number is not divisible by pf. Algorithm details are 
	 * given in Section 3.2. 
	 * 
	 * @param pf
	 * @return	true if divisible by pf
	 * 			false otherwise
	 */
	public boolean dividedBy(PrimeFactorization pf)
	{
		if(pf.value == 1)
			return true;

		// value < pf.value is only checked if pf.value != -1
		if(value == 1 || (value != -1 && (pf.value == -1 || value < pf.value))) 
			return false; 

		if(value == pf.value)
		{
			clearList();
			value = 1;
			return true;
		}

		PrimeFactorization copy = new PrimeFactorization(this);
		PrimeFactorizationIterator iterCopy = copy.iterator();
		PrimeFactorizationIterator iterPf   = pf.iterator();

		PrimeFactor copyPrime, pfPrime = iterPf.next();

		do
		{
			copyPrime = iterCopy.next();

			if(copyPrime.prime >= pfPrime.prime)
			{
				if(copyPrime.prime == pfPrime.prime)
				{
					if(copyPrime.multiplicity >= pfPrime.multiplicity)
					{
						copyPrime.multiplicity -= pfPrime.multiplicity;
						if(copyPrime.multiplicity == 0)
						{
							iterCopy.remove();
							--size;
						}
						if(iterPf.hasNext())
							pfPrime = iterPf.next();
						else
							break;
					}
					else
						return false;
				}
				else
					return false;
			}
		}
		while(iterCopy.hasNext());

		if(iterPf.hasNext())
			return false;

		clearList();
		head = copy.head;
		tail = copy.tail;
		updateValue();

		return true;
	}


	/**
	 * Divide the integer represented by the object pf1 by that represented by the object pf2. 
	 * Return a new object representing the quotient if divisible. Do not make changes to pf1 and 
	 * pf2. No update if the first number is not divisible by the second one. 
	 *  
	 * @param pf1
	 * @param pf2
	 * @return quotient as a new PrimeFactorization object if divisible
	 *         null otherwise 
	 */
	public static PrimeFactorization dividedBy(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		PrimeFactorization copy = new PrimeFactorization(pf1);
		if(copy.dividedBy(pf2))
			return copy; 
		return null;
	}


	// -----------------------
	// Greatest Common Divisor
	// -----------------------

	/**
	 * Computes the greatest common divisor (gcd) of the represented integer v and an input integer n.
	 * Returns the result as a PrimeFactor object.  Calls the method Euclidean() if 
	 * this.value != OVERFLOW.
	 *     
	 * It is more efficient to factorize the gcd than n, which can be much greater. 
	 *     
	 * @param n
	 * @return prime factorization of gcd
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization gcd(long n) throws IllegalArgumentException
	{ 
		return new PrimeFactorization(Euclidean(value, n)); 
	}


	/**
	 * Implements the Euclidean algorithm to compute the gcd of two natural numbers m and n. 
	 * The algorithm is described in Section 4.1 of the project description. 
	 * 
	 * @param m
	 * @param n
	 * @return gcd of m and n. 
	 * @throws IllegalArgumentException if m < 1 or n < 1
	 */
	public static long Euclidean(long m, long n) throws IllegalArgumentException
	{
		if(m < 1 || n < 1)
			throw new IllegalArgumentException();

		if(n > m)
		{
			long tempM = m;
			m = n;
			n = tempM;
		}

		long remainder = 0;
		do
		{
			remainder = m % n;			
			m = n;
			n = remainder;
		}
		while(remainder != 0);

		return m; 
	}


	/**
	 * Computes the gcd of the values represented by this object and pf by traversing the two lists.  No 
	 * direct computation involving value and pf.value. Refer to Section 4.2 in the project description 
	 * on how to proceed.  
	 * 
	 * @param  pf
	 * @return prime factorization of the gcd
	 */
	public PrimeFactorization gcd(PrimeFactorization pf)
	{
		if(pf.value == 1 || value == 1) // if either are 1 then gcd is 1 
		{
			return new PrimeFactorization();
		}

		PrimeFactorizationIterator iter1 = iterator();
		PrimeFactorizationIterator iter2 = pf.iterator();

		PrimeFactor curr1 = iter1.next(), curr2 = iter2.next();

		PrimeFactorization retPf = new PrimeFactorization();
		boolean loop = true;

		do
		{
			if(curr1.prime < curr2.prime)
			{
				if(iter1.hasNext())	
					curr1 = iter1.next();
				else
					loop = false;
			}
			else if(curr1.prime > curr2.prime)
			{
				if(iter2.hasNext())	
					curr2 = iter2.next();
				else
					loop = false;
			}
			else
			{
				retPf.link(retPf.tail.previous, new Node(curr2.prime, 
							Math.min(curr1.multiplicity, curr2.multiplicity)));

				if(iter1.hasNext() && iter2.hasNext())	
				{
					curr1 = iter1.next();
					curr2 = iter2.next();
				}
				else
					loop = false;
			}
		}
		while(loop);

		retPf.updateValue();
		return retPf; 
	}


	/**
	 * 
	 * @param pf1
	 * @param pf2
	 * @return prime factorization of the gcd of two numbers represented by pf1 and pf2
	 */
	public static PrimeFactorization gcd(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		return pf1.gcd(pf2); 
	}

	// ------------
	// List Methods
	// ------------

	/**
	 * Traverses the list to determine if p is a prime factor. 
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p  
	 * @return true  if p is a prime factor of the number v represented by this linked list
	 *         false otherwise 
	 * @throws IllegalArgumentException if p is not a prime
	 */
	public boolean containsPrimeFactor(int p) throws IllegalArgumentException
	{
		if(!isPrime(p)) throw new IllegalArgumentException(); 
		for(PrimeFactor pf : this)
		{
			if(pf.prime == p) 
				return true;
		}

		return false; 
	}

	// The next two methods ought to be private but are made public for testing purpose. Keep
	// them public 

	/**
	 * Adds a prime factor p of multiplicity m.  Search for p in the linked list.  If p is found at 
	 * a node N, add m to N.multiplicity.  Otherwise, create a new node to store p and m. 
	 *  
	 * Precondition: p is a prime. 
	 * 
	 * @param p  prime 
	 * @param m  multiplicity
	 * @return   true  if m >= 1
	 *           false if m < 1   
	 */
	public boolean add(int p, int m) 
	{
		if(m < 1)
		{
			return false; 
		}

		PrimeFactorizationIterator iter = iterator();
		if(size == 0)
		{
			iter.add(new PrimeFactor(p, m));
		}
		else
		{
			while(iter.hasNext())
			{
				PrimeFactor curr = iter.next();
				if(curr.prime >= p)
				{
					break;
				}
			}
		}	
		iter.add(new PrimeFactor(p, m));
		updateValue();
		return true;
	}


	/**
	 * Removes m from the multiplicity of a prime p on the linked list.  It starts by searching 
	 * for p.  Returns false if p is not found, and true if p is found. In the latter case, let 
	 * N be the node that stores p. If N.multiplicity > m, subtracts m from N.multiplicity.  
	 * If N.multiplicity <= m, removes the node N.  
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p
	 * @param m
	 * @return true  when p is found. 
	 *         false when p is not found. 
	 * @throws IllegalArgumentException if m < 1
	 */
	public boolean remove(int p, int m) throws IllegalArgumentException
	{
		if(m < 1) throw new IllegalArgumentException();
		PrimeFactorizationIterator iter = iterator();

		while(iter.hasNext())
		{
			PrimeFactor curr = iter.next();
			if(curr.prime == p)
			{
				if(curr.multiplicity < m)
				{
					curr.multiplicity -= m;
				}
				else
				{
					iter.remove();
				}
				updateValue();
				return true;
			}
		}
		return false; 
	}


	/**
	 * 
	 * @return size of the list
	 */
	public int size() 
	{
		return size; 
	}


	/**
	 * Writes out the list as a factorization in the form of a product. Represents exponentiation 
	 * by a caret.  For example, if the number is 5814, the returned string would be printed out 
	 * as "2 * 3^2 * 17 * 19". 
	 */
	@Override 
	public String toString()
	{
		if(size == 0) return "1";

		String retStr = "";

		PrimeFactorizationIterator iter = iterator();

		while(iter.nextIndex() < size -1)
		{
			PrimeFactor curr = iter.next();
			retStr += curr.toString() + " * ";
		}

		retStr += iter.next().toString();

		return retStr; 
	}


	// The next three methods are for testing, but you may use them as you like.  

	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within 
	 *              long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow() {
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value() {
		return value;
	}


	public PrimeFactor[] toArray() {
		PrimeFactor[] arr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this)
			arr[i++] = pf;
		return arr;
	}


	@Override
	public PrimeFactorizationIterator iterator()
	{
		return new PrimeFactorizationIterator();
	}

	/**
	 * Doubly-linked node type for this class.
	 */
	private class Node 
	{
		public PrimeFactor pFactor;			// prime factor 
		public Node next;
		public Node previous;

		/**
		 * Default constructor for creating a dummy node.
		 */
		public Node()
		{
			pFactor = null;
		}

		/**
		 * Precondition: p is a prime
		 * 
		 * @param p	 prime number 
		 * @param m  multiplicity 
		 * @throws IllegalArgumentException if m < 1 
		 */
		public Node(int p, int m) throws IllegalArgumentException 
		{	
			pFactor = new PrimeFactor(p, m);
		}   


		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf
		 */
		public Node(PrimeFactor pf)  
		{
			pFactor = pf;
		}


		/**
		 * Printed out in the form: prime + "^" + multiplicity.  For instance "2^3". 
		 * Also, deal with the case pFactor == null in which a string "dummy" is 
		 * returned instead.  
		 */
		@Override
		public String toString() 
		{
			String retStr;

			if(pFactor == null)
			{
				retStr = "dummy";
			}
			else
			{
				retStr = pFactor.toString(); 
			}

			return retStr; 
		}
	}


	private class PrimeFactorizationIterator implements ListIterator<PrimeFactor>
	{  	
		// Class invariants: 
		// 1) logical cursor position is always between cursor.previous and cursor
		// 2) after a call to next(), cursor.previous refers to the node just returned 
		// 3) after a call to previous() cursor refers to the node just returned 
		// 4) index is always the logical index of node pointed to by cursor

		private Node cursor = head.next;
		private Node pending = null;    // node pending for removal
		private int index = 0;      

		// other instance variables ... 


		/**
		 * Default constructor positions the cursor before the smallest prime factor.
		 */
		public PrimeFactorizationIterator()
		{
			cursor = head.next; // Small is the first one (This is unnecessary) could be empty constructor
			index = 0;
		}

		@Override
		public boolean hasNext()
		{
			return cursor != tail;
		}


		@Override
		public boolean hasPrevious()
		{
			return cursor.previous != head;
		}


		@Override 
		public PrimeFactor next() throws NoSuchElementException
		{
			if(!hasNext()) 
				throw new NoSuchElementException();
			pending = cursor;
			cursor = cursor.next;
			++index;
			return pending.pFactor; 
		}


		@Override 
		public PrimeFactor previous() throws NoSuchElementException 
		{
			if(!hasPrevious()) 
				throw new NoSuchElementException();
			pending = cursor;
			cursor = cursor.previous;
			--index; 
			return cursor.pFactor; 
		}


		/**
		 *  Removes the prime factor returned by next() or previous()
		 *  
		 *  @throws IllegalStateException if pending == null 
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if(pending == null)
				throw new IllegalStateException();
			if(pending == cursor.previous) --index;
			unlink(pending);
			--size;
		}


		/**
		 * Adds a prime factor at the cursor position.  The cursor is at a wrong position 
		 * in either of the two situations below: 
		 * 
		 *    a) pf.prime < cursor.previous.pFactor.prime if cursor.previous != head. 
		 *    b) pf.prime > cursor.pFactor.prime if cursor != tail. 
		 * 
		 * Take into account the possibility that pf.prime == cursor.pFactor.prime. 
		 * 
		 * Precondition: pf.prime is a prime. 
		 * 
		 * @param pf  
		 * @throws IllegalArgumentException if the cursor is at a wrong position. 
		 */
		@Override
		public void add(PrimeFactor pf) throws IllegalArgumentException 
		{
			if(cursor.previous != head && pf.prime < cursor.previous.pFactor.prime) 
				throw new IllegalArgumentException();

			else if(cursor != tail && pf.prime > cursor.pFactor.prime)
				throw new IllegalArgumentException();

			if(cursor != tail && pf.prime == cursor.pFactor.prime)
			{
				cursor.pFactor.multiplicity += pf.multiplicity;
			}
			else
			{
				link(cursor.previous, new Node(pf));
				++size;
				++index;
			}
		}


		@Override
		public int nextIndex() 
		{
			return index;
		}


		@Override
		public int previousIndex() 
		{
			return index - 1;
		}

		@Deprecated
		@Override
		public void set(PrimeFactor pf) 
		{
			throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support set method");
		}

		// Other methods you may want to add or override that could possibly facilitate 
		// other operations, for instance, addition, access to the previous element, etc.
		// 
		// ...
		// 
	}


	// --------------
	// Helper methods 
	// -------------- 

	/**
	 * Inserts toAdd into the list after current without updating size.
	 * 
	 * Precondition: current != null, toAdd != null
	 */
	private void link(Node current, Node toAdd)
	{
		toAdd.next = current.next;
		toAdd.next.previous = toAdd;
		current.next = toAdd; 
		toAdd.previous = current;
	}

	/**
	 * Removes toRemove from the list without updating size.
	 */
	private void unlink(Node toRemove)
	{
		toRemove.next.previous = toRemove.previous;
		toRemove.previous.next = toRemove.next;
	}

	/**
	 * Remove all the nodes in the linked list except the two dummy nodes. 
	 * 
	 * Made public for testing purpose.  Ought to be private otherwise. 
	 */
	public void clearList()
	{
		head.next = tail;
		tail.previous = head;
	}	

	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is throw, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 */
	private void updateValue()
	{
		value = 1;

		try {	
			for(PrimeFactor pf : this)
			{
				for(int i = 0; i < pf.multiplicity; ++i)
				{
					value = Math.multiplyExact(value, pf.prime);
				}
			}
		} 	
		catch (ArithmeticException e) 
		{
			value = OVERFLOW;
		}
	}
}

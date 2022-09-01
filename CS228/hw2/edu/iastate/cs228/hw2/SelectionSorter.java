package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{	
		super(pts); 
		algorithm = "Selection Sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int i, j;
		for(i = 0; i < points.length; ++i)
		{
			int smallest = i, compareInt;
			for(j = i; j < points.length; ++j)
			{
				if(usingComparator)
                {
                    compareInt = pointComparator.compare(points[smallest], points[j]);
                }
                else
                {
                    compareInt = points[smallest].compareTo(points[j]);
                }

				if(compareInt > 0) // smaller point at j than at smallest
				{
					smallest = j;
				}
			}
			swap(smallest, i);
		}
	}
	
}

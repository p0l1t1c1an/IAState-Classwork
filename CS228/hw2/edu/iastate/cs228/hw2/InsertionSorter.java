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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "Insertion Sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{	
		int i, j, compareInt;
		for(i = 0; i < points.length; ++i)
		{	
			Point temp = points[i];
			for(j = i-1; j >= 0; --j)
			{
				if(usingComparator)
				{
					compareInt = pointComparator.compare(temp, points[j]);
				}
				else
				{
					compareInt = temp.compareTo(points[j]);
				}

				if(compareInt < 0) //temp is smaller
				{
					points[j+1] = points[j];
				}
				else
				{
					break;
				}
			}
			points[j+1] = temp;
		}
	}		
}

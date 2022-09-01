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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{ 
		super(pts); 
		algorithm = "Quick Sort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length-1); 
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first < last)
		{
			int part = partition(first, last);
			
			quickSortRec(first, part-1);
			quickSortRec(part+1, last);
		}
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		Point pivot = points[last];

		int i = first -1, j;

		for(j = first; j < last; ++j)
		{
			int compareInt;
			if(usingComparator)
			{
				compareInt = pointComparator.compare(pivot, points[j]);
			}
			else
			{
				compareInt = pivot.compareTo(points[j]);
			}

			if(compareInt > 0) //pivot is larger
			{
				++i;
				swap(i, j);
			}
		}
		swap(++i, last);
		return i; 
	}	
}

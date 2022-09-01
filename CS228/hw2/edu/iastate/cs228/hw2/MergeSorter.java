package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

import java.util.Arrays;

//import sun.security.ec.point.Point;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{  
		super(pts);
		algorithm = "Merge Sort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		 mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length <= 1)
		{
			return;
		}

		Point[] left = Arrays.copyOfRange(pts, 0, pts.length/2);
		Point[] right = Arrays.copyOfRange(pts, pts.length/2, pts.length); 
		
		mergeSortRec(left);
		mergeSortRec(right);

		merge(pts, left, right);
	}


	/**
	 * Merges the two subarrays, sub1 and sub2, into pts[] keeping them in  
	 * sorted in order.
	 *
	 * @param pts The array to store the sorted merged subarrays
	 * @param sub1 Left hand subarray of pts
	 * @param sub2 Right hand subarray of pts
	 */
	private void merge(Point[] pts, Point[] sub1, Point[] sub2)
	{
		// counts for both sub-arrays values being used 
		// and the total count
		int c1 = 0, c2 = 0, total = 0;
		
		while(total < sub1.length + sub2.length)
		{
			if(c1 >= sub1.length)
			{
				pts[total] = sub2[c2];
				++c2;
			}
			
			else if(c2 >= sub2.length)
			{
				pts[total] = sub1[c1];
				++c1;
			}
		
			else
			{
				int compareInt;
				if(usingComparator)
				{
					compareInt = pointComparator.compare(sub1[c1], sub2[c2]);
				}
				else
				{
					compareInt = sub1[c1].compareTo(sub2[c2]);
				}

				if(compareInt < 0 || compareInt == 0) //sub1 is smaller or equal to sub2
				{
					pts[total] = sub1[c1];
					++c1;
				}
				else
				{
					pts[total] = sub2[c2];
					++c2;
				}
			}
			++total;
		}
	}
}

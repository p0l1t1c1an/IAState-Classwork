package edu.iastate.cs228.hw2;

/**
 * 
 * @author Jacob Boicken 
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import java.io.File;
import java.util.Scanner;

import java.io.PrintWriter;

// No idea why this exists
//import sun.security.ec.point.Point;

/**
 * 
 * This class sorts all the points in an array by polar angle with respect to a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class RotationalPointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
		                                  // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long scanTime; 	       // execution time in nanoseconds. 

	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[]. Set outputFileName. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public RotationalPointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length <= 0)
		{
			throw new IllegalArgumentException();
		}

		points = new Point[pts.length];
		for(int i = 0; i < pts.length; ++i)
		{
			points[i] = new Point(pts[i]);
		}

		sortingAlgorithm = algo;
		
		switch(algo)
		{
			case SelectionSort:
				outputFileName = "select.txt";
				break;

			case InsertionSort:
				outputFileName = "insert.txt";
				break;

			case MergeSort:
				outputFileName = "merge.txt";
				break;

			case QuickSort:
				outputFileName = "quick.txt";
				break;

			default:
				throw new IllegalArgumentException();
		}
	}

	
	/**
	 * This constructor reads points from a file. Set outputFileName. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	public RotationalPointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File file = new File(inputFileName);
		Scanner sc = new Scanner(file);
	
		int count = 0;
		while(sc.hasNextInt())
		{
			++count;
			sc.nextInt();
		}

		if(count % 2 != 0)
		{
			throw new InputMismatchException();
		}

		points = new Point[count/2];
		sc = new Scanner(file);

		count = 0;
		while(sc.hasNextInt())
		{ 
			// number is even so we can iterate by 2 integers
			points[count] = new Point(sc.nextInt(), sc.nextInt()); 
			count++;
		}

		System.out.println(count);

		sortingAlgorithm = algo;
		switch(algo)
		{
			case SelectionSort:
				outputFileName = "select.txt";
				break;

			case InsertionSort:
				outputFileName = "insert.txt";
				break;

			case MergeSort:
				outputFileName = "merge.txt";
				break;

			case QuickSort:
				outputFileName = "quick.txt";
				break;

			default:
				throw new IllegalArgumentException();
		}
	}

	
	/**
	 * Carry out three rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates. 
	 *     d) Sort points[] again by the polar angle with respect to medianCoordinatePoint.
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting. Copy the sorting result back onto the array points[] by calling 
	 * the method getPoints() in AbstractSorter. 
	 *      
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter = null;

		switch(sortingAlgorithm)
		{
			case SelectionSort:
				aSorter = new SelectionSorter(points);
				break;

			case InsertionSort:
				aSorter = new InsertionSorter(points);
				break;

			case MergeSort:
				aSorter = new MergeSorter(points);
				break;

			case QuickSort:
				aSorter = new QuickSorter(points);
				break;
		}

		int medX, medY;

		scanTime = System.nanoTime();

		aSorter.setComparator(0);
		aSorter.sort();

		medX = aSorter.getMedian().getX();

		aSorter.setComparator(1);
		aSorter.sort();	

		medY = aSorter.getMedian().getY();

		medianCoordinatePoint = new Point(medX, medY);

		aSorter.setReferencePoint(medianCoordinatePoint);
		aSorter.setComparator(2);
		aSorter.sort();

		scanTime = System.nanoTime() - scanTime;
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String sort = "";

		switch(sortingAlgorithm)
		{
			case SelectionSort: 
				sort = "Selection Sort"; 
				break; 
			
			case InsertionSort: 
				sort = "Insertion Sort"; 
				break; 
			
			case MergeSort: 
				sort = "Mergesort"; 
				break; 
		
			case QuickSort: 
				sort = "Quicksort"; 
				break; 
		}

		return sort + "\t" + points.length + "\t" + scanTime; 
	}
	
	
	/**
	 * Write points[] after a call to scan(). When printed, the points will appear 
	 * in order of polar angle with respect to medianCoordinatePoint with every point occupying a separate 
	 * line.  The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		String pString = "";
		for(Point p : points)
		{
			pString += p.getX() + " " + p.getY() + "\n";
		}

		return pString; 
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		File file = new File(outputFileName);

        // This creates a file if it isn't found, exception shouldn't be needed
        PrintWriter pw = new PrintWriter(file);

        pw.print(this.toString());

        pw.close();	 
	}	

	
	/**
	 * This method is called after each scan for visually check whether the result is correct.  You  
	 * just need to generate a list of points and a list of segments, depending on the value of 
	 * sortByAngle, as detailed in Section 4.1. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{		
		// number of segments to draw.
		int numSegs = points.length; // points.length is how many segments between the median there are

		int i; 
		for(i = 0; i < points.length; ++i)
		{
			int next = (i+1) % points.length;
			if(!points[i].equals(points[next])) // add one for each point that doesn't repeat
				numSegs++;
		}

		// Generate the line segments to draw for display of the sorting result.
		Segment[] segments = new Segment[numSegs];  
		
		i = 0;
		for(int j = 0; j < segments.length; ++j)
		{
			int next = (i+1) % points.length;
			if(!points[i].equals(points[next]))
				segments[j++] = new Segment(points[i], points[next]);
	
			segments[j] = new Segment(points[i], medianCoordinatePoint);
			i++;
		}
	
		String sort = ""; 
		
		switch(sortingAlgorithm)
		{
		case SelectionSort: 
			sort = "Selection Sort"; 
			break; 
		case InsertionSort: 
			sort = "Insertion Sort"; 
			break; 
		case MergeSort: 
			sort = "Mergesort"; 
			break; 
		case QuickSort: 
			sort = "Quicksort"; 
			break; 
		default: 
			break; 		
		}

		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, sort);		
	}	
}

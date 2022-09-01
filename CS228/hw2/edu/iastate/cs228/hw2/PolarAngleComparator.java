package edu.iastate.cs228.hw2;

/**
 *  
 * @author Jacob Boicken
 *
 */

import java.util.Comparator;

//import sun.security.ec.point.Point;

/**
 * 
 * This class compares two points p1 and p2 by polar angle with respect to a reference point.  
 *
 */
public class PolarAngleComparator implements Comparator<Point>
{
	private Point rP; // reference Point for polar angle comparison 
	
	/**
	 * 
	 * @param p reference point
	 */
	public PolarAngleComparator(Point p)
	{
		rP = p; 
	}


	/**
	 * Use cross product and dot product to implement this method.  Do not take square roots 
	 * or use trigonometric functions. See the PowerPoint notes on how to carry out cross and 
	 * dot products. Calls private methods crossProduct() and dotProduct().
	 * 
	 * Call comparePolarAngle() and compareDistance().												
	 *
	 * @param p1
	 * @param p2
	 * @return  0 if p1 and p2 are the same point
	 *         -1 otherwise, if one of the following two conditions holds:
	 *
	 *                a) the polar angle of p1 w.r.t. rP is less than that of p2.
	 *                b) the two points have the same polar angle but p1 is closer to rP
	 *                   than p2.
	 *
	 *          1  otherwise.
	 *
	 */

	public int compare(Point p1, Point p2)
	{
		int pa = comparePolarAngle(p1, p2);

		if(pa == 0)
		{
			return compareDistance(p1, p2);
		}
		
		return pa;
	}
	
	/**
	 * Compare the polar angles of two points p1 and p2 with respect to rP.  Use 
	 * cross products.  Do not use trigonometric functions. 
	 * 
	 * All polar angles are within the range [0, 2 * pi). 
	 * 
	 * Ought to be private but made public for testing purpose. 
	 *
	 *	 @param p1
	 *   @param p2
	 *   @return    0  if one of the following two situations happens:
	 *   
	 *                    a) p1 and p2 are the same point (this case is checked already if the
	 *                       method is called within compare()).
	 *                    b) none is equal to rP, but the vectors p1 - rP and
	 *                       p2 - rP have a zero cross product and a positive dot product.
	 *   
	 *             -1  otherwise, if p1 equals rP;
	 *                 otherwise, if p2 is not equal to rP and one of the following situations
	 *                 below happens:
	 *   
	 *                      1) p1.y < rP.y and p2.y < rP.y, and the cross product of
	 *                         p1 - rP and p2 - rP is positive.
	 *   
	 *                      2) p1.y == rP.y and one of the following three situations happens:
	 *   
	 *                         a) p2.y < rP.y
	 *                         b) p2.y == rP.y and p1.x > rP.x and p2.x < rP.x
	 *						   c) p2.y > rP.y and p1.x > rP.x
	 *                   
	 *						3) p1.y > rP.y and one of the following three situations happens:
	 *
	 *						   a) p2.y > rP.y and the cross product of p1 - rP
	 *                             and p2 - rP is positive.
	 *                         b) p2.y == rP.y and p2.x < rP.x.
	 *                         c) p2.y < rP.y
	 *
	 *              1  otherwise.
	 */
	 public int comparePolarAngle(Point p1, Point p2) 
	 {	
		// Java doc seems exceedingly complicated. 
		// So I decided to check the location of p1 and p2 
		// to be on the ref point's x axis or p1 and p2 
		// being on different sides of the x axis of the ref point

		if(p1.getY() == rP.getY()) 
		{
			if(p2.getY() == rP.getY()) // are both on x axis?
			{
				if(p1.getX() > rP.getX() && p2.getX() > rP.getX()) // both to the right?
				{
					return 0;
				}
				else if(p1.getX() > rP.getX()) 
				{
					return -1;
				}
				else 
				{
					return 1;
				}
			}
			else if(p1.getX() > rP.getX()) 
			{
				return -1;
			}
		}

		if(p2.getY() == rP.getY() && p2.getX() > rP.getX()) 
		{
			return 1;
		}

		// if p1 is above the reference and p2 is below
		if(p1.getY() > rP.getY() && p2.getY() < rP.getY()) 
		{
			return -1;
		}
		
		if(p1.getY() < rP.getY() && p2.getY() > rP.getY()) 
		{
			return 1; 
		}

		// Points must be in the same half so we can 
		// return based on the direction of the cross product 

    	int c = crossProduct(p1, p2);

		if(c > 0)  // positive means p1 is before p2
		{
			return -1;
		}

		if(c < 0)
		{
			return 1;
		}

		return 0; 
    }
    
    
    /**
     * Compare the distances of two points p1 and p2 to rP.  Use dot products. 
     * Do not take square roots. 
     * 
     * Ought to be private but made public for testing purpose.
     * 
     * @param p1
     * @param p2
     * @return    0   if p1 and p2 are equidistant to rP
     * 			 -1   if p1 is closer to rP than p2 
     *            1   otherwise
     */
    public int compareDistance(Point p1, Point p2)
    {
		int d1 = dotProduct(p1, p1); // dot product with the same point is distance squared
		int d2 = dotProduct(p2, p2);
		
		if(d1 == d2)
		{
			return 0;
		}
		else if(d1 < d2)
		{
			return -1;
		}

		return 1;
    }
    

    /**
     * 
     * @param p1
     * @param p2
     * @return cross product of two vectors p1 - rP and p2 - rP
     */
    private int crossProduct(Point p1, Point p2)
    {
    	int x1, x2, y1, y2;
		x1 = p1.getX() - rP.getX();
		x2 = p2.getX() - rP.getX();
		y1 = p1.getY() - rP.getY();
		y2 = p2.getY() - rP.getY();
 
    	return x1*y2 - x2*y1; 
    }

    /**
     * 
     * @param p1
     * @param p2
     * @return dot product of two vectors p1 - rP and p2 - rP
     */
    private int dotProduct(Point p1, Point p2)
    {
    	int x1, x2, y1, y2;
		x1 = p1.getX() - rP.getX();
		x2 = p2.getX() - rP.getX();
		y1 = p1.getY() - rP.getY();
		y2 = p2.getY() - rP.getY();
    	
		return x1*x2 + y1*y2; 
    }
}

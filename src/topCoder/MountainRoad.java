package topCoder;
import java.lang.Math;


/*
 * This is the solution to the first problem of TopCoder's
 * SRM 449 DIV 2.
 */
public class MountainRoad {
	
	// Starting points of hypotenuses
	int[] start = null;

	// Ending points of hypotenuses
	int[] end = null;
	
	/* Checks whether the triangle at position 'element' is
	 * contained in the triangle at position 'container'
	 */
	boolean contains ( int set, int i )
	{
		// Just a normal edge check for set belonging
		return ( start[set] <= start[i] ) &&
				( end[set] >= end[i] );
	}
	
	/* Returns true if triangles at positions 'a' and 'b' 
	 * intersect.  False, otherwise.
	 */
	boolean intersect ( int a, int b )
	{
		return (
				(start[a] < start[b] && end[a] < end[b]) ||
				(start[b] < start[a] && end[b] < end[a])
				);
	}
	
	/*
	 * Calculates the base of triangle at position 'a'
	 */
	int tBase ( int a )
	{
		if ( a == -1 ) return 0;
		return end[a] - start[a];
	}
	
	/*
	 * Calculates the mountain distance that needs to be walked
	 * across over the union of right isosceles triangles
	 * whose hypotenuses are defined by starting and ending
	 * points specified in 'start' and 'end' respectively.
	 */
	public double findDistance(int[] start, int[] end)
	{
		// Set local members using received arguments
		this.start = start;
		this.end = end;
		
		int n = start.length;
		
		boolean[] ignore = new boolean[n];
		
		for ( int i=0; i<n; i++ )
			for ( int j=0; j<n; j++ )
			{
				if ( i==j || ignore[j] ) continue;
				if ( ignore[j] = contains(i, j) ) break;
			}
		
		double total = 0d;
		for ( int i=0; i<n; i++ )
		{
			if ( ignore[i] ) continue;
			
			int maxleft = -1;
			int leftEnd = Integer.MIN_VALUE;
			int leftBase = 0;
			
			int maxright = -1;
			int rightStart = Integer.MAX_VALUE;
			int rightBase = 0;
			
			for ( int l=0; l<n; l++ )
			{
				if ( i==l || ignore[l] || !intersect( i, l ) || (end[l] > end[i]) ) continue;

				int lBase = tBase(l);
				if ( end[l] == leftEnd && lBase > leftBase )
				{
					leftBase = lBase;
					maxleft = l;
				}
				else if ( end[l] > leftEnd )
				{
					leftBase = lBase;
					leftEnd = end[l];
					maxleft = l;
				}
			}
			
			for ( int r=0; r<n; r++)
			{
				if ( i==r || ignore[r] || !intersect( i, r ) || (start[r] < start[i]) ) continue;
				
				int rBase = tBase(r);
				if ( start[r] == rightStart && rBase > rightBase )
				{
					rightBase = rBase;
					maxright = r;
				}
				else if ( start[r] < rightStart )
				{
					rightBase = rBase;
					rightStart = start[r];
					maxright = r;
				}
			}
		
			total += 2 * tBase(i);
		
			if ( maxleft != -1 )
				total -= (double)( end[maxleft] - start[i] );
			
			if ( maxright != -1 )
				total -= (double)( end[i] - start[maxright] );
		}
		return total / Math.sqrt(2d);
	}
	
	/*
	 * This method computes the MountainRoad distance like a boss, i.e.
	 * instead of stupidly searching all triangles under the road, it
	 * computes the difference between the minimum start point and the
	 * maximum end point and multiplies it by sqrt(2), which give the 
	 * exact same distance that was calculated by the method findDistance
	 * with so much complexity.
	 */
	public double findDistanceLikeABoss ( int[] start, int[] end )
	{
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for ( int i=0; i<start.length; i++ )
		{
			min = Math.min(min, start[i]);
			max = Math.max(max, end[i]);
		}
		return (double)(max - min) * Math.sqrt(2d);
	}
	
}
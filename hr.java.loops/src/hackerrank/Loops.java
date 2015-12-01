package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loops {
	
	private static long power( int a, int b ) {
		return (long)Math.pow( a, b );
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner( System.in );
		
		int numTestCases = s.nextInt(); 
		
		int [][] testCases = new int[ numTestCases ][];
		for ( int i=0; i<numTestCases; i++ ) {
			testCases[i] = new int[3];
			testCases[i][0] = s.nextInt(); // Gets added to each element of the series
			testCases[i][1] = s.nextInt();
			testCases[i][2] = s.nextInt(); // n-1 elements			
		}
		s.close();
			
		for ( int [] tc: testCases ) {
			
			int a = tc[0];
			int b = tc[1];
			int n = tc[2];
			
			List<Long> elements = new ArrayList<>();
			for ( int j=0; j<n; j++ ) {
				long term = 0l;
				for ( int k=0; k<=j; k++ ) {
					long sum = Long.valueOf( power( 2, k ) * b );
					//System.out.println( String.format( "%d + ( 2^%d ) * %d = %d ", a, k, b, sum )  );
					term = term + sum;
				}
				//System.out.println( "TERM SUM: " + term );
				elements.add( a + term );
			}
			
			for ( Long element: elements ) {
				System.out.print( element );
				System.out.print( " " );
			}
			
			System.out.println();
		}
	}
}

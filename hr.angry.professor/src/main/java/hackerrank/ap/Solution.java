package hackerrank.ap;

import java.util.Scanner;

public class Solution {
  
   private static int getNumTestCases( Scanner s ) {
      int ntc = s.nextInt();
      return ntc;
   }

   private static int [] getTestCase( Scanner s ) {
      int classSize = s.nextInt();
      int cancelT = s.nextInt();

      int [] tc = new int[2];
      tc[0] = classSize;
      tc[1] = cancelT;

      return tc;
   }

   private static int [][] getTestCases( Scanner s ) {

      int ntc = getNumTestCases( s );
      int [][] testCases = new int[ ntc ][];
      for ( int i=0; i<ntc; i++ ) {
         testCases[i] = getTestCase( s );
      }

      return testCases;
   }

   public static void main( String [] args ) {

      Scanner s = new Scanner( System.in );
      int numTestCases = getNumTestCases( s );
      for ( int i=0; i<numTestCases; i++ ) {
         int [] testCase = getTestCase( s );
         int totalKids = testCase[0];
         int threshold = testCase[1];
         int onTimeKids = 0;

         //System.out.println( String.format( "Total Kids: %d, Threashold: %d", totalKids, threshold ) );
         for ( int j=0; j<totalKids; j++ ) {
            int kidState = s.nextInt();
            if ( kidState <= 0 )
               onTimeKids++;
         }

         if ( onTimeKids >= threshold ) {
            System.out.println( "NO" );
         } else {
            System.out.println( "YES" );
         }
      }
      s.close();
   }
}

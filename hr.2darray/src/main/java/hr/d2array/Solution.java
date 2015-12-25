package hr.d2array;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Solution {

   private static final int LEN = 6;
   private static final int HG_LEN = LEN - 2;

   private static void printArray( int [][] array ) {
      for ( int i=0; i<LEN; i++ ) {
         for ( int j=0; j<LEN; j++ ) {
            System.out.print( array[i][j] + " " );
         }
         System.out.println();
      }
   }

   private static int [][] getArray() {
      int [][] array = new int[LEN][LEN];
      Scanner s = new Scanner( System.in );
      for ( int i=0; i<LEN; i++ ) {
         for ( int j=0; j<LEN; j++ ) {
            array[i][j] = s.nextInt();
         }
      }

      s.close();
      return array;
   }

   public static void main( String [] args ) {

      int [][] array = getArray();
      int largest = Integer.MIN_VALUE;
      for ( int i=0; i<HG_LEN; i++ ) {
         for ( int j=0; j<HG_LEN; j++ ) {
   
            int current = 0;

            int tv1 = array[i][j];
            int tv2 = array[i][j+1];
            int tv3 = array[i][j+2];

            int cv = array[i+1][j+1];

            int bv1 = array[i+2][j];
            int bv2 = array[i+2][j+1];
            int bv3 = array[i+2][j+2];

            current = tv1 + tv2 + tv3 + cv + bv1 + bv2 + bv3;

            largest = current > largest ? current : largest;

         }
      }

      System.out.println( String.format( "%d", largest ) );
   }
}

package hr.cube.summation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;

class R3Point {
   private int x;
   private int y;
   private int z;

   public R3Point( int x, int y, int z ) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getZ() {
      return this.z;
   }

   @Override
   public boolean equals( Object other ) {
      if ( other == null || ! ( other instanceof R3Point ) )
         return false;
      else {
         R3Point otherPoint = ( R3Point )other;
         if ( otherPoint.x == this.x &&
              otherPoint.y == this.y &&
              otherPoint.z == this.z ) 
            return true;
         else 
            return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash( this.x, this.y, this.z );
   }

   @Override
   public String toString() {
      return String.format( "(x: %d, y: %d, z: %d )", this.x, this.y, this.z );
   }
}

class CubeData {

   private int matrixSize;
   private int numCommands;
   private Map<R3Point, Long> updates;

   private void processUpdate( List<String> updateList ) {
      int x = Integer.valueOf( updateList.get( 0 ) );
      int y = Integer.valueOf( updateList.get( 1 ) );
      int z = Integer.valueOf( updateList.get( 2 ) );
      Long value = Long.valueOf( updateList.get( 3 ) );
      R3Point point = new R3Point( x, y, z );
      updates.put( point, value );
   }

   private void processQuery( List<String> queryList ) {

      /* TODO - It all happens right here!!
       * - What happens if x0 == x1?
       *   Divide by zero! 
       * - What does it mean if values are equal in the point? 
       *   E.G. 2 3 4 : 4 3 2
       * - I'm not sure about the math. It needs to be verified.
       * - Output must be correct, right now I didn't even read what it's supposed to be.
       */

      long sum = 0l;

      int x0 = Integer.valueOf( queryList.get( 0 ) );
      int y0 = Integer.valueOf( queryList.get( 1 ) );
      int z0 = Integer.valueOf( queryList.get( 2 ) );

      int x1 = Integer.valueOf( queryList.get( 3 ) );
      int y1 = Integer.valueOf( queryList.get( 4 ) );
      int z1 = Integer.valueOf( queryList.get( 5 ) );

      int a = x1 - x0;
      int b = y1 - y0;
      int c = z1 - z0;

      //System.out.println( String.format( "\nQuery Points: (%d,%d,%d) - (%d,%d,%d)", x0, y0, z0, x1, y1, z1 ) );
      //System.out.println( String.format( "Vector: <%d,%d,%d>", a, b, c ) );
      for ( R3Point point : updates.keySet() ) {

         boolean onTheLine = true;
         int x = point.getX();
         int y = point.getY();
         int z = point.getZ();

         if ( ( x == x0 && y == y0 && z == z0 ) || ( x == x1 && y == y1 && z == z1 ) ) {
            onTheLine = true;
         } else if ( a == 0 && b == 0 && c == 0 ) {
            onTheLine = false;
         } else {

            List<Double> tList = new ArrayList<Double>();

            if ( a != 0 ) {
               tList.add( (double)( x - x0 )/ (double) a );
            }

            if ( b != 0 ) {
               tList.add( (double)( y - y0 )/ (double) b );
            }
            
            if ( c != 0 ) {
               tList.add( (double)( z - z0 )/ (double) c );
            }

            Double last = null;
            for ( Double t : tList ) {
               if ( last == null ) {
                  last = t;
               } else {
                  if ( ! t.equals( last ) || t < 0 || t > 1 ) {
                     //System.out.println( String.format( "\t\tt: %f != last: %f", t, last ) );
                     onTheLine = false;
                     break;
                  }
               }
            }
         }

         if ( onTheLine ) {
            //System.out.println( String.format( "\tOn The Line: %s", point.toString()) );
            sum += updates.get( point );
         } /*else {
            System.out.println( String.format( "\tNot On The Line: %s", point.toString() ) );
         }*/
      }

      System.out.println( sum );
   }

   public CubeData() {
      this.updates = new HashMap<R3Point, Long>();
   }

   public void process( String input ) {

      List<String> tokens = Arrays.asList( input.split( " " ) );
      String command = tokens.get( 0 );

      if ( command.equals( "UPDATE" ) ) 
         this.processUpdate( tokens.subList( 1, tokens.size()) );

      else if ( command.equals( "QUERY" ) )
         this.processQuery( tokens.subList( 1, tokens.size()) );

      else new RuntimeException( String.format( "Non-command input: %s", command ) );
   }
}

public class Solution {

   public static void main( String [] args ) {

      try ( BufferedReader reader = new BufferedReader(
               new InputStreamReader( System.in ) ) )  {

         String line = reader.readLine();
         int testCases = Integer.valueOf( line );
         for ( int i=0; i<testCases; i++ ) {
            
            String [] initData = reader.readLine().split( " " );
            int matrixSize = Integer.valueOf( initData[0] );
            int numCommands = Integer.valueOf( initData[1] );
            
            CubeData cd = new CubeData();
            for ( int j=0; j<numCommands; j++ )
               cd.process( reader.readLine() );
         }

         reader.close();

      } catch ( Exception e ) {
         e.printStackTrace();
      }   
   }
}  

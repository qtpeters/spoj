package hr.cube.summation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.List;
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
      return String.format( "< x: %d, y: %d, z: %d > ", this.x, this.y, this.z );
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
      System.out.println( "Processing QUERY" );
      System.out.println( queryList );
      System.out.println( updates );
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
            for ( int j=0; j<numCommands; j++ ) {
               cd.process( reader.readLine() );
            }
         }

         reader.close();

      } catch ( Exception e ) {
         e.printStackTrace();
      }   
   }
}  

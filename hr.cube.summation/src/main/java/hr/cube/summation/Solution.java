package hr.cube.summation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.List;
import java.util.Arrays;

class R3Point {
   private int x;
   private int y;
   private int z;
   private long value;

   public R3Point( int x, int y, int z ) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public void setValue( long value ) {
      this.value = value;
   }

   public long getValue() {
      return this.value;
   }
}

class CubeData {

   private int matrixSize;
   private int numCommands;
   private List<R3Point> updates;

   private void processUpdate( List<String> updateList ) {
      System.out.println( "Processing UPDATE" );
      System.out.println( updateList );
   }

   private void processQuery( List<String> queryList ) {
      System.out.println( "Processing QUERY" );
      System.out.println( queryList );
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

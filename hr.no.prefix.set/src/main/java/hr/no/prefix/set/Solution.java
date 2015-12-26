package hr.no.prefix.set;

import java.util.*;
import java.io.*;

public class Solution {

   public static List<String> strings = new ArrayList<String>();
   public static int numStrings;
   
   private static String process() throws Exception {

      // Create a scanner to read from STDIN
      BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

      // Read in the number of strings 
      int numStrings = Integer.valueOf( br.readLine() );

      // Iterate 'numStrings' times:
      // * Get a 
      String foundPrefixString = null;
      for ( int i=0; i<numStrings; i++ ) {
         
         String next = br.readLine();
         for ( String string : strings ) {
            if ( string.startsWith( next ) || next.startsWith( string ) ) { 
               foundPrefixString = next;
               break;
            }
         }
         
         if ( foundPrefixString != null ) break;
         else strings.add( next );
      }

      return foundPrefixString;
   }

   public static void main( String [] args ) throws Exception {
      String str = null;
      if ( ( str = process() ) != null ) {
         System.out.println( "BAD SET" );
         System.out.println( str );
      } else {
         System.out.println( "GOOD SET" );
      }
   }
}

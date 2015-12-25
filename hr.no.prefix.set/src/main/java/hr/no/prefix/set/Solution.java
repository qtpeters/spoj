package hr.no.prefix.set;

import java.util.*;

public class Solution {

   public static List<String> strings = new ArrayList<String>();
   public static int numStrings;
   
   private static String process() {
      Scanner s = new Scanner( System.in );
      int numStrings = s.nextInt();

      String foundPrefixString = null;
      for ( int i=0; i<numStrings; i++ ) {
         
         String next = s.next();
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

   public static void main( String [] args ) {
      String str = null;
      if ( ( str = process() ) != null ) {
         System.out.println( "BAD SET" );
         System.out.println( str );
      } else {
         System.out.println( "GOOD SET" );
      }
   }
}

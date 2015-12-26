import java.io.*;
import java.util.*;

public class Solution {

   public static SortedSet<String> strings = new TreeSet<String>();
   public static int numStrings;
   
   private static String process() {
      
       
      String input;
      String foundPrefixString = null;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
      while((input=br.readLine())!=null){
        for ( String string : strings ) {
            if ( string.startsWith( input ) || input.startsWith( string ) ) { 
               foundPrefixString = input;
               break;
            } else if (input.compareTo(string) > 0) {
                continue;
            }
         }
          
         if (foundPrefixString != null) break;    
         else strings.add(input);
      }
      } catch (IOException ioe) {
          ioe.printStackTrace();
          
      }
      return foundPrefixString;
   }

   public static void main( String [] args ) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      String str = null;
      if ( ( str = process() ) != null ) {
         System.out.println( "BAD SET" );
         System.out.println( str );
      } else {
         System.out.println( "GOOD SET" );
      }
   }

}

package hr.hrtweets;

import java.util.Scanner;

public class Solution {
   public static void main(String[] args) {
        
        int linesWithHr = 0;
        Scanner s = new Scanner( System.in );
        s.useDelimiter( "\\n" );
        int lines = s.nextInt();
        for ( int i=0; i<lines; i++ ) {
            String line = s.next();
            if ( line.matches( "(?i:.*hackerrank.*)" ) ) {
               linesWithHr++;
            }
        }
        
        System.out.println( linesWithHr );
        s.close();
    }
}

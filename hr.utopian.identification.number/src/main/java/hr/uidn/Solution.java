package hr.uidn;

import java.util.Scanner;

public class Solution {
   public static void main(String[] args) {
        
        int linesWithHr = 0;
        Scanner s = new Scanner( System.in );
        int numIdn = s.nextInt();
        for ( int i=0; i<numIdn; i++ ) {
            String idn = s.next();
            if ( idn.matches( "[a-z]{0,3}[0-9]{2,8}[A-Z]{3,}" ) ) {
               System.out.println( "VALID" );
            } else {
               System.out.println( "INVALID" );
            }
        }

        s.close();
    }
}

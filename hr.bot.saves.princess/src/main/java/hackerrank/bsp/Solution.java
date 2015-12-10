package hackerrank.bsp;

import java.util.Scanner;

class PrincessGrid {
   
   private char [][] grid;
   private int [] pLoc;
   private int [] bLoc;

   public PrincessGrid( int size ) {
      
      grid = new char[size][]; 
      for ( int i=0; i<size; i++ ) {
         grid[i] = new char[size];
      }

      pLoc = new int[2];
      bLoc = new int[2];
   }

   public void addItem( char item, int r, int c ) {
      
      if ( item == 'p' ) {
         pLoc[0] = r;
         pLoc[1] = c;
      } else if ( item == 'm' ) {
         bLoc[0] = r;
         bLoc[1] = c;
      }

      grid[r][c] = item;
   }

   public int [] getPrincessLoc() {
      return pLoc;
   }

   public int [] getBotLoc() {
      return bLoc;
   }
}

public class Solution {
  
   private static int getGridSize( Scanner s ) {
      int gridSize = s.nextInt();
      return gridSize;
   }

   private static PrincessGrid getGrid( int gs, Scanner s ) {
      PrincessGrid pg = new PrincessGrid( gs );
      for ( int i=0; i<gs; i++ ) {
         String line = s.next();
         int j = 0;
         for ( char c : line.toCharArray() ) {
            pg.addItem( c, i, j );
            j++;
         }
      }

      return pg;
   }

   private static void printMoves( String dir, int times ) {
      for ( int i=0; i<times; i++ ) {
         System.out.println( dir );
      }
   }

   public static void main( String [] args ) {
      Scanner s = new Scanner( System.in );
      int gridSize = getGridSize( s );
      PrincessGrid grid = getGrid( gridSize, s );
      int [] bl = grid.getBotLoc();
      int [] pl = grid.getPrincessLoc();
     
      int yDist = pl[0] - bl[0];
      int xDist = pl[1] - bl[1];

      if ( xDist > 0 )
         printMoves( "RIGHT", xDist );
      else
         printMoves( "LEFT", xDist * -1 );

      if ( yDist > 0 )
         printMoves( "DOWN", yDist );
      else
         printMoves( "UP", yDist * -1 );

      s.close();
   }
}

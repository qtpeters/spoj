package hackerrank.bsp;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class GridLocation {
   
   private int x;
   private int y;

   public GridLocation( int x, int y ) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }
}

class GridEntity {
   
   private Character c;
   private GridLocation loc;

   public GridEntity( Character c, GridLocation loc ) {
      this.c = c;
      this.loc = loc;
   }

   public Character getChar() {
      return this.c;
   }
   
   public GridLocation getLocation() {
      return this.loc;
   }
}

class Bot extends GridEntity {
   
   // CLosest dirty cell
   private GridEntity cdc;

   public Bot( Character c, GridLocation loc ) {
      super( c, loc );
   }

   public void setClosestDirtyCell( GridEntity cdc ) {
      this.cdc = cdc;
   }

   public GridEntity getClosestDirtyCell() {
      return this.cdc;
   }

   // Evaluates cell to see if it is the closest.
   public boolean isCLosestDirtyCell( GridEntity dc ) {

      boolean isCloser = false;
      if ( cdc != null ) {

         int botX = this.getLocation().getX();
         int botY = this.getLocation().getY();
         
         int inX = dc.getLocation().getX();
         int inY = dc.getLocation().getY();

         int cdcX = cdc.getLocation().getX();
         int cdcY = cdc.getLocation().getY();

         int sumInX = Math.abs( inX - botX );
         int sumInY = Math.abs( inY - botY );
         int totalInSum = sumInX + sumInY;

         int sumCdcX = Math.abs( cdcX - botX );
         int sumCdcY = Math.abs( cdcY - botY );
         int totalCdcSum = sumCdcX + sumCdcY;

         if ( totalInSum < totalCdcSum )
            isCloser = true;

      } else {
         System.out.println( String.format( "Setting initial dirty cell: X: %d, Y: %d", dc.getLocation().getX(), dc.getLocation().getY() ) );
         this.cdc = dc;
      }

      return isCloser;
   }
}

class Grid {
   
   private List<GridEntity> grid;
   private List<GridEntity> ds;
   private Bot b;

   public Grid() {
      grid = new ArrayList<GridEntity>();
      ds = new ArrayList<GridEntity>();
   }

   public void addItem( char item, int r, int c ) {
      
      GridLocation gl = new GridLocation( r, c );
      GridEntity ge = new GridEntity( item, gl );
      
      if ( item == 'd' ) 
         ds.add( ge );
      else if ( item == 'b' ) { 
         ge = new Bot( item, gl );
         b = ( Bot ) ge;
      }

      grid.add( ge );
   }

   public List<GridEntity> getDirtyCells() {
      return ds;
   }

   public Bot getBot() {
      return b;
   }
}

public class Solution {
 
   private static final int GRID_SIZE = 5;

   private static GridLocation getCurrentBotLoc( Scanner s ) {
      int x = s.nextInt();
      int y = s.nextInt();
      return new GridLocation( x, y );
   }

   private static Grid getGrid( Scanner s ) {
      Grid pg = new Grid();
      for ( int i=0; i<GRID_SIZE; i++ ) {
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

      GridLocation initialBotLoc = getCurrentBotLoc( s );
      Grid grid = getGrid( s );
      s.close();
      
      List<GridEntity> ds = grid.getDirtyCells();
      Bot bot = grid.getBot();
     
      System.out.println( String.format( 
         "Char: %c, X: %d, Y: %d",  
         bot.getChar(), 
         bot.getLocation().getX(), 
         bot.getLocation().getY() ) 
      );

      for ( GridEntity dc : ds ) {
         System.out.println( String.format( 
            "Dirty Cell at: X: %d, Y: %d",  
            dc.getLocation().getX(), 
            dc.getLocation().getY() ) 
         );

         if ( bot.isCLosestDirtyCell( dc ) ) {
            System.out.println( String.format( 
               "Found new Closest Dirty Cell: X: %d Y: %d", 
               dc.getLocation().getX(), dc.getLocation().getY() ) );
            bot.setClosestDirtyCell( dc );
         }

      }

      /*int yDist = pl[0] - bl[0];
      int xDist = pl[1] - bl[1];

      if ( xDist > 0 )
         printMoves( "RIGHT", xDist );
      else
         printMoves( "LEFT", xDist * -1 );

      if ( yDist > 0 )
         printMoves( "DOWN", yDist );
      else
         printMoves( "UP", yDist * -1 );*/

   }
}

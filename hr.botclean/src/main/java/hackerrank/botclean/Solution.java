package hackerrank.bsp;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class GridLocation {
   
   private int row;
   private int col;

   public GridLocation( int row, int col ) {
      this.row = row;
      this.col = col;
   }

   public int getRow() {
      return this.row;
   }

   public int getCol() {
      return this.col;
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

   private void printMoves( String text, int times ) {
      for ( int i=0; i<times; i++ ) {
         System.out.println( text );
      }
   }

   public Bot( Character c, GridLocation loc ) {
      super( c, loc );
   }

   public void setClosestDirtyCell( GridEntity cdc ) {
      this.cdc = cdc;
   }

   public GridEntity getClosestDirtyCell() {
      return this.cdc;
   }

   public void moveTo( GridLocation cellLoc ) {

      int colDist = cellLoc.getCol() - this.getLocation().getCol();
      int rowDist = cellLoc.getRow() - this.getLocation().getRow();

      System.out.println( String.format( 
         "Moving Row Dist: %d, Col Dist: %d", 
         rowDist, colDist ) );

      if ( colDist > 0 )
         printMoves( "RIGHT", colDist );
      else
         printMoves( "LEFT", colDist * -1 );

      if ( rowDist > 0 )
         printMoves( "DOWN", rowDist );
      else
         printMoves( "UP", rowDist * -1 );

   }

   public void clean() {
      System.out.println( "CLEAN" );
   }

   // Evaluates cell to see if it is the closest.
   public boolean isCLosestDirtyCell( GridEntity dc ) {

      boolean isCloser = false;
      if ( cdc != null ) {

         int botRow = this.getLocation().getRow();
         int botCol = this.getLocation().getCol();
         
         int inRow = dc.getLocation().getRow();
         int inCol = dc.getLocation().getCol();

         int cdcRow = cdc.getLocation().getRow();
         int cdcCol = cdc.getLocation().getCol();

         int sumInRow = Math.abs( inRow - botRow );
         int sumInCol = Math.abs( inCol - botCol );
         int totalInSum = sumInRow + sumInCol;

         int sumCdcRow = Math.abs( cdcRow - botRow );
         int sumCdcCol = Math.abs( cdcCol - botCol );
         int totalCdcSum = sumCdcRow + sumCdcCol;

         if ( totalInSum < totalCdcSum )
            isCloser = true;

      } else 
         this.cdc = dc;

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

   public void addItem( char item, int row, int col ) {
      
      GridLocation gl = new GridLocation( row, col );
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

   private static GridEntity findClosestDirtyCell( Bot bot, List<GridEntity> dirtyCells ) {

      GridEntity cdc = null;
      for ( GridEntity dc : dirtyCells ) {
         if ( bot.isCLosestDirtyCell( dc ) ) {
            cdc = dc;
            bot.setClosestDirtyCell( dc );
         }
      }
      return cdc;
   }

   public static void main( String [] args ) {
      
      Scanner s = new Scanner( System.in );

      GridLocation initialBotLoc = getCurrentBotLoc( s );
      Grid grid = getGrid( s );
      s.close();
      
      Bot bot = grid.getBot();
      System.out.println( String.format(
         "Bot is at Row: %d Col: %d",
         bot.getLocation().getRow(), bot.getLocation().getCol() ) );

      List<GridEntity> ds = grid.getDirtyCells();
      GridEntity dc = findClosestDirtyCell( bot, ds );
      System.out.println( String.format(
         "Found new Closest Dirty Cell: Row: %d Col: %d",
         dc.getLocation().getRow(), dc.getLocation().getCol() ) );
      bot.moveTo( dc.getLocation() );

   }
}

package hackerrank.bsp;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

final class GridLocation {
   
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

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append( "Row: " );
      sb.append( this.row );
      sb.append( " " );
      sb.append( "Col: " );
      sb.append( this.col );
      sb.append( "\n" );
      return sb.toString();
   }
}

class GridEntity {
   
   protected Character c;
   protected GridLocation loc;

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

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append( "Char: " );
      sb.append( c );
      sb.append( " " );
      sb.append( loc );
      return sb.toString();
   }

   @Override
   public boolean equals( Object in ) {
      if ( in == null || ! ( in instanceof GridEntity ) ) {
         return false;
      } else {
         GridEntity ge = ( GridEntity ) in;
         GridLocation gl = ge.getLocation();
         GridLocation thisGl = this.getLocation();
         if ( gl.getRow() == thisGl.getRow() && 
              gl.getCol() == thisGl.getCol() && 
              ge.c == this.c ) {
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public int hashCode() {

      int row = this.loc.getRow();
      int col = this.loc.getCol();

      int hash = 5;
      hash = 89 * hash + (this.c != null ? this.c.hashCode() : 0);
      hash = 89 * hash + (int) (this.loc.getRow() ^ (this.loc.getRow() >>> 32));
      hash = 89 * hash + (int) (this.loc.getCol() ^ (this.loc.getCol() >>> 32));
      return hash;
   }

}

final class Bot extends GridEntity {
   
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

      if ( colDist > 0 )
         printMoves( "RIGHT", colDist );
      else
         printMoves( "LEFT", colDist * -1 );

      if ( rowDist > 0 )
         printMoves( "DOWN", rowDist );
      else
         printMoves( "UP", rowDist * -1 );

      this.loc = cellLoc;
   }

   public void clean( GridEntity cdc, List<GridEntity> dirtyCells ) {
      cdc.c = '-';
      dirtyCells.remove( cdc );
      System.out.println( "CLEAN" );
      this.cdc = null;
   }

   // Evaluates cell to see if it is the closest.
   public boolean isClosestDirtyCell( GridEntity dc ) {

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

      } else {
         this.cdc = dc;
         isCloser = true;
      }

      return isCloser;
   }

   public void printLocation() {
      System.out.println( String.format(
         "Bot is at Row: %d Col: %d",
         this.loc.getRow(), this.loc.getCol() ) );
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

   public void printGrid() {
      
      for ( GridEntity g : this.grid ) {
         GridLocation gl = g.getLocation();
         if ( gl.getCol() == 0 )
            System.out.println();
         System.out.print( g.getChar() );
      }

      System.out.println();
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
         if ( bot.isClosestDirtyCell( dc ) ) {
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
      List<GridEntity> ds = grid.getDirtyCells();
      while ( ds.size() > 0 ) {
         GridEntity dc = findClosestDirtyCell( bot, ds );
         bot.moveTo( dc.getLocation() );
         bot.clean( dc, ds );
      }
   }
}

package hw

import java.util.Date
import scala.math.sqrt
import scala.math.pow

/**
 * @author qtpeter
 */
object Main extends App {
  
  def rev( num: Int ): Int = {
    num.toString().reverse.replaceFirst( "^0+", "" ).toInt
  }
  
  def getReverseSum( first: Int, second: Int ): Int = {
    rev( rev( first ) + rev( second ) )
  }
  
  def produceOutput( s: Int, ntc: Int ): Unit = {
    if ( s <= ntc ) {
      val nums: Array[String] = scala.io.StdIn.readLine().split( " " )
      println( getReverseSum( nums(0).toInt, nums(1).toInt ) )
      produceOutput( s+1, ntc )
    }
  }
  
  val ntc: Int = scala.io.StdIn.readInt()
  produceOutput( 0, ntc )
}
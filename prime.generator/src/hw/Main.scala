
package hw
import scala.util.control.Breaks._
object Main extends App {
  
  def isDivisible( num: Int ): Boolean = {
    for ( x <- 2 until num-1 ) {
      val m = num % x
      print( "( " + x + ":" + m + " )")
      if ( m == 0 ) return false
    }
    true
  }
  
  def isPrime( num: Int ): Boolean = {
    isDivisible( num )
  }
  
  def printIfPrime( num: Int ): Unit = {
    if ( num != 1 && isPrime( num ) ) {
      println( num )
    } else {
      println()
    }
  }
  
  def searchThrough( n: Int, l: Int ): Unit  = {
    if ( n <= l ) {
      printIfPrime( n )
      searchThrough( n+1, l )
    }
  }
  
  def exeTestCases( cnt: Int, total: Int, testCases: Array[String] ): Unit = {    
    if ( cnt <= total-1 ) {
      val nums = testCases(cnt).split( " " )
      searchThrough( nums(0).toInt, nums(1).toInt )
      println()
      exeTestCases( cnt+1, total, testCases )
    }
  }
  
  def getTestCases( cnt: Int, total: Int, testCases: Array[String] ) {
    testCases( cnt ) = io.StdIn.readLine()
    if ( cnt < total-1 ) getTestCases( cnt+1, total, testCases ) 
  }
  
  val t: Int = io.StdIn.readShort()
  val tc: Array[String] = new Array[String]( t );
  getTestCases( 0, t, tc )
  exeTestCases( 0, t, tc )
}
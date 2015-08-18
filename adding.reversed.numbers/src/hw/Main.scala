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
  
  def collectTestCases( ntc: Int ): Array[String] = {
    val testCases: Array[String] = new Array[String]( ntc )
    def getNextTestCase( tcr: Int ) {
      if ( tcr != 0 ) {
        testCases( ntc - tcr ) = scala.io.StdIn.readLine()
        getNextTestCase( tcr - 1 )
      }
    }
    getNextTestCase( ntc )
    return testCases
  }
  
  def produceOutput( testCases: Array[String] ): Unit = {
    testCases.foreach { x =>  
      val nums: Array[String] = x.split( " " )
      println( getReverseSum( nums(0).toInt, nums(1).toInt ) ) 
    }
  }
  
  val ntc: Int = scala.io.StdIn.readInt()
  val testCases: Array[String] = collectTestCases( ntc )
  produceOutput( testCases )
}
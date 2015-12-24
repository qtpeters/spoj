package hr.eval.ex;

import scala.io.StdIn;

object Solution extends App {

   /*
    * Rounds value to 4 places
    */
	def r( value:Double ) : Double = {
		BigDecimal( value ).setScale( 
			4, BigDecimal.RoundingMode.HALF_UP 
		).toDouble
	}

   /*
    * Return value to the pow power
    */
	def p( value:Double, pow:Int ) : Double = {
		if ( pow > 1 ) value * p( value, pow - 1 )
		else if ( pow == 0 ) 1
		else value
	}

   /*
    * Return the factorial of num
    */
	def f( num:Int ) : Double = {
		if ( num > 0 ) 
			return num * f( num - 1 ) 
		else 1
	}

   /*
    * Evalueate value itr times
    */
	def evalFor( value:Double, itr:Int=0 ) : Double = {
		if ( itr < 10 )
			p( value, itr )/f( itr ) + evalFor( value, itr + 1 )
		else 0.0
	} 
	
	/*
	 * Execute tn test cases
	 */
	def exeTestCase( tn:Int ) : Unit = {
		if ( tn > 0 ) {
			exeTestCase( tn - 1 );
			println( r( evalFor( scala.io.StdIn.readDouble() ) ) )
		}
	}
	
	exeTestCase( scala.io.StdIn.readInt() )
}


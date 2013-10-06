package pl.macjankowski.fps.ch10

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class Monoids {

  trait Monoid[A] {
    def op(a1: A, a2: A): A
    def sero: A
  }

  val intAddition = new Monoid[Int] {
    def op(a1: Int, a2: Int) = a1 + a2
    def sero: Int = 0
  }

  val intMultiplication = new Monoid[Int] {
    def op(a1: Int, a2: Int) = a1 * a2
    def sero: Int = 1
  }

  val booleanOr = new Monoid[Boolean] {
    def op(a1: Boolean, a2: Boolean) = a1 || a2
    def sero: Boolean = false
  }

  val booleanAnd = new Monoid[Boolean] {
    def op(a1: Boolean, a2: Boolean) = a1 && a2
    def sero: Boolean = true
  }

  def optionMonoid[A] = new Monoid[Option[A]] {
    def op(a1: Option[A], a2: Option[A]) = a1 orElse a2
    def sero: Option[A] = None
  }

  def EndoMonoid[A] = new Monoid[A => A] {
    def op(f: A => A, g: A => A): A => A = x => f(g(x))
    def sero: A => A = x => x
  }
  
//  def wordsMonoid(s: String) = new Monoid[String] = {
//     def op(a1: String, a2: String):String = a1 match{
//       
//     }
//    def sero: String = 
//  }

}
package pl.macjankowski.fps.ch4

import org.scalacheck._
import Prop.forAll

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 11:00
 */
class TestLift extends Properties("lift"){

  def lift[A,B](f: A => B): Option[A] => Option[B] = _ map f

  def m(s: String) = s.reverse
  def lifted = lift(m)


  def test(){
    println("abcdf")
    println(lift(m)(Some("abc")))
  }

}

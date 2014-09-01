package pl.macjankowski.fps.other

import org.scalacheck.Properties
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 28.08.14
 *         Time: 14:46
 */
class TestMethods extends Properties("isSublist") {

  import Methods._

  property("checks if isSublist") =  forAll { (l1: List[Int], l2: List[Int]) =>
    l2.containsSlice(l1) ==>  (isSubList(l1, l2))
  }


}

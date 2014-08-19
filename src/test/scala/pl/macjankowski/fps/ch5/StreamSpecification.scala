package pl.macjankowski.fps.ch5

import org.scalacheck.{Arbitrary, Gen, Properties}
import org.scalacheck.Prop.forAll
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import scala.collection.immutable.Range.Inclusive

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 18.08.14
 *         Time: 12:20
 */
@RunWith(classOf[JUnitRunner])
class StreamSpecification extends Properties("Stream") {


  val propConcatLists = forAll { (l1: List[Int], l2: List[Int]) =>
    l1.size + l2.size == (l1 ::: l2).size }

  property("map on Stream") = forAll(streams) {
    (s: Stream[Int]) =>
      println(s)
      s map (x => x) equals s
  }

  property("map toList") = forAll(streams) {
    (s: Stream[Int]) =>
      println(s)
      val l = s.toList
      s.uncons.map(el => el._1).fold(false)(x => x == l.head)
  }

  val streams: Gen[Stream[Int]] = {

    for{
      size <- Gen.choose(0, 100)
    } yield {
      val inclusive: Inclusive = 0 to size
      val op: (Int, Stream[Int]) => Stream[Int] = (a, b) => Stream.cons(a, b)
      inclusive.foldRight(Stream.empty[Int])(op)
    }

  }

}

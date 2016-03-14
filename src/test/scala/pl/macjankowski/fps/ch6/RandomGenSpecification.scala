package pl.macjankowski.fps.ch6

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import pl.macjankowski.fps.ch6.RandomGen._


/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 14:34
 */
class RandomGenSpecification extends Properties("RandomGen") {

  val positives: Gen[Int] =
    for {
      seed <- arbitrary[Int]
    } yield {
      val rng = RNG.simple(seed)
      RandomGen.positiveInt(rng)._1
    }

  val positives2: Gen[Int] =
    for {
      seed <- arbitrary[Int]
    } yield {
      val rng = RNG.simple(seed)
      RandomGen.positiveInt2(rng)._1
    }

  val doubles: Gen[Double] =
    for {
      seed <- arbitrary[Int]
    } yield {
      val rng = RNG.simple(seed)
      RandomGen.double(rng)._1
    }

  val doubles2: Gen[Rand[Double]] = {
    for {
      seed <- arbitrary[Int]
    } yield {
      RandomGen.double2
    }
  }

  val positivesMax: Gen[(Int, Rand[Int])] =
    for {
      seed <- arbitrary[Int]
    } yield {
      val rng = RNG.simple(seed)
      val (n, _) = RandomGen.positiveInt(rng)
      val r = RandomGen.positiveMax(n)
      (n, r)
    }

  property("positive integers") = forAll(positives) {
    (pInt: Int) => pInt >= 0
  }

  property("positive integers 2") = forAll(positives2) {
    (pInt: Int) => pInt >= 0
  }

//  property("doubles between 0 and one exclusive") = forAll(doubles) {
//    (d: Double) =>
//      (d >= 0) :| "generated double is below zero" &&
//        (d < 1) :| "generated double is above or equal one"
//  }
//
//  property("double2: doubles between 0 and one exclusive") = forAll(doubles2) {
//    (rand: Rand[Double]) =>
//      val (d, _) = rand(RNG.simple(100))
//      (d >= 0) :| "generated double is below zero" &&
//        (d < 1) :| "generated double is above or equal one"
//  }

//  property("positive max") = forAll(positivesMax) {
//    (x: (Int, Rand[Int])) => x match {
//      case (n, rand) =>
//        val (x, _) = rand(RNG.simple(100))
//        (x >= 0) :| "generated int is below zero" &&
//          (x <= n) :| "generated int is above max"
//    }
//  }
}

package pl.macjankowski.fps.ch2

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import org.scalatest.prop.TableDrivenPropertyChecks

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class TestFibTailrec extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val alg = new FibTailrec

  val params = Table(
    ("n", "result"),
    (0, 0),
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 5),
    (6, 8),
    (12, 144))

  describe("Calculates fibbonaci number") {
    forAll(params) { (n: Int, result: Int) =>
      it(s"${n}th element of fibbonaci sequence should be $result") {
        alg.fibRec(n) should equal (result)
      }
    }
  }
}
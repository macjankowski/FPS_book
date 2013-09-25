package pl.macjankowski.fps.ch2

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import pl.macjankowski.fps.ch3.List
import pl.macjankowski.fps.ch3.Nil

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class TestList extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val testTail = Table(
    ("collection", "isPalindrome"),
    (List(1), Nil),
    (List(1, 2, 3, 4, 5), List(2, 3, 4, 5)),
    (List(1, 3, 1), List(3, 1)),
    (List(1, 3, 5, 3, 1), List(3, 5, 3, 1)),
    (List(1, 3, 3, 3, 1), List(3, 3, 3, 1)),
    (List(5, 5, 5, 5, 5, 5, 5, 5), List(5, 5, 5, 5, 5, 5, 5)))

  val testDrop = Table(
    ("collection", "n", "isPalindrome"),
    (List(1), 1, Nil),
    (List(1), 0, List(1)),
    (List(1, 2, 3, 4, 5), 2, List(3, 4, 5)),
    (List(1, 3, 1), 3, Nil),
    (List(1, 3, 5, 3, 1), 3, List(3, 1)))

  describe("tail method") {
    forAll(testTail) { (collection, colTail) =>
      it(s"tail of collection $collection should equal $colTail") {
        collection.tail should equal(colTail)
        List.tail2(collection) should equal(colTail)
      }
    }
  }

  describe("drop method") {
    forAll(testDrop) { (collection, n, dropped) =>
      it(s"after dropping $n elements collection $collection should equal $dropped") {
        List.drop(n, collection) should equal(dropped)
      }
    }

    it("should throw exception when removing more elements than the length of a list") {
      intercept[NoSuchElementException] {
        List.drop(1, Nil)
      }

      intercept[NoSuchElementException] {
        List.drop(2, List(1))
      }

      intercept[NoSuchElementException] {
        List.drop(3, List(1, 2))
      }
    }
  }

}
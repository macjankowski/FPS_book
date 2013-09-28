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

  describe("tail method") {
    forAll(testTail) { (collection, colTail) =>
      it(s"tail of collection $collection should equal $colTail") {
        collection.tail should equal(colTail)
        List.tail2(collection) should equal(colTail)
      }
    }
  }

  val testDrop = Table(
    ("collection", "n", "isPalindrome"),
    (List(1), 1, Nil),
    (List(1), 0, List(1)),
    (List(1, 2, 3, 4, 5), 2, List(3, 4, 5)),
    (List(1, 3, 1), 3, Nil),
    (List(1, 3, 5, 3, 1), 3, List(3, 1)))

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

  describe("flatten") {

    val input = List(List(1, 2, 3), List(4), List(5, 6), List(7, 8, 9, 10), Nil, List(11))
    val res = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    it(s"should flatten $input to $res") {
      List.flatten(input) should equal(res)
    }
  }

  describe("filter") {

    val input = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val res = List(2, 4, 6, 8, 10)
    val odd = (x: Int) => x % 2 == 0

    it(s"should return only odd numbers, For $input it should be $res") {
      List.filter(input)(odd) should equal(res)
    }
  }

  describe("map") {

    val input = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val res = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22)
    val multiplyByTwo = (x: Int) => x * 2

    it(s"should modify a list") {
      List.map(input)(multiplyByTwo) should equal(res)
    }
  }

  describe("foldLeft") {

    val input = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val res = 66
    val sum = (x: Int, y: Int) => x + y

    it(s"when sum provided, shoul sum all elements") {
      List.foldLeft(input, 0)(sum) should equal(res)
    }
  }

  describe("execFOnElemFromTwoLists") {

    val input1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val input2 = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110)
    val res = List(11, 22, 33, 44, 55, 66, 77, 88, 99, 110, 121)
    val sum = (x: Int, y: Int) => x + y

    it(s"when sum provided, should sum elements from both lists") {
      List.execFOnElemFromTwoLists(input1, input2)(sum) should equal(res)
    }
  }

  val testSub = Table(
    ("list", "prefix", "isPrefix"),
    (List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), List(4, 5, 6), true),
    (List(1, 2, 3), Nil, true),
    (List(1, 2, 3, 4, 5), List(2, 4), false),
    (List(1, 2, 3, 4, 5), List(3, 4, 5), true),
    (List(1, 2, 3, 4, 5), List(2, 4, 5), false),
    (List(1, 2, 3, 4, 5), List(1, 2), true),
    (List(1, 2, 3, 4, 5), List(2, 3), true),
    (List(1, 3, 5, 3, 1), List(1), true),
    (List(1), List(1), true),
    (Nil, Nil, true),
    (Nil, List(1, 2), false))

  describe("hasSubsequence") {
    forAll(testSub) { (list, prefix, isPrefix) =>
      it(s"should check if $prefix is subsequence of $list and return $isPrefix") {
        List.hasSubsequence(list, prefix) should equal(isPrefix)
      }
    }
  }

}
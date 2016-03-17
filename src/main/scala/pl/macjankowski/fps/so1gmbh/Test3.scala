package pl.macjankowski.fps.so1gmbh

import scala.Predef._
import pl.macjankowski.fps.so1gmbh.Interval

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 06.03.16
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */

case class Interval(from: Int, to: Int) {
  override def toString() = s"($from,$to)"
}

object Test3 {

  def mergeTwo(l1: Interval, l2: Interval): List[Interval] = {
    if (l1.to >= l2.from) List(Interval(Math.min(l1.from, l2.from), Math.max(l1.to, l2.to)))
    else List(l1, l2)
  }

  def merge(intervals: Array[Interval]): List[Interval] = {

    intervals.foldLeft(List[Interval]()) {
      case (l1, l2) =>

        l1 match {
          case Nil => List(l2)
          case l => l.dropRight(1) ::: mergeTwo(l.last, l2)
        }
    }
  }

  def solution(A: Array[Int], B: Array[Int]): Int = {

    val tuples = A.zip(B)
    val intervals = tuples map {
      case (x, y) => Interval(x, y)
    }
    val sortedIntervals = intervals.sortWith {
      case (l1, l2) => l1.from < l2.from
    }
    //sortedIntervals foreach println
    val merged = merge(sortedIntervals)
    merged foreach println
    merged.length
  }

  def main(args: Array[String]) {

    val A = Array(1, 12, 42, 70, 36, -4, 43, 15)
    val B = Array(5, 15, 44, 72, 36, 2, 69, 24)
    solution(A, B)

  }
}

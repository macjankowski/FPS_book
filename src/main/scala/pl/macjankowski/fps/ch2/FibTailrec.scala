package pl.macjankowski.fps.ch2

import scala.annotation.tailrec

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class FibTailrec {

  def fibRec(n: Int): Int = {

    @tailrec
    def go(k: Int, b: Int, a: Int): Int = {
      if (k == 0) a
      else if (k == 1) b
      else {
        go(k - 1, a + b, b);
      }
    }

    go(n, 1, 0)
  }
}
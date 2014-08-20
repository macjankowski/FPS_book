package pl.macjankowski.fps.ch5

import pl.macjankowski.fps.ch5.Stream._

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 20.08.14
 *         Time: 15:29
 */
object Methods {

  def nat(n: Int): Stream[Int] = cons(n, nat(n + 1))

  def constant[A](x: A): Stream[A] = cons(x, constant(x))

  val ones: Stream[Int] = cons(1, ones)

  val onesByC: Stream[Int] = constant(1)

  def fibs: Stream[Int] = {
    def go(x: (Int, Int)): Stream[Int] = {
      val next: Int = x._1 + x._2
      cons(next, go((x._2, next)))
    }
    go(0, 1)
  }

  def fibsByUnfold: Stream[Int] =
    Stream.unfold((0, 1))(s => Some(s._1 + s._2, (s._2, s._1 + s._2)))

  def fromByUnfold(n: Int): Stream[Int] =
    Stream.unfold(n)(s => Some(s + 1, s + 1))

  def constantByUnfold(n: Int): Stream[Int] =
    Stream.unfold(n)(s => Some(s, s))

  def onesByUnfold: Stream[Int] = constantByUnfold(1)
}

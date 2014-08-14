package pl.macjankowski.fps.ch4.either

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 14:01
 */
trait MEither[+E, +A] {
  def map[B](f: A => B): MEither[E, B]
  def flatMap[EE >: E, B](f: A => MEither[EE, B]): MEither[EE, B]
  def orElse[EE >: E,B >: A](b: => MEither[EE, B]): MEither[EE, B]
  def map2[EE >: E, B, C](b: MEither[EE, B])(f: (A, B) => C):
  MEither[EE, C]
}

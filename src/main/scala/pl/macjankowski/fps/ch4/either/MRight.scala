package pl.macjankowski.fps.ch4.either

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 14:03
 */
case class MRight[+A](value: A) extends MEither[Nothing, A] {

  def map[B](f: A => B): MEither[Nothing, B] = MRight(f(value))

  def flatMap[EE >: Nothing, B](f: A => MEither[EE, B]): MEither[EE, B] = f(value)

  def orElse[EE >: Nothing,B >: A](b: => MEither[EE, B]): MEither[EE, B] = this

  def map2[EE >: Nothing, B, C](b: MEither[EE, B])(f: (A, B) => C):
    MEither[EE, C] = b map (x => f(value, x))


}

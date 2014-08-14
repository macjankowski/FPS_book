package pl.macjankowski.fps.ch4.either

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 14:02
 */
case class MLeft[+E](value: E) extends MEither[E, Nothing] {

  def map[B](f: Nothing => B): MEither[E, B] = MLeft(value)

  def flatMap[EE >: E, B](f: Nothing => MEither[EE, B]): MEither[EE, B]
    = MLeft(value)

  def orElse[EE >: E,B >: Nothing](b: => MEither[EE, B]): MEither[EE, B] = b

  def map2[EE >: E, B, C](b: MEither[EE, B])(f: (Nothing, B) => C):
    MEither[EE, C] = MLeft(value)
}

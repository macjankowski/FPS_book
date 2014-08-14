package pl.macjankowski.fps.ch4

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 13:16
 */
class Ex5 {

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil =>  None
    case x::xs => x flatMap(e => sequence(xs) map(l => e :: l))
  }
}

class Ex6 {

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil =>  None
    case x::xs => f(x).flatMap(e => traverse(xs)(f) map(l => e :: l))
  }

}
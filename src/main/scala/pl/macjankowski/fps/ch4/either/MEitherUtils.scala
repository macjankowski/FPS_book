package pl.macjankowski.fps.ch4.either

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 15:20
 */
class MEitherUtils {

  def traverse[A, B](a: List[A])(f: A => MEither[String, B]):  MEither[String, List[B]] = a match {
    case Nil =>  MLeft("Empty list")
    case x::xs => f(x).flatMap(e => traverse(xs)(f) map(l => e :: l))
  }

  def sequence[A](a: List[MEither[String, A]]): MEither[String, List[A]] =
    traverse(a)(x => x)

}

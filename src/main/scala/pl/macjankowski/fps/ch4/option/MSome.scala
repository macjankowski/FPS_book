package pl.macjankowski.fps.ch4.option

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 10:29
 */
case class MSome[+A](elem: A) extends MOption[A] {

  def map[B](f: A => B): MOption[B] = MSome(f(elem))

  def flatMap[B](f: A => MOption[B]): MOption[B] = f(elem)

  def getOrElse[B >: A](default: => B): B = elem

  def orElse[B >: A](ob: => MOption[B]): MOption[B] = MSome(elem)

  def filter(f: A => Boolean): MOption[A] =
    if (f(elem)) MSome(elem)
    else MNone
}

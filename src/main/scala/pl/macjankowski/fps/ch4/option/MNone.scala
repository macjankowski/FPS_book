package pl.macjankowski.fps.ch4.option

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 10:34
 */
case object MNone extends MOption[Nothing] {

  def map[B](f: Nothing => B): MOption[B] = MNone

  def flatMap[B](f: Nothing => MOption[B]): MOption[B] = MNone

  def getOrElse[B >: Nothing](default: => B): B = default

  def orElse[B >: Nothing](ob: => MOption[B]): MOption[B] = MNone

  def filter(f: Nothing => Boolean): MOption[Nothing] = MNone
}

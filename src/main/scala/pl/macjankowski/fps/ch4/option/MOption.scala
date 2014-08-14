package pl.macjankowski.fps.ch4.option

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 10:28
 */
trait MOption[+A] {
  def map[B](f: A => B): MOption[B]
  def flatMap[B](f: A => MOption[B]): MOption[B]
  def getOrElse[B >: A](default: => B): B
  def orElse[B >: A](ob: => MOption[B]): MOption[B]
  def filter(f: A => Boolean): MOption[A]
}

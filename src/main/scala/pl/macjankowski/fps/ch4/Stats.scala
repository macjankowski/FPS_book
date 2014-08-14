package pl.macjankowski.fps.ch4

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 10:46
 */
class Stats {

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = {
     mean(xs).map(m => xs.map(x => Math.pow(x - m, 2))) flatMap mean
  }

}

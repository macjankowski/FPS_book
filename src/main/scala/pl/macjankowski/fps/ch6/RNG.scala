package pl.macjankowski.fps.ch6

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:20
 */
trait RNG {
  def nextInt: (Int, RNG)
}

object RNG {
  def simple(seed: Long): RNG = new RNG {
    def nextInt = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) &
        ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int],
        simple(seed2))
    }
  }
}

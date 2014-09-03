package pl.macjankowski.fps.other

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 28.08.14
 *         Time: 14:45
 */
object Methods {
//  def isSubList[A](short: List[A], long: List[A]): Boolean =
//    long.tails exists (_.startsWith(short))

  def isSubList[A](short: List[A], long: List[A]): Boolean = {
    val sLong = long.toStream
    val sShort = short.toStream
    sLong.tails exists (_.startsWith(sShort))
  }

  def test(){
    class SillyIterator extends Iterator[Int]
    {
      private var i = 0

      def hasNext : Boolean = i < 10

      def next() : Int =
      {
        val ret = i
        i += 1
        ret
      }
    }

    val it = new SillyIterator
    it.filter( _ > 3 ) foreach println
  }
}

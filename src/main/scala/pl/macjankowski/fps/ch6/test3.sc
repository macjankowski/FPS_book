import pl.macjankowski.fps.ch6.RandomGen2._
import pl.macjankowski.fps.ch6.RNG

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 03.09.14
 *         Time: 14:46
 */


val l = for {
  x: Int <- int
  y: Int <- int
  xs <- ints(x)
} yield xs.map(_ % y)


l foreach println




println("1")


println("1")
println("1")
println("1")
println("1")

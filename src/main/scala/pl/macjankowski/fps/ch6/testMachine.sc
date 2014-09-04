import pl.macjankowski.fps.ch6.{Turn, Coin, Machine}
import scala.Predef._

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 04.09.14
 *         Time: 16:24
 */


val m = Machine(true, 10,10)

val inputs = List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)

val state = Machine.simulateMachine(inputs)


val (coins, s) = state.run(m)


println(s.coins)


println("1")
println("1")
println("1")
println("1")
println("1")
println("1")
println("1")


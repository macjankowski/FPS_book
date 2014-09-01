/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 28.08.14
 *         Time: 13:55
 */

import pl.macjankowski.fps.ch5.Stream
import pl.macjankowski.fps.ch5.Stream._


val s = cons(1, cons(2, cons(3, empty)))

println(s.tails take(10))

println("1")
println("2")
println("3")



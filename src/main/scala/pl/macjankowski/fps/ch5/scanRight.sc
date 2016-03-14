import pl.macjankowski.fps.ch5.Stream._

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 10:51
 */


val s = cons(1, cons(2, cons(3,empty)))
val ss = s.scanRight(0)(_ + _)

println(ss.toList)

ss foreach println
println(s"length = ${ss.toList.length}")

nat

println("finished printing")
println("finished printing \"finished printing\"")
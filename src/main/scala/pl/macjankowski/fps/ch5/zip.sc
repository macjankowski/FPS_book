import pl.macjankowski.fps.ch5.Stream._

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 10:32
 */

val s = cons(1, cons(2, cons(3, cons(4,empty))))

val t = cons("a", cons("b", cons("c", empty)))

val z  = s zipByUnfold(t)


z foreach println


println(s"length = ${z.toList.length}")

println("finished printing")
println("finished printing \"finished printing\"")
import pl.macjankowski.fps.ch5.Stream
import pl.macjankowski.fps.ch5.Stream._


/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 22.08.14
 *         Time: 13:24
 */


val s = cons(1, cons(2, cons(3, empty)))
val t = cons("a", cons("b", cons("c", empty)))

val newS = s mapByUnfold(x => x * 3)


newS foreach println


def nat(n: Int): Stream[Int] = cons(n, nat(n+1))


def nextThree(n: Int): Stream[Int] = nat(n) takeWhile(n+3 >)
nat(10) takeByUnfold(10) foreach println

nat(10) takeWhileByUnfold(33>)  foreach println

































println("finished printing")
println("finished printing \"finished printing\"")

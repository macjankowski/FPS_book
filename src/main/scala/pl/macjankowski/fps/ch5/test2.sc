/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 19.08.14
 *         Time: 12:56
 */


import  pl.macjankowski.fps.ch5.Stream
import  pl.macjankowski.fps.ch5.Stream._
val s = cons(1, cons(2, cons(3, empty)))





val t = cons(5, cons(6, cons(7, empty)))





val z = t.append(s)




z foreach println










def nat(n: Int): Stream[Int] = cons(n, nat(n+1))
def nextThree(n: Int): Stream[Int] = nat(n) takeWhile(n+3 >)



nextThree(12) foreach println




s flatMapByFold (x => nextThree(x)) foreach println














































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 10:55
 */


def lift[A,B](f: A => B): Option[A] => Option[B] = _ map f

def m(s: String) = s.reverse
def lifted = lift(m)

println("abcdf")
println(lift(m)(Some("abc")))
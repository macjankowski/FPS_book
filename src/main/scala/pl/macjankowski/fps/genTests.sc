/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 28.08.14
 *         Time: 14:44
 */


val l1 = List(1, 2, 3, 4, 5)
val l2 = l1.reverse
val f: (Int, Int) => Int = (a, b) => a + b
val l3 = for {
  a <- l1
  b <- l2
} yield f(a, b)

l3 foreach println




println("1")
























println("1")
println("1")
println("1")
println("1")
println("1")




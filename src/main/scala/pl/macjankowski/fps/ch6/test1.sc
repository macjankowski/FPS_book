/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:27
 */

import pl.macjankowski.fps.ch6.RandomGen._
import pl.macjankowski.fps.ch6.{RandomGen, RNG}
import pl.macjankowski.fps.other.Methods


val (r, rng) = positiveInt(RNG.simple(123091820312l))


println(r)

val (d, _) = double(rng)

println(d)

val l = List.fill(10)(RandomGen.int)


val f = sequence(l)


val fs = f(RNG.simple(123091820312l))



fs._1 foreach println









ints2(10)(RNG.simple(123091820312l))._1 foreach println









ints(10)(RNG.simple(123091820312l))._1 foreach println









println("Koniec!!!")



/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 01.09.14
 *         Time: 14:38
 */


import pl.macjankowski.fps.ch6.RandomGen._
import pl.macjankowski.fps.ch6.{RandomGen, RNG}

positiveInt2(RNG.simple(2385742873l))._1

val r = map2UsingFlatMap(RandomGen.int, RandomGen.doub)((a:Int,b:Double) => (a + b))


r(RNG.simple(2385742873l))._1
println("Koniec")

val (a, rng2) = RandomGen.int(RNG.simple(2385742873l))


val (b, rng3) = RandomGen.doub(rng2)



a + b
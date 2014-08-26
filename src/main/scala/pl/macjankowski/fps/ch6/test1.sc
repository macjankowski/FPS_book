/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:27
 */

import pl.macjankowski.fps.ch6.RandomGen._
import pl.macjankowski.fps.ch6.RNG


val (r, rng) = positiveInt(RNG.simple(123091820312l))

println(r)

val (d, _) = double(rng)

println(d)
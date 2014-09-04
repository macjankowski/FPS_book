import pl.macjankowski.fps.ch6.{Machine, State}

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 04.09.14
 *         Time: 17:43
 */


val startState = State[Machine, Unit](m => (Unit, m))

def transition: (Machine) => Machine =
  m => m.copy(coins = m.coins + 1)

val m = Machine(true, 10, 10)
val m2 = transition(m)
val m3 = transition(m2)
val m4 = transition(m3)
val m5 = transition(m4)
println(m5)
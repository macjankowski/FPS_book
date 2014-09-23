import pl.macjankowski.fps.ch6.{Machine, State}

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 04.09.14
 *         Time: 17:43
 */

val mmm = Map[String, Unit]("a" -> Unit)


val startState = State[Machine, Unit](m => (Unit, m))


def transition: (Machine) => Machine =
  m => m.copy(coins = m.coins + 1)

def transform: State[Machine, Unit] => State[Machine, Unit] =
  m => m.modify(transition)


val m = State[Machine, Unit](m => (Unit, Machine(true, 10, 10)))

val m2 = transform(m)

val m3 = transform(m2)

val m4 = transform(m3)

val m5 = transform(m4)

println(m5.run())


package pl.macjankowski.fps.ch6

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 04.09.14
 *         Time: 14:43
 */
sealed trait Input

case object Coin extends Input

case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine {

  def simulateMachine(inputs: List[Input]): State[Machine, Int] = {

    def transition(input: Input): (Machine) => Machine = {
      m =>
        input match {
          case Coin =>
            if (m.locked)
              m.copy(locked = false, coins = m.coins + 1)
            else
              m
          case Turn =>
            if (m.locked)
              m
            else if (m.candies > 0)
              m.copy(candies = m.candies - 1, locked = true)
            else m
        }
    }

    def rec(m: State[Machine, Unit], inputs: List[Input]): State[Machine, Unit] = inputs match {
      case Nil => m
      case x :: xs =>
        val newM = m.modify(transition(x))
        rec(newM, xs)
    }

    val startState = State[Machine, Unit](m => (Unit, m))

    val finalState = rec(startState, inputs)

    State(m => {
      val (_, lastState) = finalState.run(m)
      (lastState.coins, lastState)
    })
  }

}



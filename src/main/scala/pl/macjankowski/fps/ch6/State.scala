package pl.macjankowski.fps.ch6

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 01.09.14
 *         Time: 16:18
 */

object State {

}

case class State[S, +A](run: S => (A, S)) {

  def unit[B >: A](a: B): State[S, B] = State(s => (a, s))

  def map[B >: A, C](a: State[S, B])(f: B => C): State[S, C] =
    State(s => {
      val (x, newState) = a.run(s)
      (f(x), newState)
    })

  def map2[B >: A, C >: A, D](a: State[S, B], b: State[S, C])(f: (B, C) => D): State[S, D] =
    State(s => {
      val (x, state2) = a.run(s)
      val (y, state3) = b.run(state2)
      (f(x, y), state3)
    })

  def flatMap[B >: A, C](a: State[S, B])(f: B => State[S, C]): State[S, C] = {
    State[S, C](s => {
      val (r, state2) = a.run(s)
      val (c: C, state3: S) = f(r).run(state2)
      (c, state3)
    })
  }

  def sequence[B >: A](fs: List[State[S,B]]): State[S, List[B]] = fs match {
    case Nil => State(s => (Nil, s))
    case f :: tail =>
      State(s => {
        val (a:B, newS:S) = f.run(s)
        val (l: List[B], lastRng:S) = sequence(tail).run(newS)
        (a :: l, lastRng)
      })
  }



}

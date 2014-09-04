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

  def unit: State[S, A] = State(rng => run(rng))

  def map[C](f: A => C): State[S, C] =
    State(s => {
      val (x, newState) = run(s)
      (f(x), newState)
    })

  def map2[C >: A, D](b: State[S, C])(f: (A, C) => D): State[S, D] =
    State(s => {
      val (x, state2) = run(s)
      val (y, state3) = b.run(state2)
      (f(x, y), state3)
    })

  def flatMap[C](f: A => State[S, C]): State[S, C] = {
    State[S, C](s => {
      val (r, state2) = run(s)
      val (c, state3) = f(r).run(state2)
      (c, state3)
    })
  }

  def sequence[B >: A](fs: List[State[S, B]]): State[S, List[B]] = fs match {
    case Nil => State(s => (Nil, s))
    case f :: tail =>
      State(s => {
        val (a, newS) = f.run(s)
        val (l, lastRng) = sequence(tail).run(newS)
        (a :: l, lastRng)
      })
  }

  def filter(p: A => Boolean): State[S, A] = this

  def foreach(f: A => Unit): State[S, Unit] = {
    State(rng => {
      val (a, s) = run(rng)
      (f(a), s)
    })
  }

}

package pl.macjankowski.fps.ch6

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 01.09.14
 *         Time: 16:18
 */

object State{

}

case class State[S, +A](run: S => (A, S)) {

  def unit(a: A): State[S, A] = State(s => (a, s))

  def map[B](a: State[S, A])(f: A => B): State[S, B] =
    State(s => {
      val (x, newState) = a.run(s)
      (f(x), newState)
    })


}

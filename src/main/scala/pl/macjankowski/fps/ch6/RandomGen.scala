package pl.macjankowski.fps.ch6

import pl.macjankowski.fps.ch2.Currying

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:19
 */
object RandomGen {

  def positiveInt(rng: RNG): (Int, RNG) = {
    val (n, rng2) = rng.nextInt
    val posN = if (n == Int.MinValue) Int.MaxValue else n.abs
    (posN, rng2)
  }

  def double(rng: RNG): (Double, RNG) = {

    var t: (Int, RNG) = positiveInt(rng)
    do {
      t = positiveInt(rng)
    } while (t._1 == Int.MaxValue)

    val (n, rng2) = t
    val d = n.toDouble / Int.MaxValue.toDouble
    (d, rng2)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (n, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((n, d), rng3)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (n, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((d, n), rng3)
  }


  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, rng2) = double(rng)
    val (d2, rng3) = double(rng2)
    val (d3, rng4) = double(rng3)
    ((d1, d2, d3), rng4)
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }


  def positiveMax(n: Int): Rand[Int] =
    if (n == 0) r => (0, r) else map(_.nextInt)(_.abs % n)

  def double2: Rand[Double] =
    map(positiveMax(Int.MaxValue-1))(_.toDouble / Int.MaxValue.toDouble)

  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (a, rng2) = ra(rng)
      val (b, rng3) = rb(rng2)
      (f(a,b), rng3)
    }
  }

}

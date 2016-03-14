package pl.macjankowski.fps.ch6


/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:19
 */
object RandomGen {

  def positiveInt(rng: RNG): (Int, RNG) = {
    val (n, rng2) = rng.nextInt
    if (n == Int.MinValue) positiveInt(rng2) else (n.abs, rng2)
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

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

    if (count > 0) {
      val (n, rng2) = rng.nextInt
      val (l: List[Int], rng3) = ints(count - 1)(rng2)
      (n :: l, rng3)
    } else {
      (Nil, rng)
    }
  }

  val int: Rand[Int] = _.nextInt

  val doub: Rand[Double] = double _

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
    map(positiveMax(Int.MaxValue - 1))(_.toDouble / Int.MaxValue.toDouble)

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (a, rng2) = ra(rng)
      val (b, rng3) = rb(rng2)
      (f(a, b), rng3)
    }
  }


  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = fs match {
    case Nil => rng => (Nil, rng)
    case f :: tail =>
      rng => {
        val (a, newRng) = f(rng)
        val (l: List[A], lastRng) = sequence(tail)(newRng)
        (a :: l, lastRng)
      }
  }

  def ints2(count: Int): RNG => (List[Int], RNG) = {
    val l = List.fill(count)(RandomGen.int)
    sequence(l)
  }


  def positiveInt2: RNG => (Int, RNG) =
    flatMap(_.nextInt) {
      n =>
        rng => {
          if (n != Int.MinValue) (n.abs, rng)
          else positiveInt2(rng)
        }
    }

  def mapUsingFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] =
    flatMap(s) {
      a => rng => (f(a), rng)
    }


  type Rand[+A] = RNG => (A, RNG)

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = {
    rng => {
      val (a, rng2) = f(rng)
      g(a)(rng2)
    }
  }

  def map2UsingFlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a => rng => {
      val (b, rng2) = rb(rng)
      (f(a,b),rng2)
    })
  }

}



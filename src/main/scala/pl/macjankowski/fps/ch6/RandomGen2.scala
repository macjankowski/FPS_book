package pl.macjankowski.fps.ch6

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 25.08.14
 *         Time: 13:19
 */
object RandomGen2 {

  type Rand[+A] = State[RNG, A]

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

  def ints(count: Int): State[RNG, List[Int]] = {

    State(rng =>
      if (count > 0) {
        val (n, rng2) = rng.nextInt
        val (l: List[Int], rng3) = ints(count - 1).run(rng2)
        (n :: l, rng3)
      } else {
        (Nil, rng)
      })
  }

  val int: Rand[Int] = State(_.nextInt)

  val doub: Rand[Double] = State(double _)

  def unit[A](a: A): Rand[A] =
    State(rng => (a, rng))

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    State(rng => {
      val (a, rng2) = s.run(rng)
      (f(a), rng2)
    })


  def positiveMax(n: Int): Rand[Int] =
    if (n == 0) State(r => (0, r)) else map(State(_.nextInt))(_.abs % n)

  def double2: Rand[Double] =
    map(positiveMax(Int.MaxValue - 1))(_.toDouble / Int.MaxValue.toDouble)

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    State(rng => {
      val (a, rng2) = ra.run(rng)
      val (b, rng3) = rb.run(rng2)
      (f(a, b), rng3)
    })
  }


  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = fs match {
    case Nil => State(rng => (Nil, rng))
    case f :: tail =>
      State(rng => {
        val (a, newRng) = f.run(rng)
        val (l: List[A], lastRng) = sequence(tail).run(newRng)
        (a :: l, lastRng)
      })
  }

  def ints2(count: Int): Rand[List[Int]] = {
    val l = List.fill(count)(State(RandomGen.int))
    sequence(l)
  }


  def positiveInt2: Rand[Int] =
    flatMap(State(_.nextInt)) {
      n =>
        State(rng => {
          if (n != Int.MinValue) (n.abs, rng)
          else positiveInt2.run(rng)
        })
    }

  def mapUsingFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] =
    flatMap(s) {
      a => State(rng => (f(a), rng))
    }


  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = {
    State(rng => {
      val (a, rng2) = f.run(rng)
      g(a).run(rng2)
    })
  }

  def map2UsingFlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a =>
      State(rng => {
        val (b, rng2) = rb.run(rng)
        (f(a, b), rng2)
      })
    )
  }

//  def filter[A](ra: Rand[A])(p: A => Boolean): Rand[A] =
//    flatMap(State(_.nextInt)) {
//      n =>
//        State(rng => {
//          if (n != Int.MinValue) (n.abs, rng)
//          else positiveInt2.run(rng)
//        })
//    }

}



package pl.macjankowski.fps.ch5

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 16:11
 */
trait Stream[+A] {

  import Stream._

  def uncons: Option[(A, Stream[A])]

  def isEmpty: Boolean = uncons.isEmpty

  def toList: List[A]

  def map[B](f: A => B): Stream[B]

  def take(n: Int): Stream[A]

  def takeWhile(p: A => Boolean): Stream[A]

  def foreach(f: A => Unit): Unit

  def foldRight[B](z: => B)(f: (A, => B) => B): B

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  def takeWhileByFold(p: A => Boolean): Stream[A] =
    foldRight(empty[A]) {
      (a, b) => if (p(a)) cons(a, b) else empty[A]
    }

  def mapByFold[B](f: A => B): Stream[B] =
    foldRight(empty[B])((a, b) => cons(f(a), b))

  def filter(p: A => Boolean): Stream[A] =
    foldRight(empty[A]) {
      (a, b) => if (p(a)) cons(a, b) else b
    }

  def append[B >: A](s: Stream[B]): Stream[B] =
    foldRight(s)((a, b) => cons(a, b))

  def flatMapByFold[B](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((a, b) => f(a) append b)

  def mapByUnfold[B](f: A => B): Stream[B] =
    unfold(uncons: Option[(A, Stream[A])])(s =>
      s map {
        case (hd, tl) => (f(hd), tl.uncons)
      }
    )

  def takeByUnfold(n: Int): Stream[A] =
    unfold(n, (uncons: Option[(A, Stream[A])])) {
      case (k, s) =>
        if (k > 0) s.map {
          case (hd, tl) => (hd, (k - 1, tl.uncons))
        }
        else None
    }


  def takeWhileByUnfold(p: A => Boolean): Stream[A] =
    unfold(uncons: Option[(A, Stream[A])])(s =>
      s.filter(x => p(x._1)).map {
        case (hd, tl) => (hd, tl.uncons)
      }
    )


  def zipByUnfold[B](s: Stream[B]): Stream[(A, B)] = {
    unfold((this, s)){
      case (sA,sB) => (sA.uncons, sB.uncons) match {
        case (Some((a, tsA)), Some((b, tsB))) => Some(((a,b), (tsA, tsB)))
        case _ => None
      }
      case _ =>  None
    }
  }

  def scanRight[B](acc: B)(f: (A, => B) => B): Stream[B]

}

object Stream {
  def empty[A]: Stream[A] =
    new Stream[A] {
      def uncons = None

      def toList: List[A] = Nil

      def map[B](f: A => B): Stream[B] = empty[B]

      def take(n: Int): Stream[A] = empty[A]

      def takeWhile(p: A => Boolean): Stream[A] = empty[A]

      def foreach(f: A => Unit): Unit = {}

      def foldRight[B](z: => B)(f: (A, => B) => B): B = z

      def scanRight[B](acc: B)(f: (A, => B) => B): Stream[B] =  empty[B]

    }

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] =
    new Stream[A] {
      lazy val uncons = Some((hd, tl))

      def toList: List[A] = hd :: tl.toList

      def map[B](f: A => B): Stream[B] = cons(f(hd), tl map f)

      def take(n: Int): Stream[A] =
        if (n > 0) cons(hd, tl.take(n - 1))
        else empty[A]

      def takeWhile(p: A => Boolean): Stream[A] =
        if (p(hd)) cons(hd, tl takeWhile (p))
        else empty[A]

      def foreach(f: A => Unit): Unit = {
        f(hd)
        tl foreach f
      }

      def foldRight[B](z: => B)(f: (A, => B) => B): B =
        f(hd, tl.foldRight(z)(f))

      def scanRight[B](acc: B)(f: (A, => B) => B): Stream[B] =  {
        val ps = tl.scanRight(acc)(f)
        ps.uncons match {
          case Some((h,t)) => cons(f(hd, h), ps)
          case None => cons(f(hd, acc), cons(acc, ps))
        }
      }

    }

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  def unfold[B, S](z: S)(f: S => Option[(B, S)]): Stream[B] =
    f(z) match {
      case None => empty[B]
      case Some((a, s)) => cons[B](a, unfold[B, S](s)(f))
    }
}

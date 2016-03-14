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

  //  def mapByUnfold[B](f: A => B): Stream[B]


  //  def takeByUnfold(n: Int): Stream[A]
  //  def takeWhileByUnfold(p: A => Boolean): Stream[A]
  //  def zipByUnfold

  def scanRight[B](z: B)(f: (A, B) => B): Stream[B]

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

      def scanRight[B](z: B)(f: (A, B) => B): Stream[B] = empty[B]
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


      //      def mapByUnfold[B](f: A => B): Stream[B] =
      //        unfold(hd)(s => Some(f(hd)))

      def scanRight[B](z: B)(f: (A, B) => B): Stream[B] = {

        def go(z: B)(s: Stream[A])(g: (A, B, Stream[A]) => Stream[B]): Stream[B] =
          g(hd, z, s)

        go(z)(cons(hd, tl))((a, b, s) => s.uncons match {
          case None => empty[B]
          case Some((h, t)) => cons(f(a, b), cons(h, t).scanRight(z)(f))
        })
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

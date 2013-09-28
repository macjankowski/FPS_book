package pl.macjankowski.fps.ch3

import java.util.NoSuchElementException

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
sealed trait List[+A] {
  def tail: List[A]
  //  def drop(n: Int): List[A]
}

case object Nil extends List[Nothing] {
  def tail = throw new NoSuchElementException
  //  def drop(n: Int): List[A] = throw new NoSuchElementException
}

case class Cons[+A](head: A, val tail: List[A]) extends List[A] {

}

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail2[A](l: List[A]): List[A] = l match {
    case Nil => throw new NoSuchElementException
    case Cons(x, xs) => xs
  }

  def drop[A](n: Int, l: List[A]): List[A] = {
    if (n == 0) l
    else l match {
      case Nil => throw new NoSuchElementException
      case Cons(x, xs) => drop(n - 1, xs)
    }
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) dropWhile(xs)(f) else l
  }

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def lengthR[A](l: List[A]): Int = foldRight(l, 0)((a, acc) => acc + 1)

  def sum[A](l: List[A])(implicit n: Numeric[A]): A = foldLeft(l, n.zero)(n.plus(_, _))

  def product[A](l: List[A])(implicit n: Numeric[A]): A = foldLeft(l, n.one)(n.times(_, _))

  def lengthL[A](l: List[A]): Int = foldLeft(l, 0)((acc, a) => acc + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, Nil: List[A])((acc, a) => Cons(a, acc))

  def foldLeft2[A, B](l: List[A], z: B)(f: (B, A) => B): B =
    foldRight(reverse(l), z)((a, acc) => f(acc, a))

  def append[A](l1: List[A], l2: List[A]): List[A] = foldRight(l1, l2)(Cons(_, _))

  def flatten[A](ll: List[List[A]]): List[A] = foldRight(ll, Nil: List[A])(append(_, _))

  def addOne[A](l: List[A])(implicit n: Numeric[A]): List[A] =
    foldRight(l, Nil: List[A])((a, acc) => Cons(n.plus(a, n.one), acc))

  def addOneR[A](l: List[A])(implicit n: Numeric[A]): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) => Cons(n.plus(x, n.one), addOneR(xs))
  }

  def addOneRT(l: List[Int]): List[Int] = {

    def go(newL: List[Int], oldL: List[Int]): List[Int] = oldL match {
      case Nil => newL
      case Cons(x, xs) => go(Cons(x + 1, newL), xs)
    }

    reverse(go(Nil, l))
  }

  def map[A, B](l: List[A])(f: A => B): List[B] =
    foldRight(l, Nil: List[B])((a, acc) => Cons(f(a), acc))

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = flatten(map(l)(f))

  def filter[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(a => if (f(a)) List(a) else Nil)

  def execFOnElemFromTwoLists[A, B](l1: List[A], l2: List[A])(f: (A, A) => B): List[B] = {

    if (lengthL(l1) != lengthL(l2)) throw new NoSuchElementException

    (l1, l2) match {
      case (Nil, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), execFOnElemFromTwoLists(xs, ys)(f))
    }
  }

  def isPrefix[A](l: List[A], prefix: List[A]): Boolean = (l, prefix) match {
    case (Nil, Nil) => true
    case (Cons(x, xs), Nil) => true
    case (Nil, Cons(x, xs)) => false
    case (Cons(x, xs), Cons(y, ys)) => if (x != y) false else isPrefix(xs, ys)
  }

  def hasSubsequence[A](l: List[A], sub: List[A]): Boolean = {
    if (isPrefix(l, sub)) true
    else l match {
      case Nil => false
      case Cons(x, xs) => hasSubsequence(xs, sub)
    }
  }

}
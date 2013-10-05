package pl.macjankowski.fps.ch3

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(left, right) => 1 + size(left) + size(right)
  }

  def max[A](t: Tree[A])(implicit n: Ordering[A]): A = t match {
    case Leaf(value) => value
    case Branch(left, right) => n.max(max(left), max(right))
  }

  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(value) => 1
    case Branch(left, right) => 1 + (depth(left) max depth(right))
  }

  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(value) => Leaf(f(value))
    case Branch(left, right) => Branch(map(left)(f), map(right)(f))
  }

  def fold[A, B](t: Tree[A], acc: B)(f: (A, B) => B): B = t match {
    case Leaf(value) => f(value, acc)
    case Branch(left, right) => fold(left, fold(right, acc)(f))(f)
  }

  def maxF(t: Tree[Int]): Int = fold(t, 0)(_ max _)
  
  def sizeF(t: Tree[Int]): Int = fold(t, 0)((_, acc) => acc + 1) 

}
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

  def max[A](t: Tree[A])(implicit n: Numeric[A]): A = t match {
    case Leaf(value) => value
    case Branch(left, right) => n.max(max(left), max(right))
  }

  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(value) => 1
    case Branch(left, right) => 1 + (depth(left) max depth(right))
  }
}
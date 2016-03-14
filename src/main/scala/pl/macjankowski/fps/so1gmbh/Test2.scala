package pl.macjankowski.fps.so1gmbh

import scala.collection.JavaConversions._
import scala.annotation.tailrec
import scala.collection.mutable.Stack

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 06.03.16
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
case class Tree(var x: Int, var l: Tree, var r: Tree)

object Test2 {

  def walkTheTree(t: Tree, parent: Int, acc: Int): Int = {

    if (t == null) return acc

    val newAcc = if (t.x >= parent) acc + 1 else acc
    val newParent = Math.max(parent, t.x)
    walkTheTree(t.r, newParent, walkTheTree(t.l, newParent, newAcc))
  }

  private def walkTheTreeIt(t: Tree): Int = {

    val parentStack = Stack[(Tree, Int)]()
    parentStack.push(null)
    var p: (Tree, Int) = (t, t.x)
    var acc = 0

    while (p != null) {
      val top = p._1
      val parent = p._2
      acc = if (top.x >= parent) acc + 1 else acc
      if (top.l != null)
        parentStack.push((top.l, Math.max(parent, top.x)))
      if (top.r != null)
        parentStack.push((top.r, Math.max(parent, top.x)))

      p = parentStack.pop()
    }

    acc
  }

  def solution(T: Tree): Int = {

    if (T == null) return 0

    walkTheTreeIt(T)
  }

  def main(args: Array[String]){

    val tree = Tree(5, Tree(3, Tree(20, null, null), Tree(21, null, null)), Tree(10, Tree(1, null, null), null))
    val tree2 = Tree(8, Tree(2, Tree(8, null, null), Tree(7, null, null)), Tree(6, null, null))

    val ret = solution(tree)
    println(ret)

    val ret2 = solution(tree2)
    println(ret2)
  }


}

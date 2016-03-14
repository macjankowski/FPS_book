package pl.macjankowski.fps.so1gmbh

import scala.collection.mutable.Stack

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 06.03.16
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
object Test2Own {

  def countVisible(T: Tree, visibleCount: Int, heighest: Int): Int = {
    if (T == null) visibleCount
    else{
      val newVisibleCount = if(T.x < heighest) visibleCount else visibleCount + 1
      val newHighest = Math.max(heighest, T.x)
      countVisible(T.l, countVisible(T.r, newVisibleCount, newHighest), newHighest)
    }
  }

  private def countVisibleIt(t: Tree): Int = {

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
    //countVisible(T, 0, -100000)

    if (T == null)  0
    else countVisibleIt(T)
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

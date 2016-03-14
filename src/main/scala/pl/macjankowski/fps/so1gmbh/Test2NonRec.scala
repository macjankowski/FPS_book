package pl.macjankowski.fps.so1gmbh

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 06.03.16
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
object Test2NonRec {

  def depth(T: Tree): Int = {
    if (T == null) -1
    else 1 + Math.max(depth(T.l), depth(T.r))
  }

  def solution(T: Tree): Int = depth(T)

  def main(args: Array[String]){

    val tree = Tree(5, Tree(3, Tree(20, null, null), Tree(21, null, null)), Tree(10, Tree(1, null, null), null))
    val tree2 = Tree(8, Tree(2, Tree(8, null, null), Tree(7, null, null)), Tree(6, null, null))

    val ret = solution(tree)
    println(ret)

    val ret2 = solution(tree2)
    println(ret2)
  }
}

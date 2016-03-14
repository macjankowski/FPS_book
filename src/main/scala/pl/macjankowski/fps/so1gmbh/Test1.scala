package pl.macjankowski.fps.so1gmbh

import scala.annotation.tailrec

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 06.03.16
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
object Test1 {

  def main(args: Array[String]){
   val tab = Array(1,4,-1,3,2)
   val ret =  solution(tab)
    println(ret)
  }

  def solution(A: Array[Int]): Int = {

    @tailrec def go(index: Int, length: Int): Int = {
      if(index < 0) length
      else go(A(index), length + 1)
    }

    go(0, 0)
  }
}

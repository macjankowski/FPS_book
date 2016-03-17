package pl.macjankowski.fps.so1gmbh

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17.03.16
 * Time: 02:09
 * To change this template use File | Settings | File Templates.
 */
object Test3InPlace {

  def main(args: Array[String]) {

    val A = Array(1, 12, 42, 70, 36, -4, 43, 15)
    val B = Array(5, 15, 44, 72, 36, 2, 69, 24)

    scala.util.Sorting.stableSort(A)
    scala.util.Sorting.stableSort(B)

    println("before")
    A foreach (x => print(x + ", "))
    println
    B foreach (x => print(x + ", "))

    var i = 0
    while (i < A.length - 1) {
      if (B(i) >= A(i + 1)) {
        if (A(i) < B(i)) {
          B(i) = -1
        } else {
          A(i) = -1
        }

        if (A(i + 1) < B(i + 1)) {
          A(i + 1) = -1
        } else {
          B(i + 1) = -1
        }
        i = i + 2
      } else {
        i = i + 1
      }
    }

    println("\n\nafter")
    A filter (_ != -1) foreach (x => print(x + ", "))
    println
    B filter (_ != -1) foreach (x => print(x + ", "))

  }
}

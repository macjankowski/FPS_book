package pl.macjankowski.fps.ch2

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import pl.macjankowski.fps.ch3.Branch
import pl.macjankowski.fps.ch3.Leaf
import pl.macjankowski.fps.ch3.Tree

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class TestTree extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val testSize = Table(
    ("tree", "size"),
    (Leaf(1), 1),
    (Branch(Leaf(2), Leaf(3)), 3),
    (Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), 5),
    (Branch(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Branch(Leaf(4), Leaf(5))), 9))

  describe("Tree.size") {

    forAll(testSize) { (tree, size) =>

      it(s"should calculate number of nodes in a tree $tree and return $size") {
        Tree.size(tree) should equal(size)
      }
    }
  }
}
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
  
  describe("Tree.sizeF") {
    forAll(testSize) { (tree, size) =>
      it(s"should calculate number of nodes in a tree $tree and return $size") {
        Tree.sizeF(tree) should equal(size)
      }
    }
  }

  val testMax = Table(
    ("tree", "max"),
    (Leaf(1), 1),
    (Branch(Leaf(2), Leaf(3)), 3),
    (Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), 3),
    (Branch(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Branch(Leaf(4), Leaf(5))), 5),
    (Branch(Branch(Leaf(11), Branch(Leaf(200), Leaf(3))), Branch(Leaf(4), Leaf(5))), 200))

  describe("Tree.max") {

    forAll(testMax) { (tree, max) =>

      it(s"should find maximal element of a $tree and return $max") {
        Tree.max(tree) should equal(max)
      }
    }
  }
  
  describe("Tree.maxF") {

    forAll(testMax) { (tree, max) =>

      it(s"should find maximal element of a $tree and return $max") {
        Tree.maxF(tree) should equal(max)
      }
    }
  }

  val testDepth = Table(
    ("tree", "depth"),
    (Leaf(1), 1),
    (Branch(Leaf(2), Leaf(3)), 2),
    (Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), 3),
    (Branch(Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Branch(Leaf(4), Leaf(5))), 4))

  describe("Tree.depth") {

    forAll(testDepth) { (tree, depth) =>

      it(s"should calculate depth of a $tree and return $depth") {
        Tree.depth(tree) should equal(depth)
      }
    }
  }
}
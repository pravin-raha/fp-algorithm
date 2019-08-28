package tree

import cats.data.State

import scala.annotation.tailrec

sealed trait BinarySearchTree
case class BinarySearchNode(key: Int, value: Int, left: BinarySearchTree, right: BinarySearchTree)
    extends BinarySearchTree
case object BinarySearchLeaf extends BinarySearchTree

object BinarySearchNode {

  def get(key: Key): State[BinarySearchTree, Option[Value]] = {
    @tailrec
    def loop(keyL: Key, bstL: BinarySearchTree): Option[Value] = bstL match {
      case BinarySearchLeaf => None
      case BinarySearchNode(k, v, left, right) =>
        if (keyL < k) loop(keyL, left)
        else if (keyL > k) loop(keyL, right)
        else Option(v)
    }
    State(bst => (bst, loop(key, bst)))
  }

  def put(key: Key, value: Value): State[BinarySearchTree, Unit] = {
    def loop(keyL: Key, valueL: Value, bstL: BinarySearchTree): BinarySearchTree = bstL match {
      case BinarySearchLeaf => BinarySearchNode(keyL, valueL, BinarySearchLeaf, BinarySearchLeaf)
      case b @ BinarySearchNode(k, _, left, right) =>
        if (keyL > k) b.copy(right = loop(keyL, valueL, right))
        else if (keyL < k) b.copy(left = loop(keyL, valueL, left))
        else b
    }
    State(bst => (loop(key, value, bst), ()))
  }

  @tailrec
  private def findMin(bst: BinarySearchTree): Option[(Key, Value)] = bst match {
    case BinarySearchLeaf                            => None
    case BinarySearchNode(k, v, BinarySearchLeaf, _) => Some((k, v))
    case BinarySearchNode(_, _, l, _)                => findMin(l)
  }

  private def deleteMin(bst: BinarySearchTree): BinarySearchTree = bst match {
    case BinarySearchLeaf                                => BinarySearchLeaf
    case BinarySearchNode(_, _, BinarySearchLeaf, right) => right
    case b @ BinarySearchNode(_, _, left, _)             => b.copy(left = deleteMin(left))
  }

  def delete(key: Key): State[BinarySearchTree, Unit] = {
    def loop(keyL: Key, bst: BinarySearchTree): BinarySearchTree = bst match {
      case BinarySearchLeaf                                         => BinarySearchLeaf
      case BinarySearchNode(k, _, BinarySearchLeaf, r) if keyL == k => r
      case BinarySearchNode(k, _, l, BinarySearchLeaf) if keyL == k => l
      case BinarySearchNode(k, _, l, r) if keyL == k =>
        val minVal = findMin(r).get
        BinarySearchNode(minVal._1, minVal._2, l, deleteMin(r))
      case BinarySearchNode(k, v, l, r) if keyL > k => BinarySearchNode(k, v, l, loop(keyL, r))
      case BinarySearchNode(k, v, l, r)             => BinarySearchNode(k, v, loop(keyL, l), r)
    }
    State(bst => (loop(key, bst), ()))
  }

}

object TestBinarySearchTree {

  import BinarySearchNode._

  def main(args: Array[String]): Unit = {
    val bst: State[BinarySearchTree, Option[Value]] = for {
      _   <- put(10, 10)
      _   <- put(7, 20)
      _   <- put(30, 30)
      _   <- put(7, 50)
      _   <- put(89, 60)
      _   <- put(55, 70)
      _   <- delete(55)
      _   <- put(55, 56)
      res <- get(55)
    } yield res

    println(bst.runA(BinarySearchLeaf).value)
  }
}

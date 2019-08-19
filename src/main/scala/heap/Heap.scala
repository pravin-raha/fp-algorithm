package heap

import Math.max

import cats.data.State

/*
  A heap is a binary tree satisfying the heap property.
  The heap property: The value at a node is larger than the value at its two children
 */
sealed trait Heap

case class HNode(value: Int, left: Heap, right: Heap) extends Heap

case object HLeaf extends Heap

object Heap {

  //TODO Tree to Heap
  /**
   * Helper for creating Heap from tree and for repairing heap
   *
   * Assume that L and R below, with roots y and z, are already heaps
   *      x
   *    /   \
   *   y     z
   *  / \   / \
   * t1 t2 t3 t4
   *
   * How do we ensure that the tree rooted at x is a heap
   * If x >= max y z, all is okay, else swap x with max y z, say z
   * Now heap property holds at the root
   * L is undisturbed, but R might fail to be a heap
   * Recursively repair R
   * This process is called sifting
   *
   * @param heap the heap we want to repair
   * @return the repaired heap
   */
  def shift(heap: Heap): Heap = heap match {
    case HLeaf                                       => HLeaf
    case node @ HNode(_, HLeaf, HLeaf)               => node
    case node @ HNode(x, ch @ HNode(y, _, _), HLeaf) => if (x >= y) node else HNode(y, shift(ch.copy(value = x)), HLeaf)
    case node @ HNode(x, HLeaf, ch @ HNode(y, _, _)) => if (x >= y) node else HNode(y, HLeaf, shift(ch.copy(value = x)))
    case node @ HNode(x, leftNode @ HNode(y, _, _), rightNode @ HNode(z, _, _)) =>
      if (x >= max(y, z)) node
      else if (y >= max(x, z)) HNode(y, shift(leftNode.copy(value = x)), rightNode)
      else HNode(z, leftNode, shift(rightNode.copy(value = x)))
  }

  private def realign(heap: Heap): Heap = heap match {
    case HLeaf                => HLeaf
    case h @ HNode(x, hl, hr) => if (size(hl) > size(hr)) HNode(x, hr, hl) else h
  }

  def mkRightist(heap: Heap): Heap = heap match {
    case HLeaf                     => HLeaf
    case HNode(value, left, right) => realign(HNode(value, mkRightist(left), mkRightist(right)))
  }

  private def union(h1: Heap, h2: Heap): Heap = (h1, h2) match {
    case (h, HLeaf) => h
    case (HLeaf, h) => h
    case (HNode(x, l1, r1), HNode(y, l2, r2)) =>
      if (x < y) realign(HNode(y, union(h1, l2), r2))
      else realign(HNode(x, union(l1, h2), r1))
  }

  def size(h: Heap): Int = h match {
    case HLeaf          => 0
    case HNode(_, l, r) => 1 + size(l) + size(r)
  }

  def insert(value: Int): State[Heap, Unit] = State { heap =>
    (union(heap, HNode(value, HLeaf, HLeaf)), ())
  }

  def delMax: State[Heap, Int] = State {
    case HNode(x, left, right) => (union(left, right), x)
  }

}

object Test {

  import Heap._

  val program: State[Heap, Int] = for {
    _   <- insert(10)
    _   <- insert(20)
    _   <- insert(15)
    _   <- insert(17)
    _   <- insert(30)
    _   <- insert(88)
    _   <- insert(69)
    _   <- insert(40)
    _   <- insert(74)
    _   <- delMax
    res <- delMax
  } yield res

  def main(args: Array[String]): Unit = {
    println(program.runA(HLeaf).value)
  }

}

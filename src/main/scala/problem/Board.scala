package problem
import scala.collection.immutable

sealed trait Direction
case object North     extends Direction
case object South     extends Direction
case object East      extends Direction
case object West      extends Direction
case object NorthEast extends Direction
case object NorthWest extends Direction
case object SouthEast extends Direction
case object SouthWest extends Direction

object Board {
  val (n, m) = (3, 3)

//  val board: List[List[Boolean]] = ???

  def validMove(x: Int, y: Int): Boolean = x >= 0 && x < n && y >= 0 && y < m

  def createAllMoves(x: Int, y: Int): List[(Int, Int)] = {
    (x + 1, y) :: (x, y + 1) :: (x - 1, y) :: (x, y - 1) :: (x + 1, y + 1) :: (x - 1, y - 1) :: (x + 1, y - 1) :: (
      x - 1,
      y + 1
    ) :: Nil
  }

  def createAllPossibleMove(x: Int, y: Int, filter: List[Node]): List[(Int, Int)] =
    createAllMoves(x, y)
      .filter(a => validMove(a._1, a._2))
      .filter(t => !filter.map(n => n.value).contains(t))

  def tupleToNode(tuple: (Int, Int)): Node = Node(tuple, List.empty[Node])

  def createTree(node: Node): Tree = {
    val clildrenV: List[Node] = createAllPossibleMove(node.value._1, node.value._2,Nil)
      .map(tupleToNode)

    def loop(nodes: List[Node],parents:List[Node]): List[Node] = nodes match {
      case Nil => Nil
      case x :: xs =>
        val nodes: List[Node] = createAllPossibleMove(x.value._1, x.value._2,parents).map(tupleToNode)
        x.copy(children = loop(nodes,x::parents)) :: loop(xs,parents)
    }
    Node(
      node.value,
      loop(clildrenV,node::Nil)
    )
  }

  def main(args: Array[String]): Unit = {
// val filter:List[Node] = Nil

//    println(filter.map(n => n.value).contains((0, 0)))
    val tree: Tree = createTree(Node((2, 2), Nil))
    tree



//    val (path, pathLength): (List[Node], Int) = tree
//      .paths()
//      .map(p => (p, p.length))
//      .minBy(_._2)
//
//    if (pathLength == n * m) {
//      path.foreach(n => println(s"(${n.value._1},${n.value._2})"))
//    } else println("Not possible")
  }

}

trait Tree {
  def childLevel1(parentNode: Node): List[Node]

  def paths(): List[List[Node]]
}

case class Node(value: (Int, Int), children: List[Node]) extends Tree {
  override def childLevel1(parentNode: Node): List[Node] = ???
  override def paths(): List[List[Node]]                 = ???
}

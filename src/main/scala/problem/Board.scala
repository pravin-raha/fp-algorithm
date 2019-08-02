package problem

trait Tree

case class Node(value: (Int, Int), children: List[Node]) extends Tree

object Board {
  val (n, m) = (3, 3)

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
    val clildrenV: List[Node] = createAllPossibleMove(node.value._1, node.value._2, Nil)
      .map(tupleToNode)

    def loop(nodes: List[Node], parents: List[Node]): List[Node] = nodes match {
      case Nil => Nil
      case x :: xs =>
        val nodes: List[Node] = createAllPossibleMove(x.value._1, x.value._2, parents).map(tupleToNode)
        x.copy(children = loop(nodes, x :: parents)) :: loop(xs, parents)
    }

    Node(
      node.value,
      loop(clildrenV, node :: Nil)
    )
  }

  def merge(tuple: (Int, Int), list: List[List[(Int, Int)]]): List[List[(Int, Int)]] =
    list.map(l => tuple :: l)

  def getPaths(node: Node): List[List[(Int, Int)]] = {

    def loop(node: List[Node]): List[List[(Int, Int)]] = node match {
      case Nil      => List(List.empty)
      case x :: Nil => merge(x.value, loop(x.children))
      case x :: xs  => merge(x.value, loop(x.children)) ::: loop(xs)
    }

    loop(node :: Nil)
  }

  def main(args: Array[String]): Unit = {

    val tree: Tree = createTree(Node((2, 2), Nil))
    tree match {
      case node: Node =>
        val path = getPaths(node)
        println(path.map(p => (p, p.length)).maxBy(_._2))
        path.map(println)
    }
  }
}

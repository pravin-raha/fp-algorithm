package sort

object MergeSort {

  private def merge(left: List[Int], right: List[Int]): List[Int] =
    (left, right) match {
      case (_, Nil) => left
      case (Nil, _) => right
      case (lHead :: lTail, rHead :: rTail) =>
        if (lHead > rHead) rHead :: merge(lHead :: lTail, rTail)
        else lHead :: merge(lTail, rHead :: rTail)
    }

  def sort(list: List[Int]): List[Int] = list match {
    case Nil      => Nil
    case _ :: Nil => list
    case _ =>
      val n = list.length / 2
      val (left, right) = list.splitAt(n)
      merge(sort(left), sort(right))
  }

  def main(args: Array[String]): Unit = {
    val l = List(2, 1, 5, 4, 8, 9, 6, 3, 1, 5)
    val l2 = List(1, 2, 3, 4, 5, 6)
    val l3 = l2.reverse

    println(sort(l) == l.sorted)
    println("========================")
    println(sort(l2) == l2.sorted)
    println("========================")
    print(sort(l3) == l3.sorted)
  }

}

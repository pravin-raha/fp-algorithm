package sort

object InsertionSort {

  def insert(x: Int, list: List[Int]): List[Int] = list match {
    case Nil       => x :: Nil
    case h :: tail => if (x < h) x :: h :: tail else h :: insert(x, tail)
  }

  def sort(list: List[Int]): List[Int] = list match {
    case Nil     => Nil
    case x :: xs => insert(x, sort(xs))
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

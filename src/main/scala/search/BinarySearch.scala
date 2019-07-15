package search

object BinarySearch {

  def binarySearch(list: List[Int], key: Int): Boolean = {

    def search(l: List[Int], mid: Int): Boolean = l match {
      case Nil      => false
      case x :: Nil => x == key
      case xs =>
        val midValue = xs(mid)
        val (fh, sh) = xs.splitAt(mid)
        if (midValue < key) search(sh, sh.length / 2)
        else if (midValue > key) search(fh, fh.length / 2)
        else true
    }

    search(list, list.length / 2)
  }

  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 4, 5, 6, 7, 8, 9)

    println(binarySearch(l, 1))
    println(binarySearch(l, 4))
    println(binarySearch(l, 5))
    println(binarySearch(l, 0))
  }
}

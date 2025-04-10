package array

import scala.annotation.tailrec

object PascalTree {

  private def pascalRecursion(n: Int): Seq[Int] = {
    if (n == 0) {
      Seq(1)
    } else {
      val lastRow = pascalRecursion(n - 1)
      (0 to n) map {
        case 0 | `n` => 1
        case a =>
          lastRow(a - 1) + lastRow(a)
      }
    }
  }

  private def pascalTailRecursion(n: Int): Seq[Int] = {
    @tailrec
    def loop(k: Int, current: List[Int]): List[Int] = {
      if (k >= n) current
      else {
        val next = (0 +: current).lazyZip(current :+ 0).map(_ + _)
        loop(k + 1, next)
      }
    }

    loop(0, List(1))
  }

  private def pascal(n: Int): Seq[Int] = {
    var lastRow    = Seq.empty[Int]
    var currentRow = Seq.empty[Int]
    if (n == 0) {
      return lastRow
    }
    for (i <- 0 to n) {
      lastRow = currentRow
      currentRow = Seq.empty
      for (j <- 0 to i) {
        if (j == 0 || j == i) {
          currentRow = currentRow.appended(1)
        } else {
          currentRow = currentRow.appended(lastRow(j - 1) + lastRow(j))
        }
      }
    }
    currentRow
  }
  def main(args: Array[String]): Unit = {
    println(pascal(3))
    println(pascalRecursion(3))
    println(pascalTailRecursion(3))
  }
}

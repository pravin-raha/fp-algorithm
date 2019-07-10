package unionfind

import scala.collection.mutable.ListBuffer

class QuickUnionUF(num: Int) {

  private val state: ListBuffer[Int] = ListBuffer.range(0, num)

  private def root(a: Int): Int = {
    var i = a
    while (state(i) != i) i = state(i)
    i
  }

  def union(a: Int, b: Int): Unit = {
    val p = root(a)
    val q = root(b)
    state(p) = q
  }

  def connected(a: Int, b: Int): Boolean = root(a) == root(b)
}

object QuickUnionUF {
  def main(args: Array[String]): Unit = {
    val uf = new QuickUnionUF(10)
    uf.union(0, 2)
    uf.union(2, 4)
    print(uf.connected(0, 4))
  }
}

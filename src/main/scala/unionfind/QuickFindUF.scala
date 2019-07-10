package unionfind

import scala.collection.mutable.ListBuffer

class QuickFindUF(num: Int) {

  private var state: ListBuffer[Int] = ListBuffer.range(0, num)

  def union(a: Int, b: Int): Unit = {
    val pid = state(a)
    val qid = state(b)

    state = state.map(i => if (i == pid) qid else i)
  }

  def connected(a: Int, b: Int): Boolean = state(a) == state(b)

}

object QuickFindUF {
  def main(args: Array[String]): Unit = {

    val uf = new QuickFindUF(10)
    uf.union(0, 2)
    uf.union(2, 4)
    print(uf.connected(0, 5))
  }
}

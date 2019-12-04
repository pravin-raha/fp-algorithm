package unionfind

import cats.data.State

object FPQuickFindUF {

  type UnionSet = List[Int]

  def initialState(n: Int): List[Int] = List.range[Int](0, n)

  def union(a: Int, b: Int): State[UnionSet, Unit] =
    State(s => {
      val pid      = a
      val qid      = b
      val newState = s.map(i => if (i == pid) qid else i)
      (newState, ())
    })

  def find(a: Int, b: Int): State[UnionSet, Boolean] =
    State(s => (s, s(a) == s(b)))
}

object FPQuickFindUFApp {
  import FPQuickFindUF._

  val programTrue: State[UnionSet, Boolean] = for {
    _ <- union(1, 3)
    _ <- union(1, 4)
    r <- find(1, 3)
  } yield r

  val programFalse: State[UnionSet, Boolean] = for {
    _ <- union(1, 3)
    _ <- union(1, 4)
    r <- find(1, 6)
  } yield r

  def main(args: Array[String]): Unit = {
    println(programTrue.runA(initialState(8)).value)
    println(programFalse.runA(initialState(8)).value)
  }
}

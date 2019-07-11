package stack

import cats.data.State

object StackFP {

  type Stack = List[Int]

  val empty = List.empty[Int]

  val peek: State[Stack, Int] = State {
    case x :: xs => (x :: xs, x)
  }

  val pop: State[Stack, Int] = State {
    case x :: xs => (xs, x)
  }

  def push(num: Int): State[Stack, Unit] = State(xs => (num :: xs, ()))

}

object StackFPApp {

  import StackFP._

  val program: State[Stack, Int] = for {
    _ <- push(10)
    _ <- push(20)
    a <- pop
    b <- pop
    _ <- push(a + b)
    res <- peek
  } yield res

  def main(args: Array[String]): Unit = {
    println(program.runA(empty).value)
  }

}

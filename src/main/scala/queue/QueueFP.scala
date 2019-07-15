package queue

import cats.data.State

object QueueFP {

  type Queue = List[Int]

  val empty: Queue = List.empty

  val deQueue: State[Queue, Int] = State {
    case x :: xs => (xs, x)
  }

  def inQueue(a: Int): State[Queue, Unit] = State(
    s => (s :+ a, ())
  )

}

object QueueFPApp {
  import QueueFP._

  val program: State[Queue, Int] = for {
    _ <- inQueue(1)
    _ <- inQueue(2)
    _ <- inQueue(3)
    _ <- inQueue(4)
    a <- deQueue
  } yield a

  def main(args: Array[String]): Unit = {
    println(program.run(empty).value)
  }

}

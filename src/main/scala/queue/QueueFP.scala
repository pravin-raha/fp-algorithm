package queue

import cats.data.State

object QueueFP {

  type Queue = List[Int]

  val empty: Queue = List.empty

  val deQueue: State[Queue, Int] = State {
    case x :: xs => (xs, x)
  }

  def enQueue(a: Int): State[Queue, Unit] = State(
    s => (s :+ a, ())
  )

}

object QueueFPApp {
  import QueueFP._

  val program: State[Queue, Int] = for {
    _ <- enQueue(1)
    _ <- enQueue(2)
    _ <- enQueue(3)
    _ <- enQueue(4)
    a <- deQueue
  } yield a

  def main(args: Array[String]): Unit = {
    println(program.run(empty).value)
  }

}

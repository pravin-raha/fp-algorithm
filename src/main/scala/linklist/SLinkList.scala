package linklist

import scala.annotation.tailrec

trait SLinkList[+T] {
  def head: T
  def tail: SLinkList[T]
  def map[R](func: T => R): SLinkList[R]
}

case class Cons[T](value: T, next: SLinkList[T]) extends SLinkList[T] {
  override def head: T = value

  override def tail: SLinkList[T] = next

  override def map[R](func: T => R): SLinkList[R] = {
    def loop(r: SLinkList[T]): SLinkList[R] = r match {
      case Cons(v, NilCons) => Cons(func(v), NilCons)
      case Cons(v, next)    => Cons(func(v), loop(next))
    }

    loop(this)
  }
}

case object NilCons extends SLinkList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException("SLinkList.head")

  override def tail: SLinkList[Nothing] = throw new NoSuchElementException("SLinkList.tail")

  override def map[R](func: Nothing => R): SLinkList[R] = throw new NoSuchElementException("SLinkList.map")
}

object Test extends App {
  private val value: Cons[Int] = Cons(1, Cons(2, NilCons))

  def length(element: SLinkList[Int]): Int = {
    @tailrec
    def loop(acc: Int, ele: SLinkList[Int]): Int = ele match {
      case NilCons       => acc
      case Cons(_, next) => loop(acc + 1, next)
    }

    loop(0, element)
  }

  println(value.map(a => a + 1))
}

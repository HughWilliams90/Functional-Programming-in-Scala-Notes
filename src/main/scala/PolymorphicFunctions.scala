import scala.annotation.tailrec

object PolymorphicFunctions extends App {
  def monomorphic(list: List[Int], get: Int): Option[Int] = {
    @tailrec
    def loop(list: List[Int]): Option[Int] = {
      if(list.head == get) list.headOption
      else if (list.isEmpty) None
      else loop(list.tail)
    }

    loop(list)
  }

  def polymorphic[A](list: List[A], get: A): Option[A] = {
    @tailrec
    def loop(list: List[A]): Option[A] = {
      if(list.head == get) list.headOption
      else if (list.isEmpty) None
      else loop(list.tail)
    }

    loop(list)
  }

  println(monomorphic(List("A", "B", "C", "D"), "D")) // this won't compile
  println(monomorphic(List(1, 2, 3, 4, 5, 6, 7), 6))

  println(polymorphic[String](List("A", "B", "C", "D"), "D"))
  println(polymorphic[Int](List(1, 2, 3, 4, 5, 6, 7), 6))
}

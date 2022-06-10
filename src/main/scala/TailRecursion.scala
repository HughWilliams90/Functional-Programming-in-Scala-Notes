
object TailRecursion extends App {
  import scala.annotation.tailrec

  def buildListWithElements(elements: Int): List[Int] = {
    @tailrec
    def tailRecLoop(counter: Int, buffer: List[Int] = List.empty[Int]): List[Int] = {
      if (counter == 0) buffer
      else tailRecLoop(counter - 1, buffer :+ counter)
    }
    tailRecLoop(elements)
  }

  def fibonacci(numberOfElements: Int) = {
    @tailrec
    def loop(prev: Int = 0, current: Int = 1, list: List[Int] = List.empty): List[Int] = {
      if (list.length == numberOfElements) list
      else loop(current, prev + current, list :+ prev)
    }

    loop()
  }

  println(fibonacci(7))
  println(buildListWithElements(5))
}

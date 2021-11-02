# 2. Tail Recursion

[Back to main](index.md)

A tail recursive function is simply a function whose last call is to itself

```scala
import scala.annotation.tailrec

def buildListWithElements(elements: Int): List[Int] = {
  @tailrec
  def tailRecLoop(counter: Int, buffer: List[Int] = List.empty[Int]): List[Int] = {
    if (counter == 0) buffer
    else tailRecLoop(counter - 1, buffer :+ counter)
  }
  tailRecLoop(elements)
}
```
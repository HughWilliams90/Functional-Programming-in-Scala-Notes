# 2. Tail Recursion

[Back to main](index.md)

A tail recursive function is simply a function whose last call is to itself.

when writing tail recursive methods first think of what return type you want in both my cases I want 
a list of Int, your inner loop and out function will have same return type as this.

then think of your exit case in buildListWithElements I only want to return when the counter hits zero as then I know I have the number of elements needed.
I do the same thing on fibonacci but by using the length method on list I can say once the list is the same length as the specified number of elements return.
```scala
object Something extends App {
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
```

buildListWithElements -> will build a list from the number of elements specified down to 1.
```scala
List(5, 4, 3, 2, 1)
```

fibonacci - > will build a list of fibonacci numbers in order up to a given number of elements
```scala
List(0, 1, 1, 2, 3, 5, 8)
```
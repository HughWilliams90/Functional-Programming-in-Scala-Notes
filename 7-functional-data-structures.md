# 7. Functional data structures and data sharing

[Back to main](index.md)

##Data Sharing
Data sharing is where you reuse immutable data structures to create
a new version of that structure. this immutability makes it easier to 
reason about the state of data structures. 

```scala
sealed trait MyList[+A] // sealed can only be access in this file
case object MyNil extends MyList[Nothing] // can be empty 
case class Innards[+A](head: A, tail: MyList[A])
object MyList {
  def apply[A](options: A*) = {
    if(options.empty) MyNil
    else Innards(options.head, options.tail)
  }
}

MyList("A")
```
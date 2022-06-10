# 6. Companion objects

[Back to main](index.md)

a companion object is a special kind of object that you can define 
along with your new data type, these companion objects can be used to
contain helper methods.

in the below example we have a trait called something it has a companion
object with two methods apply and unapply.

these two methods are special as apply is called automatically when
you instantiate your object and unapply is used for pattern matching
what I have created here using trait and companion object is the same as
a case class.

```scala
sealed trait Something {
  def name: String
  def age: Int
}

object Something {
  def apply(namey: String, agey: Int): Something = new Something {
    def name: String = namey
    def age: Int = agey
  }

  def unapply(something: Something): Option[(String, Int)] = {
    println(something.name)
    println(something.age)
    Some(something.name, something.age)
  }
}

case class SomethingElse(name: String, age: Int) extends Something

val x = Something("Hugh", 32)
val y = SomethingElse("Dmitri", 30)

def matcher(a: Something): Unit ={
  a match {
    case Something(a, b) => println("WHAT", a, b)
    case SomethingElse(a, b) => println("NOW", a, b)
    case _ => println("BANG")
  }
}
```

any entity that has a companion object will have access to the private members
defined in that companion object, no other class can access those members

```scala
class L {
  val capitalL = L.bigL
}

object L {
  private val bigL = "L"
}
```
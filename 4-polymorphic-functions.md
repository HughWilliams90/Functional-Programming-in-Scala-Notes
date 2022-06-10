# 4. Polymorphic functions and composition

[Back to main](index.md)

Polymorphic functions are functions that are generic and don't just work with one type.

A function that only work for a given type is called Monomorphic

example of a monomorphic function -> can only work for list of Ints
```scala
import scala.annotation.tailrec

def monomorphic(list: List[Int], get: Int): Option[Int] = {
  @tailrec
  def loop(list: List[Int]): Option[Int] = {
    if(list.head == get) list.headOption
    else if (list.isEmpty) None
    else loop(list.tail)
  }

  loop(list)
}

println(monomorphic(List("A", "B", "C", "D"), "D")) // this won't compile
println(monomorphic(List(1, 2, 3, 4, 5, 6, 7), 6))
```

below is exactly the same function as above but as there is no reason the above method
shouldn't work for any list regardless of inner type we can make it a polymorphic function can work with any list.
you just need to specify the inner type when calling it. here we use A as a placeholder
for the inner type.

```scala
import scala.annotation.tailrec  

def polymorphic[A](list: List[A], get: A): Option[A] = {
  @tailrec
  def loop(list: List[A]): Option[A] = {
    if(list.head == get) list.headOption
    else if (list.isEmpty) None
    else loop(list.tail)
  }

  loop(list)
}

println(polymorphic[String](List("A", "B", "C", "D"), "D"))
println(polymorphic[Int](List(1, 2, 3, 4, 5, 6, 7), 6))
```

##Polymorphic functions with types only

```scala
  def myCurry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b)
```

this may look daunting but this method simply takes a
function that takes something of type A with 
something of type B and Returns something of type C, then using 
just the types we've been given we can return a function that take just
something of type A and gives us a new function of type B to type C.

this means when the above is called it would look like this

```scala
def myCurry[A, B, C](f: (A, B) => C): A => (B => C) = 
  (a: A) => (b: B) => f(a, b)

def compareIntAndString(x: Int, y: String) = x.toString == y

val x = 1
val y = "1"

val curriedVersion: Int => (String => Boolean) = myCurry(compareIntAndString)
val partialApplication: String => Boolean = curriedVersion(x)
val result = partialApplication(y)
val sameAs = curriedVersion(x)(y)
```

In the above usage of myCurry the A type is `Int`, the B type is `String` and the C type is `Boolean`

<br>`def hof(x: Int, y: String)`<br> 
takes an `Int` and a `String` and returns a `Boolean`, 
when we call myCurry with hof as the argument 
<br>`myCurry(compareIntAndString)`<br> 
we get a function that takes an Int and returns
a function that takes a String and returns a boolean 
<br>`val curriedVersion: Int => (String => Boolean)`<br>
we can then call this function and partially apply the A or Int portion 
<br>`val partialApplication: String => Boolean`<br>
we can then call the function we get back with the B or String portion and get our result.
<br>`val result = partialApplication(y)`<br>
 you can also compose these so you call one after the other
as seen in
<br>`val sameAs = curriedVersion(x)(y)`

## Generic function composition
```scala
def myCompose[A, B, C](f: B => C, g: A => B): A => C =
  a => f(g(a))
```

the above take two functions, one that takes a type B to a type C
and another that takes a type A to a type B, we can "compose" these to gives us
one function that goes straight from a type A to a type C

```scala
val typeA = List(true, false, true)

def function1(a: List[Boolean]) = a.length
def function2(b: Int) = b.toString

val typeB = function1(typeA)
val result = function2(typeB)

def myCompose[A, B, C](g: A => B, f: B => C): A => C =
 a => f(g(a))

val composed: List[Boolean] => String = myCompose(function1, function2)
val result2 = composed(typeA)
```
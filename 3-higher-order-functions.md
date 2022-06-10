# 3. Higher Order Functions

[Back to main](index.md)

Higher order functions are functions that take a function as a parameter

Example of a higher order function

```scala
def higherOrderFunction(l: List[String], f: String => Boolean): List[Boolean] = {
  l.map(f)
}

def containsA(s: String): Boolean = s.contains("a")

val f = higherOrderFunction(List("asfdfd", "hjhjh"), containsA)
```

Here we have a higher order function that takes a list of Strings and a function 
that takes a String and returns Boolean, map is also a higher order function that takes the
function passed to higherOrderFunction and applies it to each element in the list.

containsA is a function that takes a String and returns a Boolean and therefore can be passed
to higherOrderFunction as the second parameter.

Note how when we call highOrderFunction we do not include the parentheses on containsA
if we did it would attempt to call containsA and give us the result, luckily for us as scala
is a compiled language this will not compile.

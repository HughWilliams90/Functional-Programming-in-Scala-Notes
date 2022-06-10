# 5. Pattern matching

[Back to main](index.md)

Patter matching allows you to match certain "cases" of a data type

```scala
val x = Option("A")

x match {
  case Some(a) if a.length > 1 => 
    println("this case matches there is a value and it is longer than 1") 
  case Some("A") =>
    println("this case matches there is a value and it is the string 'A'")
  case Some(_) =>
    println("this case matches there is a value")
  case None =>
    println("this case matches there is a not value")
} 
```

You can also use `case _ =>` at the bottom to match any case
should it not match any of the match case above.
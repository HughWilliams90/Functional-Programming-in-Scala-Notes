# 1. Pure Functions

[Back to main](index.md)

For a function to be considered pure it must be both Referentially transparent and consistent.

## Referential Transparency/Substitution Model

Referential transparency means you can replace a function call with its result, and it won't affect the behaviour of the program.

A referentially transparent example

```scala
def plus(x: Int, y: Int): Int = {
x + y
}

val x = plus(3, 5)
// as we can replace plus with its result (8) we know it is RT
```

A non referentially transparent example
```scala
def plusWithSideEffect(x: Int, y: Int): Int = {
println("side effect")
x + y
}


val y = plusWithSideEffect(1, 2)
// now if you replace plusWithSideEffect with its value the print line 
// in plusWithSideEffect will never be called changing its behaviour 
// therefore plus is not RT
```

## Consistency

Consistency means for any given input a function returns the same output

Example of a consistent function
```scala
def consistentFunction(x: Int, y: Int): Int = {
  x + y
}

val m = consistentFunction(1, 2) // we know for the input (1, 2) we always get 3
```

Example of an inconsistent function

```scala
def inconsistentFunction(x: Int, y: Int): Int = {
  x + y + Random.nextInt()
}

val n = inconsistentFunction(1, 2) // the same cannot be said about the inconsistent function as it adds a random number
```
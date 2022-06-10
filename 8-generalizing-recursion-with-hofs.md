# 8. Generalizing recursion with higher order functions

[Back to main](index.md)

When Mixing recursion with higher order functions, thing can get pretty confusing as
function might not necessarily return in the order they are called

foldLeft loops through the elements of a list left to right and then performs a given function on each element
foldRight loops through the elements of a list right to left and then performs a given function on each element

Here we will follow the path the code takes and see why foldRight and foldLeft are opposites

```scala
def foldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B = // Utility functions
  as match {
    case Nil => acc
    case Cons(head, tail) => f(head, foldRight(tail, acc)(f))
  }

foldRight(List(1, 2, 3), "")((ele, acc) => acc + ele.toString)
```

```
1.  we call `foldRight(Cons(1, Cons(2, Cons(3, Nil))), "")((acc, ele) => acc + ele.toString)`
2.  matches second case de-structures head to 1 and tail to Cons(2, Cons(3, Nil))
3.  calls function passed in (1st call) with 1 and calls foldRight(Cons(2, Cons(3, Nil)), "")((acc, ele) => acc + ele.toString)
4.  the function passed in (1st call) cannot return until inner foldRight is evaluated

5.  foldRight(Cons(2, Cons(3, Nil)), "")((acc, ele) => acc + ele.toString) `returns "32" + "1" = "321" on collapse`
6.  matches second case de-structures head to 2 and tail to Cons(3, Nil)
7.  calls function passed in (2nd call) with 2 and calls of foldRight(Cons(3, Nil)), "")((acc, ele) => acc + ele.toString)
8.  the function passed in (2nd call) cannot return until inner foldRight is evaluated

8.  foldRight(Cons(3, Nil)), "")((acc, ele) => acc + ele.toString) `returns "3" + "2" = "32" on collapse`
9.  matches second case de-structures head to 3 and tail to Nil
10. calls function passed in (3rd call) with 3 and calls foldRight((Nil), "")((acc, ele) => acc + ele.toString
11. the function passed in (3rd call)) cannot return until inner foldRight is evaluated

12. foldRight((Nil), "")((acc, ele) => acc + ele.toString) `returns "" + 3 = "3" on collapse`  
13. matches first case returns default -> "" this is where the function calls "collapse" to return the actual result

14. function passed in (3rd call) can now return "" + 3
15. function passed in (2nd call) can now return "3" + 2
16. function passed in (1st call) can now return "32" + 1
17. result can return "321"

CALLED foldRight with Cons(1,Cons(2,Cons(3,Nil))) and ""
CALLED foldRight with Cons(2,Cons(3,Nil)) and ""
CALLED foldRight with Cons(3,Nil) and ""
CALLED foldRight with Nil and ""
CALLED f with "" and 3
CALLED f with 3 and 2
CALLED f with 32 and 1
result 321
```




```scala
  @tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(head, tail) => foldLeft(tail, f(head, z))(f)
  }

foldLeft(List(1, 2, 3), "")((ele, acc) => acc + ele.toString)
```

Our call to foldRight will return a string of 321
Our call to foldLeft will return a string of 123
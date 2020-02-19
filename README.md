# Free Monad Pattern in Java

Whilst Java doesn't support higher-kinded types, it is still possible to develop an algebra and interpreter in a style similar to free monads.

Java does not support algebraic data types. Instead, the visitor pattern is used, providing type safety over an `instanceof` approach, at the cost of additional boilerplate.
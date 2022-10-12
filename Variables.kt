fun main(args : Array<String>) {

var name = "Dax" //string: var name: String = "Dax"
val age = 22 //int: val age: Int = 23

  println("My name is $name and I am $age years old")
  
name = "Kumiho" //var is mutable
age = "24" //error
 
// Expressions
val score: Int
score = 90 + 25
  
val a = 12
val b = 13
val max: Int

max = if (a > b) a else b
println("$max")
  
}

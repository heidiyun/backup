import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import kotlin.reflect.KProperty

//package ex1
//
//fun processTheAnswer(f: (Int) -> Int) {
//    println(f(42))
//}
//
//data class Person(val name: String, val age: Int)
//
//
//fun lookForAlice(people: List<Person>) {
//    people.forEach(fun(person) {
//        if (person.name == "Alice") return
//        println("${person.name} is not Alice")
//    })
//}
//
//fun look(people: List<Person>) {
//    people.filter(fun(person): Boolean {
//        return@filter person.age < 30
//        })
//
//    println("return")
//}
//
//fun main(args: Array<String>) {
//    val people = listOf(Person("Alice", 29), Person("Bob", 31))
//
//    look(people)
//
//
//}




//fun <T> List<T>.slice(indices: IntRange) {
//
//}
//inline fun <T> isA(value: Any) = value is T
//
//fun main(args: Array<String>) {
//    val value: List<Int> = listOf(1)
//    if (value is List<*>) {
//
//    }


//}

class User(val name: String, val age: Int) {
    // Memento Pattern : 객체의 상태를 저장하고 복원하는 패턴.
   inner class Memento(val name: String, val age: Int): Serializable

    fun snapshot() : Memento = Memento(name, age)
}

fun main(args: Array<String>) {
    val user = User("Tom", 42)

    val memento = user.snapshot()

    val fos = FileOutputStream("user.dat")
    val oos = ObjectOutputStream(fos)

    fos.use {
        oos.use {
            oos.writeObject(memento)
        }
    }
}
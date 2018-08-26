//프로퍼티 - Backing Field(메모리) + 저

//data class Point(val x: Int, val y: Int)

//operator fun Point.plus(other: Int): Point {
//    System.out.println("1")
//    return Point(x + other, y + other)
//}
//
//operator fun Point.plusAssign(other: Int) {
//    System.out.println("y")
//    this.x + other
//    this.y + other
//}


//operator fun <T> MutableCollection<T>.plusAssign(element: T){
//    System.out.println(1)
//    this.add(element)
//}
//
//operator fun <T> MutableCollection<T>.plus(element: T){
//    this.add(element)
//    System.out.println(2)
//}

//operator fun Point.unaryMinus(other: Int): Point {
//    return Point(-x, -y)
//}
//

//class Point(val x: Int, val y: Int) {
//    override fun equals(obj: Any?): Boolean {
//        System.out.println("equals method")
//        if (obj === this) return true
//        if (obj !is Point) return false
//        return obj.x == x && obj.y == y
//    }
//}

//fun main(args: Array<String>) {
//    var list = mutableListOf(1,2)
//
////    list += 3
//
//    val p = Point(10, 20)
//    val p1 = Point(10, 20)
//    val p2 = Point(20, 30)
//    val p3 = p
//
//    println(p == p1)
////    println(p == p2)
//    println(p != p2)
//    println(p === p3)
////}
//
//class Person(val firstName: String, val lastName: String) : Comparable<Person> {
//    override fun compareTo(other: Person): Int {
//        System.out.println("compare method")
//        return compareValuesBy(this, other,
//                Person::lastName, Person::firstName) // lastName이 우선순위
//    }
//}


//fun main(args: Array<String>) {
//    val p1 = Person("Alice", "Smith")
//    val p2 = Person("Bob", "Johnson")
//    println(p1 < p2)
//    println(p1 >= p2)
//}
//


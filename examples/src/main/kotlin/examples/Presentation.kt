package examples

import java.io.IOException
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/*
 * Null safety
 */

fun nullExample() {
    val first = "string"
    val second: String? = "something"

    printSomething(first)
    printSomethingNullable(second)
}

fun printSomething(myValue: String) {
    println(myValue)
}

fun printSomethingNullable(myValue: String?) {
    myValue?.let { println(it) }
}

fun elvisOperator() {
    val value = null

    println(value ?: "default value!")
    //             ^ elvis!
}


/*
 * No checked exceptions
 */

fun ioException() {
    throw IOException()
}

fun tryExpression() {
    val result = try {
        ioException()
        "okay"
    } catch (e: IOException) {
        "Boo! "
    }

    println(result)
}


/*
 * Immutable by default
 */

fun variables() {
    val immutable = 1
    var mutable = 2

    mutable++

    println(mutable)
    println(immutable + mutable)
}

fun collections() {
    val numbers = listOf(1, 2, 3)
    val moreNumbers = mutableListOf(4)

    moreNumbers += numbers

    val immutableCopy = moreNumbers.toList()

    moreNumbers += 6

    println(moreNumbers)
    println(immutableCopy)
}


/*
 * Smart casts
 */

fun castsIf() {
    val x: Any = "NaNaNaNa"

    if (x is String) {
        x.substring(2)
    }
}

fun castsWhen() {
    val x: Any = "NaNaNaNa"

    when (x) {
        is String -> x.substring(2)
        is Int -> x * 10
    }
}


/*
 * String templating
 */

fun simpleTemplating() {
    val name = "John"

    println("Hi, my name is $name")
}

fun complexTemplating() {
    val name = "John"
    val dob = LocalDate.now().minusYears(21)

    println("Hi, my name is $name and I'm ${ChronoUnit.YEARS.between(dob, LocalDate.now())}")
}

fun multilineString() {
    val firstName = "John"
    val lastName = "Jackson"

    val testData = """
        {
            "firstName":"$firstName",
            "lastName":"$lastName"
        }
    """.trimIndent()

    println(testData)
}


/*
 * Properties
 */

class Person(
        val firstName: String,
        val lastName: String,
        private val dob: LocalDate) {

    val fullName: String
        get() = "$firstName $lastName"

    val age: Int
        get() = ChronoUnit.YEARS.between(dob, LocalDate.now()).toInt()

    var counter: Int = 0
        private set

    fun increment() = counter++
}

fun properties() {
    val person = Person("Jill", "Jackson", LocalDate.of(1980, 2, 19))
    println(person.firstName)
    println(person.lastName)
    println(person.fullName)
    println(person.increment())
    println(person.counter)

    //Also works with Java classes
    val pojo = JavaPojo()
    pojo.someString = person.fullName

    println(pojo.someOtherString)
}


/*
 * Short hand functions
 */

fun fullExample(): List<Person> {
    return listOf(
            Person(
                    "Jack",
                    "Johnson",
                    LocalDate.now().minusYears(21)),
            Person(
                    "Jill",
                    "Johnson",
                    LocalDate.now().minusYears(16))
    )
}

fun shortExample() = listOf(
        Person(
                "Jack",
                "Johnson",
                LocalDate.now().minusYears(21)),
        Person(
                "Jill",
                "Johnson",
                LocalDate.now().minusYears(16))
)

fun filterExample() = shortExample().filter { it.age > 18 }


/*
 * Default and named arguments
 */

fun defaultExample(prefix: String = "(", value: String, postfix: String = ")") = "$prefix$value$postfix"

fun namedArgumentsExample() {
    val value = defaultExample(postfix = "!", prefix = "[", value = "Bang!")
    println(value)
}


/*
 * Data classes
 */

data class Point(val x: Int, val y: Int)

fun dataClass() {
    val p1 = Point(0, 0)
    val p2 = Point(0, 1)

    val copy = p1.copy(y = 0)
    println(p1 == p2)
    println(p1 == copy)

    val (x, y) = p1

    println("($x,$y)")
}


fun main(args: Array<String>) {
    nullExample()
    elvisOperator()
    tryExpression()
    variables()
    collections()
    castsIf()
    castsWhen()
    simpleTemplating()
    complexTemplating()
    multilineString()
    fullExample()
    filterExample()
    namedArgumentsExample()
    properties()
    dataClass()
}

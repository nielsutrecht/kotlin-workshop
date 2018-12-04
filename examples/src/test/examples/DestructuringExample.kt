import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DestructuringExample {
    class Person(val name: String, val age: Int) {
        operator fun component1() = name
        operator fun component2() = age
    }

    /*
     Destructuring in Kotlin is syntactic sugar, you are calling the available .componentN()
     operator functions
     */
    @Test
    fun basicExample() {
        val person = Person("John", 10)

        val (name, age) = person

        assertThat(name).isEqualTo("John")
        assertThat(age).isEqualTo(10)

        //Won't work, no component3() function
        //val (name, age, somethingElse) = person

        //If you're not interested in one component you can use _:

        val (_, otherAge) = person
    }

    /*
     Many classes have these operator functions built in
     */

    @Test
    fun collections() {
        val list = listOf(1, 2)

        val (a, b) = list

        assertThat(a).isEqualTo(1)
        assertThat(b).isEqualTo(2)

        //Will throw an ArrayIndexOutOfBoundsException:
        assertThrows<ArrayIndexOutOfBoundsException> {
            val (a, b, c) = list
        }
    }

    /*
     Pairs have the component functions built in
     */
    @Test
    fun pairs() {
        val pair = 123 to 456

        val (first, second) = pair

        assertThat(first).isEqualTo(pair.first)
        assertThat(second).isEqualTo(pair.second)
    }

    /*
     With data classes you get the component functions for free
     */
    @Test
    fun dataClass() {
        //Yes you can do this:
        data class TestClass(val a: Int, val b: Int)

        val test = TestClass(1, 2)
        val (a, b) = test

        assertThat(a).isEqualTo(test.a)
        assertThat(b).isEqualTo(test.b)
    }

    /*
     It's a convenient way to only use part of a result you're interested in
     */
    @Test
    fun functionResults() {
        fun someList() = listOf(1, 2, 3, 4)

        val (first) = someList()

        assertThat(first).isEqualTo(1)
    }
}

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SmartCastsExample {
    abstract class Animal(val name: String)

    class Cow(name: String) : Animal(name) {
        fun moo() {
            println("$name says MOO!")
        }
    }

    class Cat(name: String) : Animal(name) {
        fun purr() {
            println("$name says PRRRR!")
        }
    }

    @Test
    fun nullCast() {
        val a: Int? = 1

        //Won't work, a can be null
        //a.toDouble()

        if(a != null) {
            //A gets smart-casted from Int to Int?
            assertThat(a.toDouble()).isEqualTo(1.0)
        }
    }

    @Test
    fun classCast() {
        val animal: Animal = Cow("Betsy")

        //Won't work
        //animal.moo()

        if(animal is Cow) {
            animal.moo()
        }

        when (animal) {
            is Cat -> animal.purr()
            is Cow -> animal.moo()
        }
    }

    @Test
    fun whenCast() {
        val animal: Animal = Cat("Garfield")

        when (animal) {
            is Cat -> animal.purr()
            is Cow -> animal.moo()
        }
    }
}

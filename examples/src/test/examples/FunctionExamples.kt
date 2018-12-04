import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionExamples {
    private fun normalFunction(argument: String): String {
        return argument.repeat(5)
    }

    private fun shorthandFunction(argument: String) = argument.repeat(5)

    @Test
    fun functionExamples() {
        assertThat(normalFunction("a")).isEqualTo("aaaaa")
        assertThat(shorthandFunction("a")).isEqualTo("aaaaa")
    }

    /*
     You can have functions-in-functions
     */
    @Test
    fun functionInFunction() {
        fun double(i: Int) = i * 2

        assertThat(double(3)).isEqualTo(6)
        assertThat(double(6)).isEqualTo(12)
    }

    /*
     ... as deep as you want! (don't do this though, please)
     */
    @Test
    fun functionsInFunction() {
        fun a(): Int {
            fun b(): Int {
                fun c() = 42

                return c()
            }

            return b()
        }

        assertThat(a()).isEqualTo(42)
    }

    @Test
    fun lambdaExample() {
        val double = { i: Int -> i * 2 }

        assertThat(double(3)).isEqualTo(6)
        assertThat(double(6)).isEqualTo(12)
    }

    @Test
    fun lambdaArgumentExample() {
        fun integerMapper(i: Int, func: (Int) -> Int) = func(i)

        assertThat(integerMapper(3) { it * 2 }).isEqualTo(6)
        assertThat(integerMapper(10) { it / 2 }).isEqualTo(5)
    }

    @Test
    fun functionReferenceArgumentExample() {
        fun integerMapper(i: Int, func: (Int) -> Int) = func(i)

        fun square(i: Int) = i * i

        assertThat(integerMapper(3, ::square)).isEqualTo(9)
        assertThat(integerMapper(10, ::square)).isEqualTo(100)
    }

    @Test
    fun defaultArguments() {
        fun multiply(i: Int, factor: Int = 2) = i * factor

        assertThat(multiply(3)).isEqualTo(6)
        assertThat(multiply(6, 3)).isEqualTo(18)
    }

    @Test
    fun namedArguments() {
        fun multiply(i: Int = 1, factor: Int = 1) = i * factor

        assertThat(multiply()).isEqualTo(1)
        assertThat(multiply(i = 10)).isEqualTo(10)
        assertThat(multiply(factor = 10)).isEqualTo(10)
        assertThat(multiply(factor = 10, i = 2)).isEqualTo(20)
    }

    @Test
    fun extension() {
        fun List<Int>.double() = this.map { it * 2 }

        val list = listOf(1, 2, 3)
        val result = list.double()

        assertThat(result).containsExactly(2, 4, 6)
    }

    @Test
    fun infix() {
        infix fun Int.isIn(range: IntRange) = range.contains(this)

        val result = 1 isIn (0 .. 10)

        assertThat(result).isTrue()
    }
}
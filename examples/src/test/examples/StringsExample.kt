import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringsExample {
    @Test
    fun templating() {
        val someInteger = 42

        val string = "The answer is $someInteger"

        assertThat(string).isEqualTo("The answer is 42")
    }

    @Test
    fun complexTemplating() {
        val fibo = listOf(0, 1, 1, 2, 3, 5)

        val string = "First ${fibo.size} fibonacci numbers are ${fibo.take(fibo.size - 1).joinToString(", ")} and ${fibo.last()}"

        assertThat(string).isEqualTo("First 6 fibonacci numbers are 0, 1, 1, 2, 3 and 5")
    }

    @Test
    fun multiLineStrings() {
        val person = "John" to "Jackson"
        val json = """
            {
                "firstName":"${person.first}",
                "lastName":"${person.second}"
            }
        """.trimIndent()

        println(json)

        assertThat(json).contains("\"firstName\":\"John\"", "\"lastName\":\"Jackson\"")
    }
}
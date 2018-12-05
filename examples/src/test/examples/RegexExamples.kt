
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RegexExamples {
    /*
     Kotlin has Regex support built into the API with the Regex class
     */
    @Test
    fun regex() {
        val regex = Regex("foo.+")

        assertThat(regex.matches("foobar")).isTrue()
        assertThat(regex.matches("foobaz")).isTrue()
        assertThat(regex.matches("fobar")).isFalse()
    }

    /*
     There are also shortcuts on Strings to create a Regex from a string
     */
    @Test
    fun regexFromString() {
        val regex = "foo.+".toRegex()

        assertThat(regex.matches("foobar")).isTrue()
        assertThat(regex.matches("foobaz")).isTrue()
        assertThat(regex.matches("fobar")).isFalse()
    }

    /*
     With Kotlin you can easily apply a regex to a list of Strings to capture parts of these strings.
     */

    @Test
    fun regexCapturing() {
        val regex = "point: (\\d+),(\\d+)".toRegex()
        val input = sequenceOf("point: 123,456", "point: 456,789")

        val result = input.mapNotNull { regex.matchEntire(it)?.groupValues?.drop(1) }
                .map { it[0].toInt() to it[1].toInt() }
                .toList()

        assertThat(result).contains(Pair(123, 456), Pair(456,789))
    }

    /*
     Since the group values are in a list, you can easily map and then destructure them
     */
    @Test
    fun regexDestructure() {
        val regex = "point: (\\d+),(\\d+)".toRegex()
        val input = "point: 123,456"

        val (x, y) = regex.matchEntire(input)?.groupValues?.drop(1)?.map { it.toInt()} ?: throw IllegalArgumentException()

        assertThat(x).isEqualTo(123)
        assertThat(y).isEqualTo(456)
    }
}

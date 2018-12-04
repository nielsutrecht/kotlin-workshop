import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RegexExamples {
    @Test
    fun regex() {
        val regex = Regex("foo.+")

        assertThat(regex.matches("foobar")).isTrue()
        assertThat(regex.matches("foobaz")).isTrue()
        assertThat(regex.matches("fobar")).isFalse()
    }

    @Test
    fun regexFromString() {
        val regex = "foo.+".toRegex()

        assertThat(regex.matches("foobar")).isTrue()
        assertThat(regex.matches("foobaz")).isTrue()
        assertThat(regex.matches("fobar")).isFalse()
    }

    @Test
    fun regexCapturing() {
        val regex = "point: (\\d+),(\\d+)".toRegex()
        val input = sequenceOf("point: 123,456", "point: 456,789")

        val result = input.mapNotNull { regex.matchEntire(it)?.groupValues?.drop(1) }
                .map { it[0].toInt() to it[1].toInt() }
                .toList()

        assertThat(result).contains(Pair(123, 456), Pair(456,789))
    }
}

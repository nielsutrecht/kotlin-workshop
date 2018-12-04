
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.math.roundToInt

class IfWhenExamples {
    enum class Color {
        RED,
        BLUE,
        GREEN
    }

    enum class Coin {
        HEAD,
        TAILS
    }

    /*
     If is an expression in Kotlin. This means no more ternary operator (<expr> ? <if true> : <if false>)
     because it would be confusing together with the elvis operator (?:).
     */
    @Test
    fun ifExample() {
        val result = if(Random().nextBoolean()) Coin.HEAD else Coin.TAILS

        assertThat(result).isIn(Coin.HEAD, Coin.TAILS)
    }

    /*
     When is Kotlin's version of the switch. Important differences is that it doesn't do fall-through
     and is an expression (so ideal for mapping). It also is more powerful than Java's switch
     */
    @Test
    fun whenExpressionExample() {
        val input = Color.GREEN
        val hex = when(input) {
            Color.GREEN -> "00ff00"
            Color.RED -> "ff0000"
            Color.BLUE -> "0000ff"
        }

        assertThat(hex).isEqualTo("00ff00")
    }

    /*
     Instead of a 'default' label Kotlin has else. You need to make sure that if a when is used as an expression
     all paths are covered.
     */
    @Test
    fun whenExpressionElseExample() {
        val input = "00ff00"
        val hex = when(input) {
            "00ff00" -> Color.GREEN
            "ff0000" -> Color.RED
            "0000ff" -> Color.BLUE
            else -> throw IllegalArgumentException()
        }

        assertThat(hex).isEqualTo("00ff00")
    }

    /*
     When can also be used to test types and here it also does smart casting
     */
    @Test
    fun whenClassExample() {
        val n: Any = 1

        val type = when(n) {
            is Int -> n.toString()
            is Double -> n.roundToInt().toString()
            is String -> n
            else -> "unknown"
        }

        assertThat(type).isEqualTo("1")
    }

    /*
     When can also be used insted of long if-elseif-else blocks
     */
    @Test
    fun plainWhen() {
        val a = 1
        val b = 2
        val c = 3

        val result = when {
            a == b -> "a equals b"
            a == c -> "a equals c"
            b < a -> "b is less than a"
            b == a * 2 -> "b is a times 2"
            else -> "no idea"
        }

        assertThat(result).isEqualTo("b is a times 2")
    }
}
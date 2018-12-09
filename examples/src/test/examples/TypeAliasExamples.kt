import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//Create a typealias
typealias Point = Pair<Int, Int>

//Add x and y readonly properties
val Point.x get() = this.first
val Point.y get() = this.second

//Add an add function
fun Point.add(other: Point) = Point(x + other.x, y + other.y)

class TypeAliasExamples {
    @Test
    fun createPoint() {
        val point1 = Point(1, 2)

        assertThat(point1.first).isEqualTo(1)
        assertThat(point1.second).isEqualTo(2)

        assertThat(point1).isEqualTo(1 to 2)
    }

    @Test
    fun isExample() {
        val point1 = Point(1, 2)
        val point2: Point = 3 to 4
        val point3 = 5 to 6

        assertThat(point1 is Pair<Int, Int>).isTrue()
        assertThat(point2 is Point).isTrue()
        assertThat(point3 is Point).isTrue()
    }

    @Test
    fun extensionExample() {
        val point1 = Point(1, 2)

        assertThat(point1.x).isEqualTo(1)
        assertThat(point1.y).isEqualTo(2)

        val point2 = point1.add(10 to 20)

        assertThat(point2.x).isEqualTo(11)
        assertThat(point2.y).isEqualTo(22)

        //Since it's a typealias Pair<Int,Int> now also has an add method
        val pair = 1 to 2
        val point3 = pair.add(point1)

        val stringPair = "a" to "b"

        //Does not work; the TypeAlias is on Pair<Int, Int>
        //stringPair.x
    }
}
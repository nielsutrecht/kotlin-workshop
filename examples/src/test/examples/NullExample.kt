import org.junit.jupiter.api.Test

class NullExample {
    @Test
    fun nulls() {
        //Won't work
        //val aValue : Int = null

        //Does work
        val aValue : Int? = null

        //Default value for nulls via elvis operator:
        val secondValue = aValue ?: 0

        val map = mapOf(1 to 2)

        //Handling absent values with default value
        //The ?: is called the elvis operator!
        val doubled = map[1]?.let { it * 2 } ?: 0

        //Handling absent values by throwing an exception

        val tripled = map[1]?.let { it * 3 } ?: throw IllegalArgumentException("Not found")
    }
}

import org.junit.jupiter.api.Test

class CollectionsExamples {
    @Test
    fun mapCompute() {
        val map = mutableMapOf<String, Int>()

        map.compute("key") { _, value -> value?.plus(1) ?: 1}

        println(map)

        map.compute("key") { _, value -> value?.plus(1) ?: 1}

        println(map)
    }
}

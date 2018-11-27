package advent

import java.io.InputStream

object Resources {
    fun resourceString(year: Int, day: Int): String {
        return resource(year, day).bufferedReader().use { it.readText() }
    }

    fun resource(year: Int, day: Int): InputStream {
        val name = String.format("/%04d/day%02d.txt", year, day)

        return Resources::class.java.getResourceAsStream(name) ?: throw IllegalArgumentException("No resource with name $name")
    }
}

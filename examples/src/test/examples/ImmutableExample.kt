import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ImmutableExample {
    @Test
    fun varVal() {
        val a = 1

        //Won't work
        //a = 2

        var b = 1
        b = 2
    }

    @Test
    fun lists() {
        val list = listOf(1, 2)

        //Won't work
        //list += 3

        val mutableList = mutableListOf(1, 2)
        mutableList += 3

        assertThat(mutableList).contains(1, 2, 3)

        val asImmutable: List<Int> = mutableList

        //Won't work
        //asImmutable += 4

        val otherMutableList = list.toMutableList() //Makes a copy!
        otherMutableList += 3
        assertThat(list).containsExactly(1, 2)

        //You can add lists to each other if the Left Hand Side is mutable
        otherMutableList += list

        assertThat(otherMutableList).containsExactly(1, 2, 3, 1, 2)
    }

    @Test
    fun maps() {
        val map = mapOf(1 to 2, 3 to 4)

        //Won't work
        //map += 5 to 6
        //map.put(5, 6)

        val mutableMap = mutableMapOf(1 to 2, 3 to 4)

        mutableMap += 5 to 6
        mutableMap[7] = 8
        mutableMap.put(9, 0)

        assertThat(mutableMap).containsEntry(5, 6)
        assertThat(mutableMap).containsEntry(7, 8)
        assertThat(mutableMap).containsEntry(9, 0)
    }
}

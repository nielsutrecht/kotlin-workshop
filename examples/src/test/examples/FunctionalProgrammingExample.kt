import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FunctionalProgrammingExample {
    private val input = listOf("a1", "a2", "trash", "a3", "b4", "garbage", "b5", "c8", "d9")
    private val regex = "([a-z])([0-9])".toRegex()

    /*
     As an example of how you could apply functional programming. The input contains some garbage
     and we want filter out and we want to group the integers into a sum per 'a', 'b', 'c', 'd' String
     */
    @Test
    fun example() {
        val result = input
                .asSequence()                           //Lazy evaluation
                .mapNotNull(regex::matchEntire)         //Map to matches and drop null values
                .map { it.groupValues.drop(1) }      //Map to a list of the group values we're interested in
                .map { it[0] to it[1].toInt() }         //Map to String, Int pair
                .groupBy { it.first }                   //Group by the String
                .map {
                    it.key to it.value
                            .sumBy { it.second }            //Sum the values of the group
                }
                .toMap()                                //Collect the list of pairs to a String,Int map

        assertThat(result).containsEntry("a", 6)
        assertThat(result).containsEntry("b", 9)
        assertThat(result).containsEntry("c", 8)
        assertThat(result).containsEntry("d", 9)
    }

    /*
     Filter is probably the most basic FP component you'll use
     */
    @Test
    fun filter() {
        //Basic filter function
        val l1 = listOf(1, 2, 3)
        val r1 = l1.filter { it % 2 == 0 } //Even numbers only
        assertThat(r1).containsExactly(2)

        //Filter function that also supplies an index
        val l2 = listOf(1, 2, 3)
        val r2 = l2.filterIndexed { index, _ -> index % 2 == 0 } //Even indices only
        assertThat(r2).containsExactly(1, 3)

        //Convenience function that filters out null values
        val l3 = listOf(1, null, 3)
        val r3 = l3.filterNotNull()
        assertThat(r3).containsExactly(1, 3)

        //You can also use the reified generic filterIsInstance to only get elements of a certain type
        //Yes, Kotlin has reified generics!
        val l4 = listOf(1, "Foo", 3.0, "Bar")
        val r4 = l4.filterIsInstance<String>()

        assertThat(r4).containsExactly("Foo", "Bar")
    }

    /*
     There are a number of map functions with slightly different behaviors
     */
    @Test
    fun map() {
        //Basic map that takes input and gives output
        val l1 = listOf(1, 2, 3)
        val r1 = l1.map { it * 2 }
        assertThat(r1).containsExactly(2, 4, 6)

        //mapNotNull is a combination of map and filterNotNull
        val l2 = listOf(1, 2, null, 3)
        val r2 = l2.mapNotNull { it?.times(2) }
        assertThat(r2).containsExactly(2, 4, 6)

        //mapIndexed is the same as map, but also provides the index in the sequence
        val l3 = listOf(1, 2, 3)
        val r3 = l3.mapIndexed { index, i -> index + i }
        assertThat(r3).containsExactly(1, 3, 5)

        //mapTo takes a mutable collection as an argument, so it's basically like a map and foreach
        val l4 = listOf(1, 2, 3)
        val r4 = mutableListOf<String>()
        l4.mapTo(r4) { it.toString() }
        assertThat(r4).containsExactly("1", "2", "3")
    }

    /*
     Flatmaps lets you create new elements. It is a map where results in a collection or sequence get 'flattened'
     */

    @Test
    fun flatMap() {
        val range = (0..4)

        val grid = range.flatMap { y -> range.map { x -> x to y } }

        assertThat(grid).hasSize(25)
        assertThat(grid).contains(0 to 0, 4 to 0, 0 to 4, 4 to 4)
    }

    @Test
    fun firstLast() {
        val list = listOf(1, 2, 3, 4)

        assertThat(list.first()).isEqualTo(1)
        assertThat(list.last()).isEqualTo(4)

        //Using first or last on an empty collection throws an exception
        assertThrows<NoSuchElementException> { listOf<Int>().last() }

        assertThat(listOf<Int>().lastOrNull()).isNull()
    }

    @Test
    fun forEach() {
        val builder = StringBuilder()

        val list = listOf(1, 2, 3)
        list.forEach { builder.append(it) }

        assertThat(builder.toString()).isEqualTo("123")

        //Often there's simpler functions available:

        val result = list.joinToString()
        assertThat(result).isEqualTo("123")
    }

    @Test
    fun drop() {
        val list = listOf(1, 2, 3)

        assertThat(list.drop(1)).containsExactly(2, 3)
        assertThat(list.dropLast(1)).containsExactly(1, 2)
        assertThat(list.dropWhile { it <= 2 }).containsExactly(3)
    }

    @Test
    fun foldReduce() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        //Keep in mind that Kotlin has a lot of convenience methods, such as List<Number>.sum():
        assertThat(list.sum()).isEqualTo(21)

        //Fold takes an initial value and an accumulator + element lambda
        //Sum example
        val sum = list.fold(0) { acc, i -> acc + i }
        assertThat(sum).isEqualTo(21)

        //String join example
        val join = list.fold("") { acc, i -> acc + i }
        assertThat(join).isEqualTo("123456")

        //Sum elements at even indices example
        val sumEven = list.foldIndexed(0) { index, acc, i ->
            if (index % 2 == 0) {
                acc + i
            } else acc
        }

        assertThat(sumEven).isEqualTo(9)

        //Reduce is like fold, but does not take an initial value. Instead you start with the first element in the collection

        val sumReduce = list.reduce { acc, i -> acc + i }
        assertThat(sumReduce).isEqualTo(21)

        //There's also indexed versions of the reduce functions
        val sumEvenReduce = list.reduceIndexed { index, acc, i ->
            acc + if (index % 2 == 0) {
                i
            } else 0
        }

        assertThat(sumEvenReduce).isEqualTo(9)
    }

    /*
     Slice lets you pick individual elements into a new list based on indices
     */
    @Test
    fun slice() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        val result = list.slice(listOf(0, 3, 5))

        assertThat(result).containsExactly(1, 4, 6)
    }

    /*
     GroupBy lets you group elements of a collection into a map of collections based on a grouping function
     */
    @Test
    fun groupBy() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        val result = list.groupBy {
            when {
                it % 2 == 0 -> "even"
                else -> "odd"
            }
        }

        assertThat(result["even"]).containsExactly(2, 4, 6)
        assertThat(result["odd"]).containsExactly(1, 3, 5)
    }

    /*
     Combines two iterables pair-wise, stops if one of them runs out
     */
    @Test
    fun zip() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5)

        val result = a.zip(b)

        assertThat(result).containsExactly(1 to 4, 2 to 5)
    }

    /*
     Splits a list into a list of lists with chunks of a certain size
     */
    @Test
    fun chunked() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        val result = list.chunked(2)

        assertThat(result).hasSize(3)
        assertThat(result[0]).containsExactly(1, 2)
        assertThat(result[1]).containsExactly(3, 4)
        assertThat(result[2]).containsExactly(5, 6)
    }

    /*
     Splits a list into a list of lists with windows of a certain size
     that slide with a certain step size.
     */
    @Test
    fun windowed() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        val result = list.windowed(2, 1)

        assertThat(result).hasSize(5)

        assertThat(result.first()).containsExactly(1, 2)
        assertThat(result.last()).containsExactly(5, 6)
    }

    /*
     Separates an iterable into two 'sides' of a pair based on a predicate.
     */
    @Test
    fun partition() {
        val list = listOf(1, 2, 3, 4, 5, 6)

        val result = list.partition { it % 2 == 0 }

        assertThat(result.first).containsExactly(2, 4, 6)
        assertThat(result.second).containsExactly(1, 3, 5)
    }

    /*
     Builds the union (OR) between two iterables.
     */
    @Test
    fun union() {
        val a = listOf(1, 2, 3, 4)
        val b = listOf(3, 4, 5, 6)

        val result = a.union(b)

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6)
    }

    /*
     Builds the intersection (AND) between two iterables
     */
    @Test
    fun intersection() {
        val a = listOf(1, 2, 3, 4)
        val b = listOf(3, 4, 5, 6)

        val result = a.intersect(b)

        assertThat(result).containsExactly(3, 4)
    }

    /*
     Builds a key-value map from an iterable.
     */
    @Test
    fun associateBy() {
        val list = listOf(1, 2, 3, 4)

        //Only the key
        val result = list.associateBy { it - 1 }

        assertThat(result).containsEntry(0, 1)
        assertThat(result).containsEntry(1, 2)
        assertThat(result).containsEntry(2, 3)
        assertThat(result).containsEntry(3, 4)

        //Both key and value mapped
        val result2 = list.associateBy(
                {v -> v - 1},
                {v -> v * 2}
        )

        assertThat(result2).containsEntry(0, 2)
        assertThat(result2).containsEntry(1, 4)
        assertThat(result2).containsEntry(2, 6)
        assertThat(result2).containsEntry(3, 8)
    }
}
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WithApplyLetAlsoRunExample {
    @Test
    fun with() {
        val s = with("Na") { repeat(3) }

        assertThat(s).isEqualTo("NaNaNa")
    }

    //Apply and Also are  operations that return the original object
    // - apply has a 'this' context
    // - also has an 'it' context

    @Test
    fun apply() {
        val s = "42".apply { println(this) }

        assertThat(s).isEqualTo("42")
    }

    @Test
    fun also() {
        val s = "42".also { println(it) }

        assertThat(s).isEqualTo("42")
    }

    //Run and Let are map operations that map from T to R
    // - run has a 'this' context
    // - let has an 'it' context

    @Test
    fun run() {
        val s = "Na".run { repeat(3) }

        assertThat(s).isEqualTo("NaNaNa")
    }

    @Test
    fun let() {
        val s = "Na".let { it.repeat(3) }

        assertThat(s).isEqualTo("NaNaNa")
    }

    @Test
    fun letAndAlso() {
        val list = mutableListOf<Int>()

        val i = 3

        list += i.let { it * 2 }.also { list += it }

        assertThat(list).containsExactly(6, 6)
    }

    @Test
    fun runAndApply() {
        val list = mutableListOf<Int>()

        val i = 3

        list += i.run { this * 2 }.apply { list += this }

        assertThat(list).containsExactly(6, 6)
    }
}

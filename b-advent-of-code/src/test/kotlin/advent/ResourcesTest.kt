package advent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class ResourcesTest {
    @Test
    fun resourceString() {
        assertThat(Resources.resourceString(2017, 1)).matches("[0-9]+")
    }

    @Test
    fun resource() {
        assertThat(Resources.resource(2017, 1)).isNotNull()

        assertThrows<IllegalArgumentException> {
            Resources.resource(2016, 1)
        }
    }
}


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PropertiesExample {

    @Test
    fun properties() {
        val person = Person("Jill", "Johnson", LocalDate.now().minusYears(42))

        assertThat(person.firstName).isEqualTo("Jill")
        assertThat(person.lastName).isEqualTo("Johnson")
        assertThat(person.age).isEqualTo(42)

        person.dob = LocalDate.now().minusYears(36)

        assertThat(person.age).isEqualTo(36)
    }

    internal class Person(val firstName: String, val lastName: String, var dob: LocalDate) {
        val age : Long
            get() = ChronoUnit.YEARS.between(dob, LocalDate.now())
    }
}


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataClassExample {
    data class Pet(val name: String, val age: Int)
    class Person(val name: String, val age: Int)

    /*
     You create a data class the same way as a normal class
     */
    @Test
    fun create() {
        val pet = Pet("Max", 5)
        val person = Person("Jack", 42)
    }

    /*
     Data classes get things handled for you, such as automatic .toString implementations (which you can override)
     */
    @Test
    fun toStringExample() {
        val pet = Pet("Max", 5)
        val person = Person("Jack", 42)

        //Default Any toString
        assertThat(person.toString()).matches("DataClassExample\\\$Person@[0-9a-f]+")
        //Nice toString
        assertThat(pet.toString()).isEqualTo("Pet(name=Max, age=5)")
    }

    /*
     And .equals and hashCode
     */
    @Test
    fun equalsHashCode() {
        val pet1 = Pet("Max", 5)
        val pet2 = Pet("Max", 5)
        val person1 = Person("Jack", 42)
        val person2 = Person("Jack", 42)

        assertThat(pet1).isEqualTo(pet2)
        assertThat(person1).isNotEqualTo(person2)
        assertThat(pet1.hashCode()).isEqualTo(pet2.hashCode())
        assertThat(person1.hashCode()).isNotEqualTo(person2.hashCode())
    }

    /*
     Data classes also get a convenient .copy method that can be used to create copies
     */
    @Test
    fun copy() {
        val pet1 = Pet("Max", 5)

        val pet2 = pet1.copy("Garfield")

        assertThat(pet2.name).isEqualTo("Garfield")
        assertThat(pet2.age).isEqualTo(pet1.age)
    }

    /*
     Data classes also get automatic implementation for .componentN() functions that allow
     destructuring
     */
    @Test
    fun destructure() {
        val pet1 = Pet("Max", 5)

        val (name, age) = pet1

        assertThat(name).isEqualTo("Max")
        assertThat(age).isEqualTo(5)
    }

}
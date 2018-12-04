import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException

class ExceptionExample {
    @Test
    fun noCheckedExceptions() {
        val result = assertThrows<IOException>{ throwException()  }

        assertThat(result.message).isEqualTo("Boo!")
    }

    private fun throwException() {
        //No need to declare 'throws'
        throw IOException("Boo!")
    }
}
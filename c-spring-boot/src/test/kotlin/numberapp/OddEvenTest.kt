package numberapp

import io.restassured.RestAssured
import numberapp.controller.OddEven
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class OddEvenTest {
    @LocalServerPort
    lateinit var port: String

    @TestFactory
    fun isOddEven() = (0 .. 100)
        .sorted()
        .map { DynamicTest.dynamicTest("Is odd or even: $it") { isOddEven(it) } }

    private fun isOddEven(value: Int) {
        val expected = if(value % 2 == 0) OddEven.EVEN else OddEven.ODD
        RestAssured.given().port(port.toInt())
            .`when`().get("/oddeven/$value")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("result", equalTo(expected.toString()))
    }

    @Test
    fun findOddEven() {
        val post = mapOf("numbers" to listOf(1, 2, 3, 4))

        RestAssured.given().port(port.toInt())
                .contentType("application/json")
                .body(post)
                .`when`().post("/oddeven/find")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("result.1", equalTo("ODD"))
                .body("result.2", equalTo("EVEN"))
                .body("result.3", equalTo("ODD"))
                .body("result.4", equalTo("EVEN"))
    }
}

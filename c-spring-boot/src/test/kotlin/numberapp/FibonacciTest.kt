package numberapp

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class FibonacciTest {
    private val sequence = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811)

    @LocalServerPort
    lateinit var port: String

    @TestFactory
    fun nthFibonacci() = sequence
        .mapIndexed { i, num -> DynamicTest.dynamicTest("${i + 1}th number") { nthFibonacci(i + 1, num) } }

    private fun nthFibonacci(n: Int, value: Int) {
        RestAssured.given().port(port.toInt())
            .`when`().get("/fibonacci/$n")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("result", equalTo(value))
    }
}

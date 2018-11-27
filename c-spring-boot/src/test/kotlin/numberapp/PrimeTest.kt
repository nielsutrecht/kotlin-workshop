package numberapp

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
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
internal class PrimeTest {
    private val knownPrimes = setOf(2, 3, 5, 7, 11, 13, 17, 19, 21)
    private val knownNotPrimes = knownPrimes.map { it * 2 }

    @LocalServerPort
    lateinit var port: String

    @TestFactory
    fun testPrimes() = (knownPrimes + knownNotPrimes)
        .sorted()
        .map { DynamicTest.dynamicTest("Is prime: $it: ${knownPrimes.contains(it)}") { isPrime(it) } }

    private fun isPrime(value: Int) {
        RestAssured.given().port(port.toInt())
            .`when`().get("/prime/$value")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("result", equalTo(knownPrimes.contains(value)))
    }

    @Test
    fun findPrimes() {
        val post = mapOf("numbers" to (0 .. 22).toList())

        RestAssured.given().port(port.toInt())
            .contentType("application/json")
            .body(post)
            .`when`().post("/prime/find")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("numbers", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 21))
    }

}

package numberapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.toMono

@RestController
@RequestMapping("/fibonacci")
class FibonacciController {
    @GetMapping("/{nth}")
    fun nthFibonacci(@PathVariable nth: Int) =
            LongResponse(fibonacci(nth)).toMono()

    private fun fibonacci(n: Int): Long =
            when (n) {
                1 -> 0L
                2 -> 1L
                else -> fibonacci(n - 1) + fibonacci(n - 2)
            }
}



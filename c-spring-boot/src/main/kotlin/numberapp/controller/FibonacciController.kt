package numberapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/fibonacci")
class FibonacciController {
    @GetMapping("/{nth}")
    fun nthFibonacci(@PathVariable nth: Int) : Mono<LongResponse> = TODO("Please implement")
}



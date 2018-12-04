package numberapp.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/prime")
class PrimeController {
    @GetMapping("/{number}")
    fun isPrime(@PathVariable number: Long) : Mono<BooleanResponse> {
        TODO("Please implement")
    }

    @PostMapping("/find")
    fun findPrimes(@RequestBody request: NumberList) : Mono<NumberList> {
        TODO("Please implement")
    }
}



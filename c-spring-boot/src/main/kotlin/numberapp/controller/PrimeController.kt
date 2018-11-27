package numberapp.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController
@RequestMapping("/prime")
class PrimeController {
    @GetMapping("/{number}")
    fun isPrime(@PathVariable number: Long) : Mono<BooleanResponse> {
        TODO("Please implement")
    }

    @PostMapping("/find")
    fun findPrimes(@RequestBody request: NumberList) : Mono<NumberList> {
        return NumberList(listOf(2, 3, 5, 7, 11, 13, 17, 19, 21)).toMono()
    }
}



package numberapp.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController
@RequestMapping("/oddeven")
class OddEvenController {
    @GetMapping("/{number}")
    fun isOddOrEven(@PathVariable number: Long) : Mono<OddEvenResponse> {
        return OddEvenResponse(toOddEven(number)).toMono()
    }

    @PostMapping("/find")
    fun findOddEven(@RequestBody request: NumberList) : Mono<OddEvenMap> {
        val result = request.numbers.map { it to toOddEven(it) }
                .toMap()

        return OddEvenMap(result).toMono()
    }

    private fun toOddEven(n: Long) = if(n % 2 == 0L) OddEven.EVEN else OddEven.ODD
}



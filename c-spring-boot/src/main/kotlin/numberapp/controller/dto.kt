package numberapp.controller

data class BooleanResponse(val result: Boolean)
data class OddEvenResponse(val result: OddEven)
data class LongResponse(val result: Long)
data class NumberList(val numbers: List<Long>)
data class OddEvenMap(val result: Map<Long, OddEven>)

enum class OddEven {
    ODD,
    EVEN
}

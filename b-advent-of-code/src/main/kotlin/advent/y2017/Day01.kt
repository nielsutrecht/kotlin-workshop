package advent.y2017

import advent.Resources.resourceString

fun main(args: Array<String>) {
    val digits = resourceString(2017, 1).toCharArray().map { c -> c - '0' }

    val part1 = sum(digits) { i -> (i + 1) % digits.size }.toString()
    val part2 = sum(digits) { i -> (i + digits.size / 2) % digits.size }.toString()

    println("Day 01 part 1: $part1")
    println("Day 01 part 2: $part2")
}

fun sum(digits: List<Int>, index: (Int) -> Int) = digits.indices
        .filter { digits[it] == digits[index.invoke(it)] }
        .sumBy { digits[it] }


package org.example

import java.io.File

fun main() {
    val input = File("datasets/Day012").readText()

    println(resolvePart1(input)
//    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    TODO()
}

fun resolvePart1(input: String): Int {
    val normalizedInput = input.split("\n\n")
    val regions = normalizedInput.last().split("\n").map { it.split(Regex("\\D+")).map(String::toInt) }
    val result = regions.count { g -> g.take(2).reduce { a, b -> a * b } >= g.drop(2).sumOf { it * 9 } }
    return result
}

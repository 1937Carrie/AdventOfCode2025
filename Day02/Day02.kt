import java.io.File

fun main() {
    val input = File("Day02.txt").readText()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: String): Long {
    return input
        .splitToSequence(",")
        .map { stringRange ->
            val splittedString = stringRange.split("-")
            splittedString[0].toLong().rangeTo(splittedString[1].toLong())
        }
        .flatMap { range ->
            range.asSequence()
                .filter { number ->
                    val stringNumber = number.toString()
                    val n = stringNumber.length

                    for (patternLength in n / 2 downTo 1) {
                        if (n % patternLength == 0) {
                            val repeatCount = n / patternLength
                            if (repeatCount >= 2) {
                                val pattern = stringNumber.substring(0, patternLength)
                                if (pattern.repeat(repeatCount) == stringNumber) {
                                    return@filter true
                                }
                            }
                        }
                    }
                    false
                }
        }
        .sum()
}

fun resolvePart1(input: String): Long {
    return input
        .splitToSequence(",")
        .map { stringRange ->
            val splittedString = stringRange.split("-")
            splittedString[0].toLong().rangeTo(splittedString[1].toLong())
        }
        .flatMap { range ->
            range.asSequence()
                .filter { number ->
                    val numStr = number.toString()
                    numStr.length % 2 == 0
                }
                .filter { number ->
                    val numStr = number.toString()
                    val halfLength = numStr.length / 2
                    val firstHalf = numStr.substring(0, halfLength)
                    val secondHalf = numStr.substring(halfLength)
                    firstHalf == secondHalf
                }
        }
        .sum()
}

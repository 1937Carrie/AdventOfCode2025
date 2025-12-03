import java.io.File

fun main() {
    val input = File("datasets/Day03.txt").readLines()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    return input.sumOf { line ->
        val result = StringBuilder()
        var startIndex = 0
        val digitsNeeded = 12

        (0..<digitsNeeded).forEach { position ->
            val remainingDigits = digitsNeeded - position - 1
            val searchEnd = line.length - remainingDigits

            var maxDigit = '0'
            var maxIndex = startIndex

            (startIndex..<searchEnd).forEach { i ->
                if (line[i] > maxDigit) {
                    maxDigit = line[i]
                    maxIndex = i

                    if (maxDigit == '9') return@forEach
                }
            }

            result.append(maxDigit)
            startIndex = maxIndex + 1
        }

        result.toString().toLong()
    }
}

fun resolvePart1(input: List<String>): Long {
    return input.sumOf { line ->
        var result = 0L
        for (index in 0..<line.length) {
            line.substring(index + 1).forEach { substringCh ->
                val comparisonValue = "${line[index]}$substringCh".toLong()
                if (result < comparisonValue) result = comparisonValue
            }
        }
        result
    }
}

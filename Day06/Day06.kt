import java.io.File

fun main() {
    val input = File("datasets/Day06.txt").readLines()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    fun cephalopodMatrix(matrix: Array<Array<Char>>): Array<Array<Char>> {
        val numRows = matrix.size
        val numCols = matrix[0].size

        val cephalopodedMatrix = Array(numCols) { i ->
            Array(numRows) { j ->
                matrix[j][numCols - i - 1]
            }
        }
        return cephalopodedMatrix
    }

    val alignedInput = input.toMutableList().apply { this[lastIndex] = this[lastIndex] + " ".repeat(this.first().length - this.last().length) }
    val cephalopodInput = cephalopodMatrix(alignedInput.map { it.toCharArray().toTypedArray() }.toTypedArray())

    var result = 0L
    val tokens = mutableListOf<String>()
    for (x in cephalopodInput.indices) {
        var token = ""
        for (y in 0..<cephalopodInput.first().size - 1) {
            token += cephalopodInput[x][y]
        }
        if (token.isBlank()) continue
        tokens.add(token)
        val operatorSign = cephalopodInput[x][cephalopodInput.first().lastIndex]
        if (operatorSign.isDigit().not() && operatorSign.isWhitespace().not()) {
            var innerResult = if(operatorSign == '+') 0L else 1L
            tokens.forEach {
                when(operatorSign) {
                    '+' -> innerResult += it.trim().toLong()
                    else -> innerResult *= it.trim().toLong()
                }
            }
            result += innerResult
            tokens.clear()
        }
    }

    return result
}

fun resolvePart1(input: List<String>): Long {
    val cleanedData = input.map {
        it.split(Regex("\\s+")).filter { it.isNotBlank() }
    }
    var result = 0L
    for (y in cleanedData.first().indices) {
        val tokens = mutableListOf<String>()
        for (x in cleanedData.indices) {
            tokens.add(cleanedData[x][y])
        }

        val operator = tokens.removeLast()
        var innerResult = if (operator == "+") 0L else 1L

        tokens.forEach {
            when (operator) {
                "+" -> innerResult += it.toLong()
                else -> innerResult *= it.toLong()
            }
        }

        result += innerResult
    }

    return result
}

import java.io.File

fun main() {
    val input = File("Day04.txt").readLines()

    println(solvePart2(input, false))
    println(solvePart2(input))
}

fun solvePart2(input: List<String>, muchAsCan: Boolean = true): Int {
    val copyInput = input.map(CharSequence::toMutableList)
    val directions = listOf(
        Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
        Pair(0, -1), /*@*/ Pair(0, 1),
        Pair(1, -1), Pair(1, 0), Pair(1, 1)
    )
    var result = 0
    var newResult = 0

    while (true) {
        val rollsToChange = mutableListOf<Pair<Int, Int>>()

        for ((rowIndex, str) in copyInput.withIndex()) {
            for ((columnIndex, ch) in str.withIndex()) {
                if (ch != '@') continue
                var count = 0

                for ((x, y) in directions) {
                    val checkedRow = rowIndex + x
                    val checkedColumn = columnIndex + y

                    if (
                        checkedRow in 0 until copyInput.size
                        && checkedColumn in 0 until str.size
                        && copyInput[checkedRow][checkedColumn] == '@'
                    ) {
                        count++
                    }
                }

                if (count < 4) {
                    newResult++
                    rollsToChange.add(rowIndex to columnIndex)
                }

            }
        }

        if (newResult == result) {
            return result
        } else {
            if (muchAsCan.not()) return newResult
            result = newResult
        }

        rollsToChange.forEach { (x, y) ->
            copyInput[x][y] = 'x'
        }
    }
}

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("datasets/Day09").readLines()

    println(resolvePart1(input))
//    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    TODO()
}

fun resolvePart1(input: List<String>): Long {
    val tilesCoordinates = input.map {
        val split = it.split(",")
        split[0].toInt() to split[1].toInt()
    }

    var result = 0L
    for (i in tilesCoordinates.indices) {
        for (j in i + 1..tilesCoordinates.lastIndex) {
            val currentSquare = (abs(tilesCoordinates[i].first - tilesCoordinates[j].first).toLong() + 1) *
                    (abs(tilesCoordinates[i].second - tilesCoordinates[j].second).toLong() + 1)
            result = if (currentSquare > result) currentSquare else result
        }
    }
    return result
}
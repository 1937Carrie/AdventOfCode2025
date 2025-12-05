import java.io.File

fun main() {
    val input = File("Day05.txt").readLines()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Int {
    val (tmpFreshIngredientIDs, tmpAvailableIngredientIDs) =
        input.filter(String::isNotBlank).partition { it.contains("-") }
    val freshIngredientIDs = tmpFreshIngredientIDs.map {
        val range = it.split("-")
        range[0].toLong().rangeTo(range[1].toLong())
    }
    val availableIngredientIDs = tmpAvailableIngredientIDs.map { it.toLong() }
    TODO()
    return availableIngredientIDs
        .asSequence()
        .flatMap { availableIngredientID ->
            freshIngredientIDs.filter { it.contains(availableIngredientID) }
        }
        .flatMap { it.asSequence() }
        .distinct()
        .sum()
}

fun resolvePart1(input: List<String>): Int {
    val (tmpFreshIngredientIDs, availableIngredientIDs) =
        input.filter(String::isNotBlank).partition { it.contains("-") }
    val freshIngredientIDs = tmpFreshIngredientIDs.map {
        val range = it.split("-")
        range[0].toLong().rangeTo(range[1].toLong())
    }
    return availableIngredientIDs.map { it.toLong() }
        .count { availableIngredientID -> freshIngredientIDs.any { it.contains(availableIngredientID) } }
}

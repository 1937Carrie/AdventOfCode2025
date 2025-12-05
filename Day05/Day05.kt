import java.io.File

fun main() {
    val input = File("datasets/Day05").readLines()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    val (tmpFreshIngredientIDs, _) =
        input.filter(String::isNotBlank).partition { it.contains("-") }
    val freshIngredientIDs = tmpFreshIngredientIDs.map {
        val range = it.split("-")
        range[0].toLong().rangeTo(range[1].toLong())
    }

    val sortedFresh = freshIngredientIDs.sortedBy { it.first }

    fun two2one(a: Pair<Long, Long>?, b: Pair<Long, Long>): Pair<Long, Long>? {
        return when {
            a == null -> b
            b.first > a.second -> null
            else -> Pair(minOf(a.first, b.first), maxOf(a.second, b.second))
        }
    }

    val merged = sortedFresh.fold(mutableListOf<Pair<Long, Long>>()) { acc, e ->
        if (acc.isEmpty()) {
            acc.add(e.first to e.last)
        } else {
            val last = acc.last()
            val mixed = two2one(last, e.first to e.last)
            if (mixed != null) {
                acc[acc.size - 1] = mixed
            } else {
                acc.add(e.first to e.last)
            }
        }
        acc
    }

    return merged.sumOf { (start, end) -> end - start + 1 }
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

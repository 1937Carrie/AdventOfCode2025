import java.io.File

fun main() {
    val rawInput = File("datasets/Input").readLines()
    val input = rawInput
        .map { it.split(":").map { it.trim() } }
        .associate {
            it[0] to it[1].split(" ")
        }

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: Map<String, List<String>>): Long {
    fun countPathsThroughBoth(
        graph: Map<String, List<String>>,
        start: String,
        target: String,
        mustVisit1: String,
        mustVisit2: String
    ): Long {
        val visited = mutableSetOf<String>()
        val memo = mutableMapOf<Pair<String, Int>, Long>()

        fun dfs(node: String, state: Int): Long {
            if (node == target) {
                return if (state == 3) 1L else 0L
            }

            val key = node to state
            if (memo.containsKey(key)) return memo[key]!!

            if (!visited.add(node)) return 0L

            var count = 0L
            for (next in graph[node].orEmpty()) {
                var nextState = state
                if (next == mustVisit1) nextState = nextState or 1
                if (next == mustVisit2) nextState = nextState or 2
                count += dfs(next, nextState)
            }

            visited.remove(node)
            memo[key] = count
            return count
        }

        val startState = 0

        return dfs(start, startState)
    }

    return countPathsThroughBoth(input, "svr", "out", "dac", "fft")
}

fun resolvePart1(input: Map<String, List<String>>): Long {
    fun countPaths(graph: Map<String, List<String>>, start: String, target: String): Long {
        val memo = mutableMapOf<String, Long>()

        fun dfs(node: String): Long {
            if (node == target) return 1
            if (memo.containsKey(node)) return memo[node]!!
            if (!graph.containsKey(node)) return 0

            var count = 0L
            for (neighbor in graph[node]!!) {
                count += dfs(neighbor)
            }
            memo[node] = count
            return count
        }

        return dfs(start)
    }

    return countPaths(input, "you", "out")
}

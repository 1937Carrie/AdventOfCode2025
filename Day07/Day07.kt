import java.io.File

fun main() {
    val input = File("Day07.txt").readLines()

    println(resolvePart1(input))
    println(resolvePart2(input))
}

fun resolvePart2(input: List<String>): Long {
    val cols = input.first().length

    val startIndex = input.first().indexOf('S')

    var beams = LongArray(cols) { 0L }
    beams[startIndex] = 1L

    for (row in 1..<input.lastIndex) {
        val currentTimelineBeams = LongArray(cols) { 0L }
        for (col in 0..<cols) {
            if (beams[col] > 0) {
                currentTimelineBeams[col] = currentTimelineBeams[col] + beams[col]
            }
        }

        var changed: Boolean
        do {
            changed = false
            for (col in 0..<cols) {
                if (input[row][col] == '^' && currentTimelineBeams[col] > 0) {
                    val left = col - 1
                    val right = col + 1
                    val timelinesHere = currentTimelineBeams[col]

                    currentTimelineBeams[left] = currentTimelineBeams[left] + timelinesHere
                    currentTimelineBeams[right] = currentTimelineBeams[right] + timelinesHere

                    currentTimelineBeams[col] = 0L
                    changed = true
                }
            }
        } while (changed)

        beams = currentTimelineBeams
    }

    return beams.sum()
}

fun resolvePart1(input: List<String>): Int {
    val startIndex = input.first().indexOf('S')
    val beams = mutableSetOf(startIndex)
    var result = 0
    for (row in 1..input.lastIndex) {
        val currentLine = input[row]
        if (currentLine.contains("^").not()) continue

        var currentSplitterIndex = 0

        for (unusedCounter in 0..<currentLine.count { it == '^' }) {
            currentSplitterIndex = currentLine.indexOf(char = '^', startIndex = currentSplitterIndex)
            if (beams.contains(currentSplitterIndex).not()) {
                currentSplitterIndex++
                continue
            }

            beams.remove(currentSplitterIndex)
            beams.add(currentSplitterIndex - 1)
            beams.add(currentSplitterIndex + 1)
            result++
            currentSplitterIndex++
        }
    }
    return result
}

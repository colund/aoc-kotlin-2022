import kotlin.streams.toList

fun main() {
    val lines = readInput("Day06")

    println("Day 6 part 1: ${day6(lines, 4)}")
    println("Day 6 part 2: ${day6(lines, 14)}")
}

private fun day6(lines: List<String>, uniqueCount: Int): Int {
    var pos = 0

    val line = lines[0]

    val chars = line.chars().toList()
    val charLists: List<List<Int>> = chars.windowed(uniqueCount)

    for (charList in charLists) {
        val size = charList.toSet().size
        if (size == uniqueCount) {
            break
        }
        pos++
    }
    return pos + uniqueCount
}

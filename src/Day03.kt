fun main() {
    val testInput = readInput("Day03")

    val day3Part1 = day3Part1(testInput)
    println("Day3 part 1: $day3Part1")
    val day3Part2 = day3Part2(testInput)
    println("Day3 part 2: $day3Part2")
}

private fun day3Part1(
    testInput: List<String>
): Int {
    val priorities = priorityChars()
    return testInput
        .asSequence()
        .map { line -> line.chunked(line.length / 2) }
        .mapNotNull {
            val first = it[0]
            val second = it[1]
            first.find { char -> second.contains(char) }
        }.sumOf { priorities.indexOf(it) + 1 }
}

private fun day3Part2(testInput: List<String>): Int {
    val priorities = priorityChars()
    return testInput
        .asSequence()
        .chunked(3)
        .mapNotNull { threeLines: List<String> ->
            threeLines[0].find {
                threeLines[1].contains(it) && threeLines[2].contains(it)
            }
        }.sumOf { priorities.indexOf(it) + 1 }
}

private fun priorityChars(): List<Char> {
    val priorities = mutableListOf<Char>()
    priorities.addAll(charRange('a', 'z'))
    priorities.addAll(charRange('A', 'Z'))
    return priorities
}

private fun charRange(firstChar: Char, lastChar: Char): MutableList<Char> {
    val chars = mutableListOf<Char>()
    var char = firstChar
    while (char <= lastChar) {
        chars.add(char)
        char++
    }
    return chars
}
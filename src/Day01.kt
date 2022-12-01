fun main() {
    fun day1sums(input: List<String>): List<Int> {
        val sums = mutableListOf<Int>()
        var current = 0
        input.forEach { string ->
            if (string.isBlank()) {
                sums += current
                current = 0
            } else {
                current += string.toInt()
            }
        }
        return sums
    }

    fun part1(sums: List<Int>): Int {
        return sums.max()
    }

    fun part2(sums: List<Int>): Int {
        return sums.sortedDescending().take(3).sum()
    }

    val testInput = readInput("Day01")
    val sums = day1sums(testInput)

    println(part1(sums))

    println((part2(sums)))
}

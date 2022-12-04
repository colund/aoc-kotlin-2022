fun fullyContained(min1: Int, max1: Int, min2: Int, max2: Int): Boolean {
    return (min1 <= min2 && max1 >= max2) || (min2 <= min1 && max2 >= max1)
}

fun partiallyOverlap(min1: Int, max1: Int, min2: Int, max2: Int): Boolean {
    return (max1 in min2..max2) || (max2 in min1..max1)
}

fun main() {
    println("Day4 part 1: " + day4 { fullyContained(it[0].toInt(), it[1].toInt(), it[2].toInt(), it[3].toInt()) })

    println("Day4 part 2: " + day4 { partiallyOverlap(it[0].toInt(), it[1].toInt(), it[2].toInt(), it[3].toInt()) })
}

fun day4(fn: (List<String>) -> Boolean): Int {
    val input = readInput("Day04")

    val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()
    val count = input.map { line ->
        regex.findAll(line).map { it.groupValues.drop(1) }
    }.flatMap { it.map(fn) }.count { it } // Count the number of invocations that returned true
    return count
}
fun main() {
    println("Day5 part 1: " + day5(::part1))
    println("Day5 part 2: " + day5(::part2))
}

private fun day5(
    doInstruction: (
        count: Int,
        stacks: MutableList<MyStack>,
        fromIndex: Int,
        toIndex: Int
    ) -> Unit
): String {
    val stacks = mutableListOf<MyStack>()

    var parsingStack = true

    val moveRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    val input = readInput("Day05")
    input.forEach {
        if (it.startsWith(" 1")) {
            parsingStack = false
        } else if (parsingStack) {
            it.chunked(4).forEachIndexed { index, string ->
                val stack = stacks.elementAtOrNull(index) ?: MyStack().also { stacks.add(it) }
                val char = string.elementAt(1)
                if (char != ' ') {
                    stack.insertLast(char)
                }
            }
        } else {
            val groupValues: List<String> = moveRegex.matchEntire(it)?.groupValues ?: return@forEach

            val count = groupValues[1].toInt()
            val fromIndex = groupValues[2].toInt() - 1
            val toIndex = groupValues[3].toInt() - 1

            doInstruction(count, stacks, fromIndex, toIndex)
        }
    }
    return stacks.map { it.pop() }.joinToString("")
}

private fun part1(
    count: Int,
    stacks: MutableList<MyStack>,
    fromIndex: Int,
    toIndex: Int
) {
    repeat(count) {
        val popped = stacks[fromIndex].pop()
        stacks[toIndex].push(popped)
    }


}

private fun part2(
    count: Int,
    stacks: MutableList<MyStack>,
    fromIndex: Int,
    toIndex: Int
) {
    val popped = stacks[fromIndex].popCount(count)
    stacks[toIndex].pushGroup(popped)
}
fun main() {
    val lines = readInput("Day07")
    day7(lines)
}

fun day7(lines: List<String>) {
    val cdCommand = "\\$ cd (.*)".toRegex()
    val sizeFile = "(\\d+) (.*)".toRegex()

    val pathSizes = mutableMapOf<String, Int>()
    var cwd = ""

    for (line in lines) {
        if (line == "\$ ls") continue

        cdCommand.captures(line)?.let { list ->
            val cdDir = list.first()
            if (cdDir == "..") { // Go up
                cwd = cwd.substringBeforeLast("/")
                return@let
            }
            cwd = cwd + (if (cwd.endsWith("/") || cdDir == "/") "" else "/") + cdDir
            pathSizes[cwd] = 0
        } ?: sizeFile.captures(line)?.let {
            val size: Int = it[0].toInt()
            var currDir = cwd
            while (true) {
                pathSizes[currDir] = pathSizes.getOrDefault(currDir, 0) + size
                if (currDir == "/") break
                currDir = if (currDir.lastIndexOf("/") > 0) currDir.substringBeforeLast("/") else "/"
            }
        }
    }

    println("Day7 part 1: " + pathSizes.filter { it.value < 100_000 }.values.sum())

    val freeSpace = 70000000 - pathSizes.getOrDefault("/", 0)
    val missing = 30000000 - freeSpace
    println("Day7 part 2: " + pathSizes.filter { it.value > missing }.values.minOf { it })
}

private fun Regex.captures(line: String): List<String>? {
    return find(line)?.groupValues?.drop(1)
}

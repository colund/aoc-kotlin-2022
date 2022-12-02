enum class RPS(val value: Int) { Rock(1), Paper(2), Scissors(3) }

fun main() {

    data class RockPaperScissors(val rock: Char, val paper: Char, val scissors: Char) {
        fun toRPS(char: Char) = when (char) {
            rock -> RPS.Rock
            paper -> RPS.Paper
            scissors -> RPS.Scissors
            else -> null
        }
    }

    fun roundScore(me: RPS, they: RPS): Int {
        var score = 0
        if (me == they) {
            score += 3 // draw
        } else if ((me == RPS.Rock && they == RPS.Scissors) ||
            (me == RPS.Paper && they == RPS.Rock) ||
            (me == RPS.Scissors && they == RPS.Paper)
        ) {
            score += 6 // win
        }
        return score + me.value
    }


    val me = RockPaperScissors('X', 'Y', 'Z')
    val they = RockPaperScissors('A', 'B', 'C')

    val testInput = readInput("Day02")

    /////////////////////////////////
    // Part 2
    /////////////////////////////////

    var score = 0
    testInput.forEach {
        val myChar = it[2]
        val mine = me.toRPS(myChar) ?: return@forEach
        val theirChar = they.toRPS(it[0]) ?: return@forEach
        val roundScore = roundScore(mine, theirChar)
        score += roundScore
    }

    println("Part 1 $score")

    /////////////////////////////////
    // Part 2
    /////////////////////////////////


    val second = testInput.mapNotNull {
        val theirChoice = they.toRPS(it[0])
        val myChoice = when (it[2]) {
            'X' -> { // Need to lose
                when (theirChoice) {
                    RPS.Rock -> RPS.Scissors
                    RPS.Paper -> RPS.Rock
                    RPS.Scissors -> RPS.Paper
                    null -> null
                }
            }

            'Y' -> { // Need to draw
                theirChoice
            }

            'Z' -> { // Need to win
                when (theirChoice) {
                    RPS.Rock -> RPS.Paper
                    RPS.Paper -> RPS.Scissors
                    RPS.Scissors -> RPS.Rock
                    null -> null
                }
            }

            else -> null
        }
        if (myChoice != null && theirChoice != null)
            roundScore(myChoice, theirChoice)
        else null
    }.sum()

    println("Round two $second")
}

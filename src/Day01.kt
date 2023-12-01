fun main() {
    fun part1(input: List<String>): Int {
        var totalSum = 0

        input.forEach { line ->
            val digits = Regex("\\d").findAll(line).toList()
            if (digits.isNotEmpty()) {
                val firstDigit = digits.first().value
                val lastDigit = digits.last().value
                val number = "$firstDigit$lastDigit".toInt()
                totalSum += number
            }
        }


        // return input.size
        return totalSum
    }



    val digitsMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun String.filterDigits(withWords: Boolean): String {
        return if (!withWords) {
            this.filter { it.isDigit() }
        } else {
            val line = this
            buildString {
                for (i in line.indices) {
                    if (line[i].isDigit()) {
                        append(line[i])
                    } else {
                        val sub = line.substring(startIndex = i)
                        for ((word, num) in digitsMap) {
                            if (sub.startsWith(word)) {
                                append(num)
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    fun List<String>.sumFilteredDigits(withWords: Boolean) = this.sumOf { line ->
        val digits = line.filterDigits(withWords)
        "${digits.first()}${digits.last()}".toInt()
    }

    fun part2(input: List<String>) = input.sumFilteredDigits(withWords = true)


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 55029)

    val input = readInput("Day01_test2")
    //  part1(input).println()
    part2(input).println()
}
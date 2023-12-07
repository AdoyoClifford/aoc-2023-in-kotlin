package day04

import println
import readInput

fun part1(input: List<String>): Int {
    var score = 0
    for (card in input) {
        val parts = card.substringAfter(":").trim()
        val (part0, part1) = parts.split("|").map { it.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }
        var cardScore = 0
        for (num in part1) {
            if (num in part0) {
                cardScore = if (cardScore == 0) 1 else cardScore * 2
            }
        }
        score += cardScore

    }

    return score
}
fun part2(input: List<String>): Int {

    fun countMatches(cardLine: String): Int {
        val (_, cards) = cardLine.split(": ")
        val (win, mine) = cards.split("|").map { group ->
            group.trim().split("\\s+".toRegex()).map { num ->
                num.toInt()
            }
        }
        val winSet = win.toSet()
        return mine.count { it in winSet }
    }


    val cardCopies = IntArray(input.size) { 1 }
    for ((index, line) in input.withIndex()) {
        val matches = countMatches(line)
        if (matches > 0) {
            for (i in 1..matches) {
                if (index + i >= cardCopies.size) break
                cardCopies[index + i] += cardCopies[index]
            }
        }
    }
    return cardCopies.sum()
}



//fun main() {
//    //val testInput = readInput("Day04_test")
//    //check(part1(testInput) == 21558)
//
//    val input = readInput("Day04_test")
//    //val input = listOf("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
//    part2(input).println()
//}
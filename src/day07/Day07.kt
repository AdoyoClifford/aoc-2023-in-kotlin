package day07

import java.io.File

data class GameLogic(
    val one: Char,
    val two: Char,
    val three: Char,
    val four: Char,
    val five: Char,
) {
    fun matchType(hands: Int): Boolean {
        val frequencyMap = mutableMapOf<Char, Int>()
        listOf(one, two, three, four, five).forEach {
            frequencyMap[it] = frequencyMap.getOrDefault(it, 0) + 1
        }
        return frequencyMap.any { it.value == hands }
    }

}

fun main() {

    //val handMap = mapOf()

    val input = File("src/day07/Day07.txt").readLines()
    val test = input.map { it.substringBefore(' ').trim() }.map {
        it.split(",").map {
             GameLogic(it[0], it[1],it[2], it[3], it[4])
        }
    }




    println(test)


}
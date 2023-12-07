package day02

import println
import readInput
import kotlin.math.max


data class Set(val red: Int, val green: Int, val blue: Int)
data class Game(val id: Int, val sets: List<Set>)


fun parse(input: List<String>) = input.map {
    val (game, sets) = it.split(":")
    val id = "Game (\\d+)".toRegex().find(game)!!.groupValues[1].toInt()
    sets.split(";").map {
        val red = "(\\d+) red".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        val green = "(\\d+) green".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        val blue = "(\\d+) blue".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        Set(red, green, blue)
    }.let {
        Game(id, it)
    }
}
fun part1(input: List<String>): Int {
    val redCubes = 12
    val blueCubes = 14
    val greenCubes = 13
    return parse(input).filter {
        it.sets.none{it.red > redCubes|| it.green > greenCubes || it.blue > blueCubes }
    }.sumOf { it.id }
}

fun part2(input: List<String>): Int {
    return parse(input).map {
        var redCubes = 0
        var blueCubes = 0
        var greenCubes = 0
        it.sets.forEach{
            redCubes = max(redCubes, it.red)
            blueCubes = max(blueCubes, it.blue)
            greenCubes = max(greenCubes, it.green)
        }
        redCubes * greenCubes * blueCubes
    }.sumOf { it }
}


fun main() {

    val input = readInput("Day02_test")
    //val input = listOf("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
    part2(input).println()

}
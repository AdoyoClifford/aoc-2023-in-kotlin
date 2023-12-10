package day08

import println
import java.io.File

fun main() {
    val input = File("src/day08/Day08.txt").readLines().toString()
    val instructions = """[LR]+""".toRegex().matchAt(input, 1)!!.value

    val tableMap = """(\w+) = \((\w+), (\w+)\)""".toRegex().findAll(input).associateBy(
        keySelector = {it.groupValues[1]},
        valueTransform = {it.groupValues[2] to it.groupValues[3]}
    )

    fun start(initial: String)  = instructions.fold(initial) {acc, char ->
        when(char) {
            'L' -> tableMap[acc]!!.first
            'R' -> tableMap[acc]!!.second
            else -> error("Unknown instruction $char")
        }
    }

    fun part1(): Int = instructions.length * generateSequence("AAA",::start).indexOf("ZZZ")
    fun part2(): Long = instructions.length *
            tableMap.keys.filter { it.endsWith('A') }.map { start ->
                generateSequence(start, ::start).indexOfFirst { it.endsWith('Z') }
            }.fold(1L) { x, y -> lcm(x, y.toLong()) }





   part2().println()
}

fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}
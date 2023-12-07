import java.io.File

class Day5(input: String) {
    private val seeds: List<Long>
    private val mappingsList: List<List<Mapping>>

    init {
        val sections = input.split("\n\n")
        seeds = sections[0].split(' ').mapNotNull { it.toLongOrNull() }

        mappingsList = sections.drop(1).map { section ->
            section.lines().mapNotNull { line ->
                val (destination, source, size) = line.split(' ').takeIf { it.size == 3 } ?: return@mapNotNull null
                Mapping(
                    destination.toLongOrNull() ?: return@mapNotNull null,
                    source.toLongOrNull() ?: return@mapNotNull null,
                    size.toLongOrNull() ?: return@mapNotNull null,
                )
            }.sortedBy { it.source }
        }
    }

    private fun solve(ranges: List<LongRange>): Long = ranges.flatMap {
        mappingsList.fold(listOf(it)) { acc, mappings ->
            buildList {
                for (range in acc) {
                    val last = mappings.filter { mapping -> range in mapping }.fold(range.first) { first, mapping ->
                        if (first < mapping.source) add(first until mapping.source)
                        val start = maxOf(first, mapping.source)
                        val end = minOf(range.last + 1, mapping.source + mapping.length)
                        val offset = mapping.dest - mapping.source
                        add(start + offset until end + offset)
                        end
                    }
                    if (last <= range.last) add(last..range.last)
                }
            }
        }
    }.minOf { it.first }

    fun part1(): Long = solve(seeds.map { it..it })

    fun part2(): Long = solve(seeds.chunked(2) { (start, length) -> start until start + length })

    private data class Mapping(val dest: Long, val source: Long, val length: Long) {
        operator fun contains(range: LongRange): Boolean =
            !range.isEmpty() && source <= range.last && range.first < source + length
    }

}

fun main() {
    val example = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent()



    val test2 = File("src/day05/Day05.txt").readLines().toString()

    val testInput = Day5(example).part2().println()

}





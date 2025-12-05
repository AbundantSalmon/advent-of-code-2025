package day05

import utils.FileUtils

fun main() {
    val filename = "input05.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

fun part1(lines: List<String>) {
    var numberOfFresh = 0
    var upToIngredients = false
    val ranges = mutableListOf<Pair<Long, Long>>()
    for (line in lines) {
        if (line == "") {
            upToIngredients = true
            continue
        }

        if (!upToIngredients) {
            val (start, end) = line.split("-")
            ranges.add(Pair(start.toLong(), end.toLong()))
        }
        if (upToIngredients) {
            val ingredient = line.toLong()
            for (range in ranges) {
                if ((range.first <= ingredient) && ingredient <= range.second) {
                    numberOfFresh++
                    break
                }
            }
        }
    }
    println(numberOfFresh)
}

fun part2(lines: List<String>) {
    var upToIngredients = false
    val ranges = mutableListOf<Pair<Long, Long>>()
    for (line in lines) {
        if (line == "") {
            upToIngredients = true
            continue
        }

        if (!upToIngredients) {
            val (start, end) = line.split("-")
            ranges.add(Pair(start.toLong(), end.toLong()))
        }
        if (upToIngredients) {
            break
        }
    }
    val sortedRange = ranges.sortedBy { it.first }.toMutableList()

    var i = 0
    while (true) {
        if (i + 1 > sortedRange.size - 1) {
            break
        }
        val range = sortedRange[i]
        val nextRange = sortedRange[i + 1]

        if (range.second >= nextRange.first && range.second <= nextRange.second) {
            sortedRange[i] = Pair(range.first, nextRange.second)
            sortedRange.removeAt(i + 1)
        } else if (range.second >= nextRange.first && range.second >= nextRange.second) {
            sortedRange.removeAt(i + 1)
        } else {
            i++
        }
    }

    var numberOfFresh = 0L
    for (range in sortedRange) {
        numberOfFresh += range.second - range.first + 1
    }
    println(numberOfFresh)
}

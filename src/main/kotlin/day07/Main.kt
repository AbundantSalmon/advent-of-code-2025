package day07

import utils.FileUtils

fun main() {
    val filename = "input07.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines.toMutableList())

    println("Part2:")
    part2(lines)
}

fun part1(lines: MutableList<String>) {
    var numberOfSplits = 0
    for (i in 1..<lines.size) {
        val previousLine = lines[i - 1]
        val currentLine = lines[i]

        val mutableCurrentLine = currentLine.toCharArray().toMutableList()

        for ((index, value) in mutableCurrentLine.withIndex()) {
            if (previousLine[index] == 'S') {
                mutableCurrentLine[index] = '|'
            } else if (previousLine[index] == '|' && mutableCurrentLine[index] == '^') {
                numberOfSplits++
                mutableCurrentLine[index - 1] = '|'
                mutableCurrentLine[index + 1] = '|'
            } else if (previousLine[index] == '|') {
                mutableCurrentLine[index] = '|'
            }
        }

        lines[i] = mutableCurrentLine.joinToString("")
    }

    println(numberOfSplits)
}

fun part2(lines: List<String>) {
    val splitterLocations: Set<Pair<Int, Int>> = mutableSetOf()
    var startLocation: Pair<Int, Int>? = null
    val maxY = lines.size - 1
    for ((y, line) in lines.withIndex()) {
        for ((x, value) in line.toCharArray().withIndex()) {
            if (value == '^') {
                splitterLocations.plus(Pair(x, y))
            }
            if (value == 'S') {
                startLocation = Pair(x, y)
            }
        }
    }

    checkNotNull(startLocation)
}

package day04

import utils.FileUtils

fun main() {
    val filename = "input04.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

enum class Space {
    FREE,
    ROLL,
}

fun part1(lines: List<String>) {
    var rollThatCanBeAccessed = 0

    val height = lines.size
    val width = lines[0].length

    val map = mutableMapOf<Int, MutableMap<Int, Space>>()
    for ((y, row) in lines.withIndex()) {
        for ((x, space) in row.withIndex()) {
            val spaceEnum =
                if (space == '.') {
                    Space.FREE
                } else {
                    Space.ROLL
                }

            val place = map.getOrPut(x) { mutableMapOf() }
            place[y] = spaceEnum
        }
    }

    for (x in 0..<width) {
        for (y in 0..<height) {
            if (map[x]?.get(y) == Space.FREE) {
                continue
            }
            val left = map.getOrDefault(x - 1, null)?.getOrDefault(y, Space.FREE)
            val topLeft = map.getOrDefault(x - 1, null)?.getOrDefault(y - 1, Space.FREE)
            val right = map.getOrDefault(x + 1, null)?.getOrDefault(y, Space.FREE)
            val topRight = map.getOrDefault(x + 1, null)?.getOrDefault(y - 1, Space.FREE)
            val leftBottom = map.getOrDefault(x - 1, null)?.getOrDefault(y + 1, Space.FREE)
            val rightBottom = map.getOrDefault(x + 1, null)?.getOrDefault(y + 1, Space.FREE)
            val top = map.getOrDefault(x, null)?.getOrDefault(y - 1, Space.FREE)
            val bottom = map.getOrDefault(x, null)?.getOrDefault(y + 1, Space.FREE)
            val surroundingSpaces = listOf(left, topLeft, right, topRight, leftBottom, rightBottom, top, bottom)
            if (surroundingSpaces.count { it == Space.ROLL } < 4) {
                rollThatCanBeAccessed++
            }
        }
    }

    println(rollThatCanBeAccessed)
}

fun part2(lines: List<String>) {
    var rollThatCanBeAccessed = 0

    val height = lines.size
    val width = lines[0].length

    val map = mutableMapOf<Int, MutableMap<Int, Space>>()
    for ((y, row) in lines.withIndex()) {
        for ((x, space) in row.withIndex()) {
            val spaceEnum =
                if (space == '.') {
                    Space.FREE
                } else {
                    Space.ROLL
                }

            val place = map.getOrPut(x) { mutableMapOf() }
            place[y] = spaceEnum
        }
    }

    var rollsRemovedThisRound = 0
    do {
        rollsRemovedThisRound = 0
        for (x in 0..<width) {
            for (y in 0..<height) {
                if (map[x]?.get(y) == Space.FREE) {
                    continue
                }
                val left = map.getOrDefault(x - 1, null)?.getOrDefault(y, Space.FREE)
                val topLeft = map.getOrDefault(x - 1, null)?.getOrDefault(y - 1, Space.FREE)
                val right = map.getOrDefault(x + 1, null)?.getOrDefault(y, Space.FREE)
                val topRight = map.getOrDefault(x + 1, null)?.getOrDefault(y - 1, Space.FREE)
                val leftBottom = map.getOrDefault(x - 1, null)?.getOrDefault(y + 1, Space.FREE)
                val rightBottom = map.getOrDefault(x + 1, null)?.getOrDefault(y + 1, Space.FREE)
                val top = map.getOrDefault(x, null)?.getOrDefault(y - 1, Space.FREE)
                val bottom = map.getOrDefault(x, null)?.getOrDefault(y + 1, Space.FREE)
                val surroundingSpaces = listOf(left, topLeft, right, topRight, leftBottom, rightBottom, top, bottom)
                if (surroundingSpaces.count { it == Space.ROLL } < 4) {
                    rollsRemovedThisRound++
                    map[x]?.put(y, Space.FREE)
                }
            }
        }
        rollThatCanBeAccessed += rollsRemovedThisRound
    } while (rollsRemovedThisRound != 0)

    println(rollThatCanBeAccessed)
}

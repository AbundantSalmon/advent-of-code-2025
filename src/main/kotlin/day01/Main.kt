package day01

import utils.FileUtils

fun main() {
    val filename = "input01.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

fun part1(lines: List<String>) {
    var numberOfZeros = 0
    var lastValue = 50
    for (line in lines) {
        val direction = line[0]
        val number = line.substring(1).toInt()
        val result =
            if (direction == 'R') {
                val result = (lastValue + number) % 100
                result
            } else {
                var result = (lastValue - number) % 100
                if (result < 0) {
                    result += 100
                }
                result
            }
        if (result == 0) {
            numberOfZeros++
        }
        lastValue = result
    }
    println(numberOfZeros)
}

fun part2(lines: List<String>) {
    var numberOfZeros = 0
    var lastValue = 50
    for (line in lines) {
        val direction = line[0]
        val number = line.substring(1).toInt()
        val result =
            if (direction == 'R') {
                val result = (lastValue + number) % 100
                val numberOfRotations = (lastValue + number) / 100
                numberOfZeros += numberOfRotations
                result
            } else {
                var result = (lastValue - number) % 100
                val numberOfRotations = (lastValue - number) / 100
                numberOfZeros -= numberOfRotations

                if (result < 0) {
                    if (lastValue != 0) {
                        // Accounting for the case
                        // 0 -  99,  -99, 0
                        // 0 - 100, -100, 1
                        // 1 - 100,  -99, 1
                        // 1 - 101, -100, 2
                        // 1 - 10,    -9, 1
                        numberOfZeros++
                    }
                    result += 100
                }
                if (result == 0) {
                    numberOfZeros++
                }
                result
            }
        lastValue = result
    }
    println(numberOfZeros)
}

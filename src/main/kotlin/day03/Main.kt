package day03

import utils.FileUtils

fun main() {
    val filename = "input03.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

fun part1(lines: List<String>) {
    var sumOfJoltage = 0

    for (line in lines) {
        val numbers = line.map { it.toString().toInt() }

        var head = 0
        var tail = 0

        var largestJoltage = 0
        while (tail != numbers.size - 1) {
            tail += 1
            if ((numbers[tail] > numbers[head]) && tail != numbers.size - 1) {
                head = tail
                continue
            }

            val joltage = (numbers[head].toString() + numbers[tail].toString()).toInt()
            if (joltage > largestJoltage) {
                largestJoltage = joltage
            }
        }
        sumOfJoltage += largestJoltage
    }
    println(sumOfJoltage)
}

fun part2(lines: List<String>) {
    var sumOfJoltage = 0L

    for (line in lines) {
        val numbers = line.map { it.toString().toInt() }

        var startIndex = numbers.size - 1 - 12
        // search backwards for largest number
        for (value in startIndex downTo 0) {
            if (numbers[value] >= numbers[startIndex]) {
                startIndex = value
            }
        }

        val sequence = numbers.subList(startIndex, startIndex + 12).toMutableList()
        var minIndex = indexOfMinInTheRight(sequence)
        for (value in numbers.subList(startIndex + 12, numbers.size)) {
            if (value >= sequence[minIndex]) {
                sequence.removeAt(minIndex)
                sequence.add(value)
                minIndex = indexOfMinInTheRight(sequence)
            }
        }
        sumOfJoltage += sequence.joinToString("").toLong()
    }
    println(sumOfJoltage)
}

fun indexOfMinInTheRight(numbers: List<Int>): Int {
    var index = 0
    for (i in 0..<numbers.size) {
        if (numbers[i] < numbers[index]) {
            index = i
        }
    }
    return index
}

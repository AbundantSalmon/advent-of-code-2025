package day03

import utils.FileUtils
import java.util.Stack

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
        val numbers = line.map { it.toString().toInt() }.toMutableList()

        val stack = Stack<Int>()
        stack.push(numbers.removeFirst())
        while (numbers.isNotEmpty()) {
            val value = numbers.removeFirst()
            val remainingSize = numbers.size

            while (stack.isNotEmpty() && stack.peek() < value && remainingSize + stack.size >= 12) {
                stack.pop()
            }
            stack.push(value)
        }

        sumOfJoltage += stack.take(12).joinToString("").toLong()
    }
    println(sumOfJoltage)
}

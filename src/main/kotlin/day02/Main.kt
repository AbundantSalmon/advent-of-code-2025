package day02

import utils.FileUtils

fun main() {
    val filename = "input02.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

fun part1(lines: List<String>) {
    var sumOfInvalids = 0L
    val ranges = lines[0].split(",")
    for (range in ranges) {
        val (start, end) = range.split("-")
        for (i in start.toLong()..end.toLong()) {
            val stringToCheck = i.toString()
            if (stringToCheck.length % 2 != 0) {
                // ignore non-even length number as they can't be repeated
                continue
            }
            val halfIndex = stringToCheck.length / 2
            if (stringToCheck.take(halfIndex) == stringToCheck.substring(halfIndex)) {
                sumOfInvalids += i
            }
        }
    }
    println(sumOfInvalids)
}

fun part2(lines: List<String>) {
    var sumOfInvalids = 0L
    val ranges = lines[0].split(",")
    for (range in ranges) {
        val (start, end) = range.split("-")
        for (i in start.toLong()..end.toLong()) {
            val stringToCheck = i.toString()

            var tailIndex = 0
            val halfIndex = stringToCheck.length / 2
            while (tailIndex < halfIndex) {
                val length = tailIndex + 1
                val times = stringToCheck.length / length
                val repeat = stringToCheck.take(tailIndex + 1).repeat(times)
                if (stringToCheck == repeat) {
                    sumOfInvalids += i
                    break
                }
                tailIndex += 1
            }
        }
    }
    println(sumOfInvalids)
}

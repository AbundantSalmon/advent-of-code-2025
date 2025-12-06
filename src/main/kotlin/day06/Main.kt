package day06

import utils.FileUtils

fun main() {
    val filename = "input06.txt"
    val lines = FileUtils.readFile(filename)

    println("Part1:")
    part1(lines)

    println("Part2:")
    part2(lines)
}

fun part1(lines: List<String>) {
    var sum = 0L
    val listOfList = mutableListOf<MutableList<Long>>()
    for (line in lines) {
        for ((index, numberString) in line.split("\\s+".toRegex()).withIndex()) {
            if (numberString != "+" && numberString != "*") {
                if (listOfList.size <= index) {
                    listOfList.add(mutableListOf())
                }
                if (numberString == "") {
                    continue
                }
                listOfList[index].add(numberString.toLong())
            } else {
                sum +=
                    if (numberString == "+") {
                        listOfList[index].sum()
                    } else {
                        listOfList[index].reduce { acc, value -> acc * value }
                    }
            }
        }
    }
    println(sum)
}

fun part2(lines: List<String>) {
    var sum = 0L
    val lastLine = lines.last()
    val numberLines = lines.subList(0, lines.size - 1)

    var index = 0
    while (index < numberLines.first().length) {
        val start = index
        var last = index + 1
        val action = lastLine[index]
        if (last < lastLine.length - 1) {
            while (lastLine[last] == ' ') {
                last++
            }
            last--
        } else {
            last++
        }

        val numbers = mutableListOf<Long>()
        for (i in last - 1 downTo start) {
            var numberString = ""
            for (line in numberLines) {
                println(line[i])
                if (line[i] != ' ') {
                    numberString += line[i]
                }
            }
            numbers.add(numberString.toLong())
        }

        sum +=
            if (action == '+') {
                numbers.sum()
            } else {
                numbers.reduce { acc, value -> acc * value }
            }

        index = last + 1
    }
    println(sum)
}

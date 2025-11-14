package utils

class FileUtils {
    companion object {
        fun readFile(filename: String): List<String> {
            val lines =
                object {}
                    .javaClass
                    .getResource("/$filename")
                    ?.readText()
                    ?.split("\n")
                    ?.dropLast(1)
            checkNotNull(lines)
            return lines
        }
    }
}

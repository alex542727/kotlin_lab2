import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main() {
//открываем файл
    val inputFile = File("C:\\Users\\sanya\\IdeaProjects\\kotlin_lab2\\src\\main\\task\\task.txt")

//делит пробелы
    val regex = Regex("\\s+")

    val cleanedLines = inputFile.readLines().map { line ->
        regex.replace(line.trim(), " ")
    }

//пишем в результ
    val resultFile = File("C:\\Users\\sanya\\IdeaProjects\\kotlin_lab2\\src\\main\\task\\result.txt")

    resultFile.writeText(cleanedLines.joinToString(separator = "\n"))

//создаем папку результ
    val resultFolder = File("result")
    resultFolder.mkdirs()

//перенос файла
    val newResultFile = File("C:\\Users\\sanya\\IdeaProjects\\kotlin_lab2\\result\\result.txt")
    resultFile.copyTo(newResultFile, overwrite = true)
    resultFile.delete()

//ренейм
    val finalResultFile = File("C:\\Users\\sanya\\IdeaProjects\\kotlin_lab2\\result\\final_result.txt")
    newResultFile.renameTo(finalResultFile)

//а) с помощью input и OutputStream
    val inputStream = FileInputStream(inputFile)
    val byteArray = inputStream.readBytes()
    inputStream.close()

    val cleanedByteArray = String(byteArray).split("\n").map { line ->
        regex.replace(line, " ")
    }.joinToString(separator = "\n").toByteArray()

    val outputStream = FileOutputStream(resultFile)
    outputStream.write(cleanedByteArray)
    outputStream.close()

//в) c помощью BufferedFileReader и BufferedWriter
    val bufferedReader = BufferedReader(FileReader(inputFile))
    val bufferedWriter = BufferedWriter(FileWriter(resultFile))

    bufferedReader.useLines { lines ->
        lines.forEach { line ->
            bufferedWriter.write(regex.replace(line, " "))
            bufferedWriter.newLine()
        }
    }

    bufferedReader.close()
    bufferedWriter.close()


}

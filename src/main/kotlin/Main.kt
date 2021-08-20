import java.util.*

fun main() {
    println("Введите число строк:")
    val lines = Scanner(System.`in`).nextInt()
    println("Введите число символов в сроке:")
    val length = Scanner(System.`in`).nextInt()
    val generator = FileGenerator()
    val reader = Reader()
    generator.generateFile(lines,length)
    reader.read()
}
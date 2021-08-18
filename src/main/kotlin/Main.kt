fun main() {
    val generator = FileGenerator()
    val reader = Reader()
    val start = System.currentTimeMillis()
    generator.generateFile(170,100)
    reader.read()
    println(System.currentTimeMillis() - start)
}
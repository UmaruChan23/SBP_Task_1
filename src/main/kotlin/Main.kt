fun main() {
    val generator = FileGenerator()
    val reader = Reader()
    val usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
    val start = System.currentTimeMillis()
    generator.generateFile(170,100)
    reader.read()
    println(System.currentTimeMillis() - start)
    println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage)
}
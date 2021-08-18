import java.io.BufferedReader
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.thread
import kotlin.io.path.Path

class Sorter {
    private val cores = Runtime.getRuntime().availableProcessors()
    private val threads = cores * 2 + 1
    val executor = Executors.newFixedThreadPool(threads)

    @Volatile
    private var i: AtomicInteger = AtomicInteger(0)

    @Volatile
    private var files = ArrayList<File>()

    fun sort(list: ArrayList<String>) {
        val worker = Runnable {
            var file: File?
            synchronized(i) {
                file = File("temp_$i.txt")
                i.incrementAndGet()
                files.add(file!!)
            }
            file?.createNewFile()
            file?.writeText("")
            list.sort()
            for (str in list) {
                file?.let { FileGenerator.append(str, it) }
            }
            file = null
        }
        executor.execute(worker)
    }

    fun shutdown() {
        executor.shutdown()
    }

    fun fileSort(finalFile: File) {
        val readers = ArrayList<BufferedReader>()
        val lines = HashMap<Int, String?>()
        var count = 0
        finalFile.writeText("")

        for (file in files) {
            val reader = file.inputStream().bufferedReader()
            lines[count] = reader.readLine()
            readers.add(reader)
            ++count
        }
        while (lines.size > 0) {
            val sortedLines = lines.toList()
                .sortedBy { (_, value) -> value }
                .toMap()

            val finalLine = sortedLines.values.toList()[0]
            val key = sortedLines.keys.toList()[0]
            val reader = readers[key]
            finalFile.appendText("$finalLine\n")
            val temp = reader.readLine()
            if(temp != null ){
                lines[key] = temp
            } else {
                lines.remove(key)
            }
        }
        for(file in files) println(Files.delete(Path(file.absolutePath)))
    }
}

import java.io.BufferedReader
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Sorter {
    private val cores = Runtime.getRuntime().availableProcessors()
    private val threads = cores * 2 + 1
    val executor = Executors.newFixedThreadPool(threads)
    private var i = 0
    private val files = ArrayList<File>()

    fun sort(list: ArrayList<String>){
        val worker = Runnable {
            val file = File("temp_$i.txt")
            file.createNewFile()
            file.writeText("")
            files.add(file)
            ++i
            list.sort()
            for(str in list) {
                FileGenerator.append(str, file)
            }
        }
        executor.execute(worker)
    }

    fun shutdown() {
        executor.shutdown()
    }

    fun fileSort(finalFile: File) {
        val readers = ArrayList<BufferedReader>()
        val lines = HashMap<Int, String>()
        var count = 0

        for(file in files) {
            val reader = file.inputStream().bufferedReader()
            lines.set(count, reader.readLine())
            readers.add(reader)
            ++count
        }

        lines.toList()
            .sortedBy { (key, value) -> value }
            .toMap()


        /*
        while(line1 != null  && line2 != null) {
            if(line1 > line2) {
                FileGenerator.append(line2, finalFile)
                line2 = reader2.readLine()
            } else if(line1.equals(line2)) {
                FileGenerator.append(line1, finalFile)
                FileGenerator.append(line2, finalFile)
                line1 = reader1.readLine()
                line2 = reader2.readLine()
            }
            else {
                FileGenerator.append(line1, finalFile)
                line1 = reader1.readLine()
            }
        }

         */
    }
}

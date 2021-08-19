import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Reader {
    private val fileName = "data.txt"
    private val file = File(fileName)
    private val sorter = Sorter()

    fun read() {
        val list : ArrayList<String> = ArrayList()
        for(str in file.readLines()) {
            list.add(str)
            if(list.size == 10) {
                sorter.sort(ArrayList(list))
                list.clear()
            }
        }
        if (list.size > 0) {
            sorter.sort(ArrayList(list))
            list.clear()
        }
        sorter.shutdown()
        while (!sorter.executor.isTerminated) {
        }
        fileSort(file)
    }
    fun fileSort(file: File) {
        val temp0 = File("temp_0.txt")
        val temp1 = File("temp_1.txt")
        //file.writeText("")
        sorter.fileSort(file)
    }
}
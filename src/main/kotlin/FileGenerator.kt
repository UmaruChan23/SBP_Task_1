import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class FileGenerator {
    private val fileName = "data.txt"
    private val file = File(fileName)

    fun generateFile(count: Int , length: Int) {

        val isNewFileCreated: Boolean = file.createNewFile()

        if (isNewFileCreated) {
            println("$fileName is created successfully.")
        } else {
            println("$fileName already exists.")
            file.writeText("")
        }
        for(i in 1..count){
            append(generateString(length), file)
        }
    }
    companion object {
        fun write(string: String,file: File) {
            file.writeText("$string\n")
        }
        fun append(string: String,file: File) {
            file.appendText("$string\n")
        }
    }
}
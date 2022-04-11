package PrintScript.lexer.inputContent

import java.io.BufferedReader
import java.io.File
import java.util.stream.Collectors

class FileContent(file: File) : Content {

    private val bufferedReader = BufferedReader(file.reader())

    override fun convertContent(): String {
        return bufferedReader.lines().collect(Collectors.joining("\n")).toString()
    }
}

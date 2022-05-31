package interpreter

class InterpreterConsole {
    private val buffer = StringBuffer()
    fun write(result: String) {
        buffer.append(result)
    }

    fun read(): String {
        return buffer.toString()
    }
}

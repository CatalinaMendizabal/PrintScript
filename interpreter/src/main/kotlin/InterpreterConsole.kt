class InterpreterConsole {

    var builder: StringBuilder = StringBuilder()

    fun print(text: String) {
        if (builder.isNotEmpty()) {
            builder.append("\n")
        }
        builder.append(text)
    }

    fun readLine(): String {
        return builder.toString()
    }
}

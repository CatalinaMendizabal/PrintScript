class TerminalPrinter {

    var builder: StringBuilder = StringBuilder()

    fun print(text: String) {
        builder.append(text)
    }

    fun readLine(): String {
        return builder.toString()
    }
}

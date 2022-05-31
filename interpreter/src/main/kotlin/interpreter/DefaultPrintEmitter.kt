package interpreter

class DefaultPrintEmitter : IPrintEmitter {
    override fun print(s: String) {
        println(s)
    }
}

package interpreter

class DefaultPrintEmitter : IPrintEmitter {
    override fun print(s: String) {
        print(s)
    }
}

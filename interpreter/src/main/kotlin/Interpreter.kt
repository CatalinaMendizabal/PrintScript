import node.Node

class Interpreter {

    fun interpret(node: Node): InterpreterConsole {
        val interpreter = InterpreterImplementation()
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }
}

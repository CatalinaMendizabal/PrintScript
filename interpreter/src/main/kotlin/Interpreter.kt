import expression.Function
import node.Node

class Interpreter {

    fun interpret(node: Node): TerminalPrinter {
        val interpreter = InterpreterImplementation()
        node.accept(interpreter)
        return interpreter.terminalPrinter
    }

}

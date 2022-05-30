package interpreter

import ast.node.Node
import interpreter.interpreterVisitor.AbstractInterpreterVisitor
import interpreter.interpreterVisitor.InterpreterVisitorV1
import interpreter.interpreterVisitor.InterpreterVisitorV2

class Interpreter {
    fun run(codeBlock: Node): InterpreterConsole {
        val visitor: AbstractInterpreterVisitor = InterpreterVisitorV1()
        codeBlock.accept(visitor)
        return visitor.result
    }

    fun run(codeBlock: Node, inputProvider: IInputProvider, printEmitter: IPrintEmitter): InterpreterConsole {
        val visitor: AbstractInterpreterVisitor = InterpreterVisitorV2(inputProvider, printEmitter)
        codeBlock.accept(visitor)
        return visitor.result
    }
}

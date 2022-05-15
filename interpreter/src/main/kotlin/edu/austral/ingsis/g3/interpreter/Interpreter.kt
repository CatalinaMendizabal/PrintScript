package edu.austral.ingsis.g3.interpreter

import node.Node

class Interpreter {

    fun interpret(node: Node): InterpreterConsole {
        val interpreter = InterpreterImplementation()
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }

    fun interpreter(node: Node, inputProvider: InputProvider): InterpreterConsole {
        val interpreter = InterpreterImplementation(inputProvider)
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }
}

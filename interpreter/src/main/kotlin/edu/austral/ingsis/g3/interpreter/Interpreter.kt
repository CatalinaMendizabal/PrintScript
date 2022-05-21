package edu.austral.ingsis.g3.interpreter

import node.Node

class Interpreter {

   /* fun interpret(node: Node): InterpreterConsole {
        val interpreter = InterpreterVisitor()
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }*/

    fun interpret(node: Node, readInputProvider: ReadInputProvider): InterpreterConsole {
        val interpreter = InterpreterVisitor(readInputProvider)
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }
}

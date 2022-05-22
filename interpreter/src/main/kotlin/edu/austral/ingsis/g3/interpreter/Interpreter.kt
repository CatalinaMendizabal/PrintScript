package edu.austral.ingsis.g3.interpreter

import node.Node

class Interpreter {

   /* fun interpret(node: Node): InterpreterConsole {
        val interpreter = InterpreterVisitor()
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }*/

    fun interpret(node: Node): InterpreterConsole {
        val interpreter = InterpreterVisitor(DefaultReadInputProvider())
        node.accept(interpreter)
        return interpreter.interpreterConsole
    }
}

package interpreter.exception

import ast.expression.Operand
import ast.node.NodeException

class InvalidOperationException(lString: String, rString: String, opString: Operand) :
    NodeException("Invalid operation: " + lString + " " + opString.string + " " + rString)

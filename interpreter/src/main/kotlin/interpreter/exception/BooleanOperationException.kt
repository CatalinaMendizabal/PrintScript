package interpreter.exception

import ast.expression.Operand
import ast.node.NodeException

class BooleanOperationException(leftResult: String, rightResult: String, operand: Operand) :
    NodeException("Boolean operations are not supported: " + leftResult + " " + operand.string + " " + rightResult)

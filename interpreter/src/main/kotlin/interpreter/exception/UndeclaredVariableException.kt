package interpreter.exception

import ast.node.NodeException

class UndeclaredVariableException(name: String) : NodeException("Undeclared variable: $name")

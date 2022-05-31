package interpreter.exception

import ast.node.NodeException

class TypeMismatchException(name: String, type: String) : NodeException("Type mismatch: $name is of type $type")

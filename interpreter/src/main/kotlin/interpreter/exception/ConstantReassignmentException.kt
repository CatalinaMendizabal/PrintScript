package interpreter.exception

import ast.node.NodeException

class ConstantReassignmentException(name: String) : NodeException("Constant $name cannot be reassigned")

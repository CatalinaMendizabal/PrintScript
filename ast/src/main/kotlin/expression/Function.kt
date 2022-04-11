package expression

import node.Node

interface Function : Node {
    fun accept(visitor: ExpressionVisitor)
    fun addVariable(operand: Operand, variable: Variable): Function
}

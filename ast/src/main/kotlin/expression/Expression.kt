package expression

import node.Node

interface Expression : Node {
    fun accept(visitor: ExpressionVisitor)
    fun addVariable(operand: Operand, variable: Expression): Expression
}

package ast.expression

import ast.node.NodeVisitor

class Variable(val value: String) : Expression {

    override fun accept(visitor: ExpressionVisitor) { visitor.visitVariable(this) }

    override fun addVariable(operand: Operand, variable: Expression): Expression { return Operation(this, operand, variable) }

    override fun accept(visitor: NodeVisitor) {}

    override fun toString(): String { return value }
}

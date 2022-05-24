package expression

import node.NodeVisitor

class Variable(private val value: String) : Expression {

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun accept(visitor: NodeVisitor) {}

    override fun addVariable(operand: Operand, variable: Expression): Expression {
        return Operation(this, operand, variable)
    }

    override fun toString(): String {
        return value
    }

    fun getValue(): String {
        return value
    }

   /* fun getType(): VariableType {
        return type
    }*/
}

package expression

import node.NodeVisitor

class ReadInput(var prompt: Expression) : Expression {

    override fun accept(visitor: NodeVisitor) {}

    override fun addVariable(operand: Operand, variable: Variable): Expression {
        return Operation(this, operand, variable)
    }

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitReadInput(this)
    }

    override fun toString(): String {
        return "ReadInput($prompt)"
    }
}

package ast.expression

import ast.node.NodeVisitor
import lombok.AllArgsConstructor

@AllArgsConstructor
class ReadInput(var prompt: Expression) : Expression {

    override fun accept(visitor: NodeVisitor) {}

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitReadInput(this)
    }

    override fun addVariable(operand: Operand, variable: Expression): Expression {
        return Operation(this, operand, variable)
    }

    override fun toString(): String {
        return "ReadInput($prompt)"
    }
}

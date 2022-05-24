package expression

import node.NodeVisitor

class Operation : Expression {

    var left: Expression
    lateinit var operand: Operand
    lateinit var right: Expression

    constructor(value: String) {
        this.left = Variable(value)
    }

    constructor(left: Expression, operand: Operand, right: Expression) {
        this.left = left
        this.operand = operand
        this.right = right
    }

    override fun accept(visitor: NodeVisitor) {}

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitExpression(this)
    }

    override fun addVariable(operand: Operand, variable: Expression): Expression {
        return if (operand === Operand.SUBSTRACT || operand === Operand.SUM) {
            Operation(this, operand, variable)
        } else {
            val right: Expression = Operation(right, operand, variable)
            Operation(left, this.operand, right)
        }
    }

    override fun toString(): String {
        return ("($left)$operand($right)")
    }
}

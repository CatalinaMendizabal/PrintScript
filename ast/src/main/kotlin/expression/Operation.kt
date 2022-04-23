package expression

import node.NodeVisitor

class Operation : Expression {

    lateinit var left: Expression
    lateinit var operand: Operand
    lateinit var right: Expression

    constructor(value: String, type: VariableType) {
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

    override fun addVariable(operand: Operand, variable: Variable): Expression {
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

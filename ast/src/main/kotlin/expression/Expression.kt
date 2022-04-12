package expression

import node.NodeVisitor

class Expression : Function {

    lateinit var left: Function
    lateinit var operand: Operand
    lateinit var right: Function

    constructor(value: String) {
        this.left = Variable(value)
    }

    constructor(left: Function, operand: Operand, right: Function) {
        this.left = left
        this.operand = operand
        this.right = right
    }

    override fun accept(visitor: NodeVisitor) {}

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitExpression(this)
    }

    override fun addVariable(operand: Operand, variable: Variable): Function {
        return if (operand === Operand.SUB || operand === Operand.SUM) {
            Expression(this, operand, variable)
        } else {
            val right: Function = Expression(right, operand, variable)
            Expression(left, this.operand, right)
        }
    }

    override fun toString(): String {
        return ("($left)$operand($right)")
    }
}

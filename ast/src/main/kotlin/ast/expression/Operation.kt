package ast.expression

import ast.node.NodeVisitor

class Operation : Expression {

    var left: Expression?
    var operand: Operand? = null
    var right: Expression? = null

    constructor(value: String) {
        this.left = Variable(value)
    }

    constructor(left: Expression?, operand: Operand?, right: Expression?) {
        this.left = left
        this.operand = operand
        this.right = right
    }

    override fun accept(visitor: NodeVisitor) {}

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitExpression(this)
    }

    override fun addVariable(operand: Operand, variable: Expression): Expression {
        return if (operand === Operand.SUB || operand === Operand.SUM) {
            Operation(this, operand, variable)
        } else {
            Operation(left, this.operand, Operation(right, operand, variable))
        }
    }

    // toString
    override fun toString(): String {
        return ("($left)$operand($right)")
    }
}

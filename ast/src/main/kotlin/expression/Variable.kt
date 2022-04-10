package expression

import node.NodeVisitor

class Variable(private val value: String) : Function {


    //    @Throws(NodeException::class)
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun accept(visitor: NodeVisitor) {
        TODO("Not yet implemented")
    }

    override fun addVariable(operand: Operand, variable: Variable): Function {
        return Expression(this, operand, variable)
    }


    override fun toString(): String {
        return value
    }
}

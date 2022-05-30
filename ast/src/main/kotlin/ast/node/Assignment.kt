package ast.node

import ast.expression.Expression

class Assignment(val name: String, val value: Expression) : Node {

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "Assignment(name='$name', value=$value)"
    }
}

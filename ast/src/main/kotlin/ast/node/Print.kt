package ast.node

import ast.expression.Expression

class Print(val content: Expression) : Node {

    override fun accept(visitor: NodeVisitor) { visitor.visit(this) }

    override fun toString(): String { return "Print(content=$content)" }
}

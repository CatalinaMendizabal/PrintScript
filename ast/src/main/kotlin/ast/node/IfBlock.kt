package ast.node

import ast.expression.Expression

class IfBlock(val condition: Expression, val ifCodeBlock: CodeBlock, val elseCodeBlock: CodeBlock) : Node {

    override fun accept(visitor: NodeVisitor) { visitor.visit(this) }

    override fun toString(): String {
        return ("ifBlock{condition=$condition, ifCodeBlock=$ifCodeBlock, elseCodeBlock=$elseCodeBlock}")
    }
}

import expression.Expression
import node.Node
import node.NodeVisitor

class Condition(booleanValue: String, ifCode: CodeBlock, elseCode: CodeBlock, condition: Expression) : Node {

    val condition: Expression = condition
    val ifCode: CodeBlock = ifCode
    val elseCode: CodeBlock = elseCode
    val booleanValue = booleanValue

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
}

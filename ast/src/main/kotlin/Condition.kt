import node.Node
import node.NodeVisitor

class Condition(booleanValue: String, ifCode: CodeBlock, elseCode: CodeBlock) : Node {

    val ifCode: CodeBlock = ifCode
    val elseCode: CodeBlock = elseCode
    val booleanValue = booleanValue

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
}

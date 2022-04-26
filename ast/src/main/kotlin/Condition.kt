import node.Node
import node.NodeVisitor

class Condition(ifCode: CodeBlock, elseCode: CodeBlock) : Node {

    val ifCode: CodeBlock = ifCode
    val elseCode: CodeBlock = elseCode

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
}

import expression.Expression
import node.Node
import node.NodeVisitor

class Condition(ifCode: CodeBlock, elseCode: CodeBlock) : Node {
    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }


}
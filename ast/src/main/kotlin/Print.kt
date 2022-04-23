import expression.Expression
import node.Node
import node.NodeVisitor

class Print(var content: Expression) : Node {

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
    override fun toString(): String {
        return "Print(content=$content)"
    }
}

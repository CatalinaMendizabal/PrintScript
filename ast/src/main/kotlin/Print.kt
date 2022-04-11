import expression.Function
import node.Node
import node.NodeVisitor

class Print(var content: Function) : Node {

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
    override fun toString(): String {
        return "Print(content=$content)"
    }
}

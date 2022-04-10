import node.Node
import node.NodeVisitor
import expression.Function

class Print(var content: Function) : Node {

   // @Throws(NodeException::class)
    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }
    override fun toString(): String {
        return "Print(content=$content)"
    }
}

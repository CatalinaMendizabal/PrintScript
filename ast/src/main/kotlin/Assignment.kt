import node.Node
import node.NodeVisitor
import expression.Function

class Assignment(var name: String, var value: Function) : Node {

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "Assignment(name='$name', value=$value)"
    }
}

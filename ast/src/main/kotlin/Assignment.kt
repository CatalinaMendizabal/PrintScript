import expression.Expression
import node.Node
import node.NodeVisitor

class Assignment(var name: String, var value: Expression) : Node {

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "Assignment(name='$name', value=$value)"
    }
}

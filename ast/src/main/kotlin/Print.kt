import expression.Expression
import node.Node
import node.NodeVisitor

class Print(val expr: Expression): Node {
    override fun accept(visitor: NodeVisitor) {
        TODO("Not yet implemented")
    }

}

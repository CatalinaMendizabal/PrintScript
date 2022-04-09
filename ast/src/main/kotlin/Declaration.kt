import expression.Expression
import node.Node
import node.NodeVisitor

class Declaration(val varType: String, val name: String, val value: Expression) : Node {
    override fun accept(visitor: NodeVisitor) {
        TODO("Not yet implemented")
    }
}

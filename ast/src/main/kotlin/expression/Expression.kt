package expression

import node.Node
import node.NodeVisitor

class Expression(val value: String, val optExpression: List<OptionalExpression>) : Node {
    override fun accept(visitor: NodeVisitor) {
        TODO("Not yet implemented")
    }
}

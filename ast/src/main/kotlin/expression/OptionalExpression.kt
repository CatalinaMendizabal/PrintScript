package expression

import node.Node
import node.NodeVisitor

class OptionalExpression(val value: String, val operand: Operand) : Node {

    /* fun accept(visitor: org.gradle.internal.snapshot.ReadOnlyFileSystemNode.NodeVisitor?) {}

    override fun toString(): String? {
        return "ExpressionOptional(operand=$operand, value='$value')"
    }*/
    override fun accept(visitor: NodeVisitor) {
        TODO("Not yet implemented")
    }
}

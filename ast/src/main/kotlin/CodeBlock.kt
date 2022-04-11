import node.Node
import node.NodeVisitor

class CodeBlock : Node {
    private val children: MutableList<Node> = ArrayList<Node>()
    fun addChild(child: Node) {
        children.add(child)
    }

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (child in children) {
            builder.append(child.toString())
        }
        return builder.toString()
    }
}

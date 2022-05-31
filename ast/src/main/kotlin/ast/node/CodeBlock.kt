package ast.node

class CodeBlock : Node {

    val children: MutableList<Node> = ArrayList()

    fun addChild(child: Node) {
        children.add(child)
    }

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (child in children) sb.append(child.toString())
        return sb.toString()
    }
}

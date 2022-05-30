package ast.node

interface Node {
    fun accept(visitor: NodeVisitor)
}

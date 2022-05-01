package node

interface Node {

    fun accept(visitor: NodeVisitor)
}

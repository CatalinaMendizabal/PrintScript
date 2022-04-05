import node.Node

interface Parser<T : Node> {
    fun parse(): T
}

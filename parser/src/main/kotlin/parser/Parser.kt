package parser

import ast.node.Node

interface Parser<T : Node> {
    fun parse(): T
}

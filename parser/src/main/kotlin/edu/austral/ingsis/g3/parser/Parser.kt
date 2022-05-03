package edu.austral.ingsis.g3.parser

import node.Node

interface Parser<T : Node> {
    fun parse(): T
}

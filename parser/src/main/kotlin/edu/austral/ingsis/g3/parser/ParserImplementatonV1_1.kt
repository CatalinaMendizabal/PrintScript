package edu.austral.ingsis.g3.parser

import CodeBlock
import node.Node
import org.austral.ingsis.printscript.common.CoreTokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ParserImplementatonV1_1(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val statementParser = StatementParser(stream)

    override fun parse(): Node {
        val program = CodeBlock()
        while (peek(CoreTokenTypes.EOF) == null) {
            program.addChild(statementParser.parse())
        }
        return program
    }
}

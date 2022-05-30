package parser

import ast.node.CodeBlock
import ast.node.Node
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class ProgramParserV2(stream: TokenIterator) :
    TokenConsumer(stream),
    Parser<Node> {
    private val statementParser: StatementParser = StatementParser(stream)

    override fun parse(): Node {
        val program = CodeBlock()
        while (peek(TokenTypes.EOF) == null) {
            program.addChild(statementParser.parse())
        }
        return program
    }
}

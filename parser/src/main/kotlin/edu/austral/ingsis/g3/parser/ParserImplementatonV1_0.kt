package edu.austral.ingsis.g3.parser

import CodeBlock
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import node.Node
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ParserImplementatonV1_0(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParserV10 = DeclarationParserV1_0(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream, FunctionParserV1_0(stream))

    override fun parse(): Node {
        val program = CodeBlock()
        var nextContent: Content<String>?
        while (isNotAtEndOfFile()) {
            nextContent = peekAny(TokenTypes.LET, TokenTypes.PRINTLN, TokenTypes.TYPESTRING, TokenTypes.TYPENUMBER, TokenTypes.TYPEBOOLEAN, TokenTypes.CONST, TokenTypes.IF)

            if (nextContent != null) {
                when (nextContent.content) {
                    "let" -> {
                        program.addChild(declarationParserV10.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    else -> throwParserException(nextContent)
                }
            } else program.addChild(assignmentParser.parse())
        }

        if (peek(TokenTypes.SEMICOLON) == null) throw UnexpectedTokenException(";", current().range.startCol, current().range.startLine)
        consume(TokenTypes.SEMICOLON)

        return program
    }

    private fun isNotAtEndOfFile() = peek(TokenTypes.EOF) == null

    private fun throwParserException(nextContent: Content<String>) {
        throw ParserException(
            nextContent.content,
            nextContent.token.range.startCol,
            nextContent.token.range.startLine
        )
    }
}

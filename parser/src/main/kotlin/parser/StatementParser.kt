package parser

import ast.node.Node
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.declaration.DeclarationParserV2
import parser.exceptions.UnexpectedKeywordException
import parser.exceptions.UnexpectedTokenException
import parser.function.FunctionParserV2

class StatementParser(stream: TokenIterator) :
    TokenConsumer(stream),
    Parser<Node> {
    private val declarationParserV2 = DeclarationParserV2(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(
        stream,
        FunctionParserV2(stream)
    )
    private val ifParser = IfBlockParser(stream, this)

    override fun parse(): Node {
        val result: Node
        val next: Content<String>? = peekAny(TokenTypes.LET, TokenTypes.CONST, TokenTypes.PRINTLN, TokenTypes.READINPUT, TokenTypes.IF, TokenTypes.ELSE)
        if (next != null) {
            when (next.content) {
                "let", "const" -> {
                    result = declarationParserV2.parse()
                }
                "println" -> {
                    result = printParser.parse()
                }
                "if" -> {
                    result = ifParser.parse()
                    if (peek(TokenTypes.SEMICOLON) != null) consumeEOL()
                    return result
                }
                else -> throw UnexpectedKeywordException(
                    next.content,
                    next.token.range.startCol,
                    next.token.range.startLine
                )
            }
        } else {
            result = assignmentParser.parse()
        }
        consumeEOL()
        return result
    }

    private fun consumeEOL() {
        if (peek(TokenTypes.SEMICOLON) == null) throw UnexpectedTokenException(
            ";",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.SEMICOLON)
    }
}

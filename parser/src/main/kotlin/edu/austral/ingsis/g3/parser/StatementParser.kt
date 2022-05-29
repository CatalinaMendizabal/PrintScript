package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.parser.declaration.DeclarationParserV2
import edu.austral.ingsis.g3.parser.exceptions.UnexpectedKeywordException
import edu.austral.ingsis.g3.parser.exceptions.UnexpectedTokenException
import edu.austral.ingsis.g3.parser.function.FunctionParserV2
import node.Node
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class StatementParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParserV2 = DeclarationParserV2(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream, FunctionParserV2(stream))
    private val conditionParser: ConditionParser = ConditionParser(stream, this)

    override fun parse(): Node {
        val result: Node
        val next: Content<String>? = peekAny(TokenTypes.LET, TokenTypes.CONST, TokenTypes.PRINTLN, TokenTypes.READINPUT, TokenTypes.IF, TokenTypes.ELSE)
        if (next != null) {
            when (next.content) {
                "const", "let" -> {
                    result = declarationParserV2.parse()
                }
                "println" -> {
                    result = printParser.parse()
                }
                "if" -> {
                    result = conditionParser.parse()
                    if (peek(TokenTypes.SEMICOLON) != null) consumeEOL()
                    return result
                }
                else -> throw UnexpectedKeywordException(next.content, next.token.range.startCol, next.token.range.startLine)
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

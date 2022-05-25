package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import node.Node
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class StatementParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParserV1_1 = DeclarationParserV1_1(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream, FunctionParserV1_1(stream))
    private val conditionParser: ConditionParser = ConditionParser(stream, this)

    override fun parse(): Node {
        val result: Node
        val next: Content<String>? = peekAny(TokenTypes.LET, TokenTypes.CONST, TokenTypes.PRINTLN, TokenTypes.READINPUT, TokenTypes.IF, TokenTypes.ELSE)
        if (next != null) {
            if (next.content == "LET" || next.content == "CONST") {
                result = declarationParserV1_1.parse()
            } else if (next.content == "PRINTLN") {
                result = printParser.parse()
            } else if (next.content == "IF") {
                result = conditionParser.parse()
                if (peek(TokenTypes.SEMICOLON) != null) consumeEOL()
                return result
            } else throw UnexpectedKeywordException(next.content, next.token.range.startCol, next.token.range.startLine)
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

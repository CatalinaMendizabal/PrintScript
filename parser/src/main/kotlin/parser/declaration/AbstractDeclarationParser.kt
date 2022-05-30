package parser.declaration

import ast.expression.Expression
import ast.node.Declaration
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.Parser
import parser.exceptions.UnexpectedTokenException
import parser.function.AbstractFunctionParser
import parser.function.FunctionParserV1

abstract class AbstractDeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    var functionParser: AbstractFunctionParser = FunctionParserV1(stream)

    override fun parse(): Declaration {
        val isConst = consumeDeclarationKeyword()
        val variable = consumeIdentifier()
        consumeTypeSeparator()
        val type = consumeTypeKeyword()
        if (peek(TokenTypes.SEMICOLON) != null) { return Declaration(variable, type, isConst) }
        consumeAssignmentOperator()
        val expression: Expression = functionParser.parse()
        return Declaration(variable, type, isConst, expression)
    }

    private fun consumeDeclarationKeyword(): Boolean {
        consume(TokenTypes.LET)
        if (peek(TokenTypes.IDENTIFIER) == null) throw UnexpectedTokenException(
            "identifier",
            current().range.startCol,
            current().range.startLine
        )
        return false
    }

    private fun consumeAssignmentOperator() {
        if (peek(TokenTypes.EQUAL) == null) throw UnexpectedTokenException(
            "=",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.EQUAL)
    }

    private fun consumeTypeKeyword(): String {
        if (peekAny(TokenTypes.TYPESTRING, TokenTypes.TYPENUMBER, TokenTypes.TYPEBOOLEAN) == null) throw UnexpectedTokenException("type", current().range.startCol, current().range.startLine)
        return consumeAny(TokenTypes.TYPESTRING, TokenTypes.TYPENUMBER, TokenTypes.TYPEBOOLEAN).content
    }

    private fun consumeTypeSeparator() {
        if (peek(TokenTypes.COLON) == null) throw UnexpectedTokenException(
            ":",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.COLON)
    }

    private fun consumeIdentifier(): String {
        return consume(TokenTypes.IDENTIFIER).content
    }
}

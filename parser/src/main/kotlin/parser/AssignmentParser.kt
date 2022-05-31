package parser

import ast.expression.Expression
import ast.node.Assignment
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.exceptions.UnexpectedTokenException
import parser.function.AbstractFunctionParser

class AssignmentParser(stream: TokenIterator, private val expressionParser: AbstractFunctionParser) :
    TokenConsumer(stream), Parser<Assignment> {
    override fun parse(): Assignment {
        if (peek(TokenTypes.IDENTIFIER) == null) throw UnexpectedTokenException(
            "Identifier",
            current().range.startCol,
            current().range.startLine
        )
        val variable = consume(TokenTypes.IDENTIFIER).content
        if (peek(TokenTypes.EQUAL) == null) throw UnexpectedTokenException(
            "=",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.EQUAL)
        val expression: Expression = expressionParser.parse()
        return Assignment(variable, expression)
    }
}

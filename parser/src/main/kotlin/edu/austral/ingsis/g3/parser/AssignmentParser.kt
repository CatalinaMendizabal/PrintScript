package edu.austral.ingsis.g3.parser
import Assignment
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Assignment {
        if (peek(TokenTypes.IDENTIFIER) == null) throwParserException("identifier")
        val variable = consume(TokenTypes.IDENTIFIER).content
        if (peek(TokenTypes.EQUAL) == null) throwParserException("=")
        consume(TokenTypes.EQUAL)
        val expression: Expression = expressionParser.parse()
        return Assignment(variable, expression)
    }

    private fun throwParserException(value: String) {
        throw ParserException(
            "Expected an $value",
            current().range.startCol,
            current().range.startLine
        )
    }
}

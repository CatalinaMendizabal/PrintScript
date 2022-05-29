package edu.austral.ingsis.g3.parser
import Assignment
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.parser.exceptions.ParserException
import edu.austral.ingsis.g3.parser.function.AbstractFunctionParser
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class AssignmentParser(@NotNull stream: TokenIterator, private val expressionParser: AbstractFunctionParser) : TokenConsumer(stream), Parser<Assignment> {

    override fun parse(): Assignment {
        if (peek(TokenTypes.IDENTIFIER) == null) throwParserException("Identifier")
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

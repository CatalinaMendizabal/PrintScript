package edu.austral.ingsis.g3.parser
import Assignment
import edu.austral.ingsis.g3.lexer.lexerEnums.Type
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Assignment {
        if (peek(Type.IDENTIFIER) == null) throwParserException("identifier")
        val variable = consume(Type.IDENTIFIER).content
        if (peek(Type.EQUAL) == null) throwParserException("=")
        consume(Type.EQUAL)
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

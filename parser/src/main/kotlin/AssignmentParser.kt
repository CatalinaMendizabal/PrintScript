import PrintScript.lexer.lexerEnums.Types
import expression.Function
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Assignment {
        if (peek(Types.IDENTIFIER) == null) throwParserException("identifier")
        val variable = consume(Types.IDENTIFIER).content
        if (peek(Types.EQUAL) == null) throwParserException("=")
        consume(Types.EQUAL)
        val function: Function = expressionParser.parse()
        return Assignment(variable, function)
    }

    private fun throwParserException(value: String) {
        throw ParserException(
            "Expected an $value",
            current().range.startCol,
            current().range.startLine
        )
    }
}

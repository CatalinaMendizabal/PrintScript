import PrintScript.lexer.lexerEnums.Types.*
import expression.Function
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull


class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Assignment {
        if (peek(IDENTIFIER) == null) throw ParserException(
            "Expected an identifier",
            current().range.startCol,
            current().range.startLine
        )
        val variable = consume(IDENTIFIER).content
        if (peek(EQUAL) == null) throw ParserException(
            "Expected =",
            current().range.startCol,
            current().range.startLine
        )
        consume(EQUAL)
        val function: Function = expressionParser.parse()
        return Assignment(variable, function)
    }
}

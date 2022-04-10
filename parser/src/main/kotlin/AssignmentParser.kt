import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull
import expression.Function


class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser: FunctionParser = FunctionParser(stream)

   // @Throws(UnexpectedTokenException::class)
    override fun parse(): Assignment {
        if (peek(Types.IDENTIFIER) == null) throw ParserException(
            "identifier",
            current().range.startCol,
            current().range.startLine
        )
        val variable = consume(Types.IDENTIFIER).content
        if (peek(Types.EQUAL) == null) throw ParserException(
            "=",
            current().range.startCol,
            current().range.startLine
        )
        consume(Types.EQUAL)
        val function: Function = expressionParser.parse()
        return Assignment(variable, function)
    }
}

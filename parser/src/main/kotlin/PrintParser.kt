import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import expression.Function

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    @Throws(ParserException::class)
    override fun parse(): Print {
        consume(Types.PRINT, "println")
        if (peek(Types.LEFTPARENTHESIS, "(") == null) throw ParserException(
            "(",
            current().range.startCol,
            current().range.startLine
        )
        consume(Types.LEFTPARENTHESIS, "(")
        val content: Function = expressionParser.parse()
        if (peek(Types.RIGHTPARENTHESIS, ")") == null) throw ParserException(
            ")",
            current().range.startCol,
            current().range.startLine
        )
        consume(Types.RIGHTPARENTHESIS, ")")
        return Print(content)
    }
}

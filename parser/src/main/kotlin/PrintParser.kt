import PrintScript.lexer.lexerEnums.Types.*
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import expression.Function

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    @Throws(ParserException::class)
    override fun parse(): Print {
        consume(PRINT, "println")
        if (peek(LEFTPARENTHESIS, "(") == null) throw ParserException(
            "Expected (",
            current().range.startCol,
            current().range.startLine
        )
        consume(LEFTPARENTHESIS, "(")
        val content: Function = expressionParser.parse()
        if (peek(RIGHTPARENTHESIS, ")") == null) throw ParserException(
            "Expected )",
            current().range.startCol,
            current().range.startLine
        )
        consume(RIGHTPARENTHESIS, ")")
        return Print(content)
    }
}

import PrintScript.lexer.lexerEnums.Types.*
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import expression.Function

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    override fun parse(): Print {

        consume(PRINT)

        if (peek(LEFTPARENTHESIS) == null) throwParserException("(")
        consume(LEFTPARENTHESIS)

        val content: Function = expressionParser.parse()

        if (peek(RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(RIGHTPARENTHESIS)

        return Print(content)
    }

    private fun throwParserException(value: String) {
        throw ParserException("Expected $value", current().range.startCol, current().range.startLine)
    }
}

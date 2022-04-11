import PrintScript.lexer.lexerEnums.Types
import expression.Function
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    override fun parse(): Print {

        consume(Types.PRINT)

        if (peek(Types.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(Types.LEFTPARENTHESIS)

        val content: Function = expressionParser.parse()

        if (peek(Types.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(Types.RIGHTPARENTHESIS)

        return Print(content)
    }

    private fun throwParserException(value: String) {
        throw ParserException("Expected $value", current().range.startCol, current().range.startLine)
    }
}

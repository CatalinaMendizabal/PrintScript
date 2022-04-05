import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull


class PrintParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {
    private val expressionParser: ExpressionParser = ExpressionParser(stream)
    override fun parse(): Print {
        consume(Types.PRINT, "println")
        consume(Types.LEFTPARENTHESIS, "(")
        val content: Expression = expressionParser.parse()
        consume(Types.RIGHTPARENTHESIS, ")")
        return Print(content)
    }
}


import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class AssignmentParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Assignment> {
    private val expressionParser = ExpressionParser(stream)
    override fun parse(): Assignment {
        val variable = consume(Types.IDENTIFIER).content
        consume(Types.EQUAL)
        val expression: Expression = expressionParser.parse()
        return Assignment(variable, expression)
    }
}

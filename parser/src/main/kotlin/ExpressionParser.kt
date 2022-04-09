import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import expression.Operand
import expression.OptionalExpression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ExpressionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Expression> {
    override fun parse(): Expression {
        return if (peek(Types.IDENTIFIER) != null) {
            val variable = consume(Types.IDENTIFIER).content
            Expression(variable, exprOpts())
        } else {
            val literal = consume(Types.LITERAL).content
            Expression(literal, exprOpts())
        }
    }

    private fun exprOpts(): List<OptionalExpression> {
        val result: MutableList<OptionalExpression> = ArrayList()
        while (peek(Types.SUM) != null) { // TODO TIENE QUE ADAPTARSE A TOODS :(
            if (peek(Types.LITERAL) != null) {
                val literal = consume(Types.LITERAL).content
                result.add(OptionalExpression(literal, Operand.getOperand("+")))
            } else {
                val identifier = consume(Types.IDENTIFIER).content
                result.add(OptionalExpression(identifier, Operand.getOperand("")))
            }
        }
        return result
    }
}

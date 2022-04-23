import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import expression.Operand
import expression.Variable
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class FunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Expression> {

    override fun parse(): Expression {

        if (isNotAKeyWord()) throwParserException()
        val value = consumeKeyWord()

        if (isNotOperand()) return Variable(value)
        var result: Expression = Variable(value)

        while (isAnOperand()) {
            val operand: Operand? =
                Operand.getOperand(consumeAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE).content)

            if (isNotAKeyWord()) throwParserException()
            val next = consumeKeyWord()
            result = operand?.let { result.addVariable(it, Variable(next)) }!!
        }

        return result
    }

    private fun consumeKeyWord() = consumeAny(Types.IDENTIFIER, Types.LITERAL, Types.NUMBER, Types.STRING, Types.BOOLEAN).content

    private fun isAnOperand() = peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) != null

    private fun isNotOperand() = peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) == null

    private fun isNotAKeyWord() = peekAny(Types.IDENTIFIER, Types.LITERAL, Types.NUMBER, Types.STRING, Types.BOOLEAN) == null

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }
}

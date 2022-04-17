import PrintScript.lexer.lexerEnums.Types
import expression.Function
import expression.Operand
import expression.Variable
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class FunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Function> {

    override fun parse(): Function {

        if (isNotAKeyWord()) throwParserException()
        val value = consumeKeyWord()

        if (isNotOperand()) return Variable(value)
        var result: Function = Variable(value)

        while (isAnOperand()) {
            val operand: Operand? =
                Operand.getOperand(consumeAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE).content)

            if (isNotAKeyWord()) throwParserException()
            val next = consumeKeyWord()
            result = operand?.let { result.addVariable(it, Variable(next)) }!!
        }

        return result
    }

    private fun consumeKeyWord() = consumeAny(Types.IDENTIFIER, Types.LITERAL, Types.NUMBER, Types.STRING).content

    private fun isAnOperand() = peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) != null

    private fun isNotOperand() = peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) == null

    private fun isNotAKeyWord() = peekAny(Types.IDENTIFIER, Types.LITERAL, Types.NUMBER, Types.STRING) == null

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }
}

package parser.function

import ast.expression.Expression
import ast.expression.Operand
import ast.expression.Variable
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.Parser
import parser.exceptions.UnexpectedTokenException

abstract class AbstractFunctionParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Expression> {

    override fun parse(): Expression {
        if (isNotAKeyWord()) throwTokenException()
        val value = consumeKeyWord()
        if (isNotAnOperand()) return Variable(value)
        var result: Expression = Variable(value)
        while (isAnOperand()) {
            val operand: Operand? = Operand.getOperand(consumeOperand())
            if (isNotAKeyWord()) throwTokenException()
            val next = consumeKeyWord()
            result = operand?.let { result.addVariable(it, Variable(next)) }!!
        }
        return result
    }

    private fun throwTokenException() {
        throw UnexpectedTokenException("identifier/literal", current().range.startCol, current().range.startLine)
    }

    private fun isNotAKeyWord() = peekAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
    ) == null

    private fun consumeKeyWord() = consumeAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
    ).content

    private fun consumeOperand() = consumeAny(
        TokenTypes.SUM,
        TokenTypes.SUBSTRACT,
        TokenTypes.MULTIPLY,
        TokenTypes.DIVIDE
    ).content

    private fun isNotAnOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) == null

    private fun isAnOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) != null
}

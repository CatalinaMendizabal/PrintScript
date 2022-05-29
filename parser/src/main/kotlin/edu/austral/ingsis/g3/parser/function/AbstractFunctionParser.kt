package edu.austral.ingsis.g3.parser.function

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.parser.Parser
import edu.austral.ingsis.g3.parser.exceptions.ParserException
import expression.Expression
import expression.Operand
import expression.Variable
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

abstract class AbstractFunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Expression> {

    override fun parse(): Expression {
        var result: Expression

        if (isNotAKeyWord()) throwParserException()
        val value: String = consumeKeyWord()

        if (isNotAnOperand()) return Variable(value)
        result = Variable(value)

        while (isAnOperand()) {
            val operand: Operand? = Operand.getOperand(consumeOperand())
            if (isNotAKeyWord()) throwParserException()
            val next = consumeKeyWord()
            result = result.addVariable(operand!!, Variable(next))
        }

        return result
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

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }
}

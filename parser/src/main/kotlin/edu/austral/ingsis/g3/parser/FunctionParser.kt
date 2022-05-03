package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.Type
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
                Operand.getOperand(consumeAny(Type.SUM, Type.SUBSTRACT, Type.MULTIPLY, Type.DIVIDE).content)

            if (isNotAKeyWord()) throwParserException()
            val next = consumeKeyWord()
            result = operand?.let { result.addVariable(it, Variable(next)) }!!
        }

        return result
    }

    private fun consumeKeyWord() = consumeAny(Type.IDENTIFIER, Type.LITERAL, Type.NUMBER, Type.STRING, Type.BOOLEAN).content

    private fun isAnOperand() = peekAny(Type.SUM, Type.SUBSTRACT, Type.MULTIPLY, Type.DIVIDE) != null

    private fun isNotOperand() = peekAny(Type.SUM, Type.SUBSTRACT, Type.MULTIPLY, Type.DIVIDE) == null

    private fun isNotAKeyWord() = peekAny(Type.IDENTIFIER, Type.LITERAL, Type.NUMBER, Type.STRING, Type.BOOLEAN) == null

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }
}

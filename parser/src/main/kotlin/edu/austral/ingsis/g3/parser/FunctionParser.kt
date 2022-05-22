package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import expression.Expression
import expression.Operand
import expression.Variable
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class FunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Expression> {
    private val readInputParser = ReadInputParser(stream, this)

    override fun parse(): Expression {

        var result: Expression
        val value: String

        if (isReadInput()) result = readInputParser.parse()
        else if (isAKeyWord()) {
            value = consumeKeyWord()
            if (isNotOperand()) return Variable(value)
            result = Variable(value)
        } else {
            throwParserException()
            result = Variable("")
        }

        while (isAnOperand()) {
            val operand: Operand? =
                Operand.getOperand(
                    consumeAny(
                        TokenTypes.SUM,
                        TokenTypes.SUBSTRACT,
                        TokenTypes.MULTIPLY,
                        TokenTypes.DIVIDE
                    ).content
                )

            if (isAKeyWord()) {
                val next = consumeKeyWord()
                result = operand?.let { result.addVariable(it, Variable(next)) }!!
            } else if (isReadInput()) {
                result = operand?.let { result.addVariable(it, readInputParser.parse()) }!!
            }
        }
        return result
    }

    private fun consumeKeyWord() = consumeAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
        TokenTypes.BOOLEAN,
        /*    TokenTypes.READINPUT*/
    ).content

    private fun isAnOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) != null

    private fun isNotOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) == null

    private fun isReadInput() = peek(TokenTypes.READINPUT) != null

    private fun isAKeyWord() = peekAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
        TokenTypes.BOOLEAN,
        /* TokenTypes.READINPUT*/
    ) != null

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }
}

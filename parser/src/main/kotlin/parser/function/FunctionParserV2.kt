package parser.function

import ast.expression.Expression
import ast.expression.Operand
import ast.expression.Variable
import enums.TokenTypes
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.ReadInputParser
import parser.exceptions.UnexpectedTokenException

class FunctionParserV2(stream: TokenIterator) :
    AbstractFunctionParser(stream) {
    var readInputParser: ReadInputParser = ReadInputParser(stream, this)

    override fun parse(): Expression {
        var result: Expression
        result = if (peek(TokenTypes.READINPUT) != null) {
            readInputParser.parse()
        } else if (isAKeyWord()) {
            val value: String = consumeKeyWord()
            if (isNotOperand()) return Variable(value)
            Variable(value)
        } else throw UnexpectedTokenException(
            "Identifier or Literal",
            current().range.startCol,
            current().range.startLine
        )
        while (isAnOperand()) {
            val operand: Operand? = Operand.getOperand(
                consumeAny(
                    TokenTypes.SUM,
                    TokenTypes.SUBSTRACT,
                    TokenTypes.MULTIPLY,
                    TokenTypes.DIVIDE
                ).content
            )
            result = if (isAKeyWord()) {
                val next: String = consumeKeyWord()
                result.addVariable(operand!!, Variable(next))
            } else if (peek(TokenTypes.READINPUT) != null) {
                result.addVariable(operand!!, readInputParser.parse())
            } else throw UnexpectedTokenException(
                "Identifier or Literal",
                current().range.startCol,
                current().range.startLine
            )
        }
        return result
    }

    private fun isAKeyWord() = peekAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
        TokenTypes.BOOLEAN,
    ) != null

    private fun consumeKeyWord() = consumeAny(
        TokenTypes.IDENTIFIER,
        TokenTypes.NUMBER,
        TokenTypes.STRING,
        TokenTypes.BOOLEAN,
    ).content

    private fun isAnOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) != null

    private fun isNotOperand() =
        peekAny(TokenTypes.SUM, TokenTypes.SUBSTRACT, TokenTypes.MULTIPLY, TokenTypes.DIVIDE) == null
}

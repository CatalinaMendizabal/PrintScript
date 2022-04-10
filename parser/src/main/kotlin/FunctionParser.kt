import PrintScript.lexer.lexerEnums.Types.*
import expression.Variable
import expression.Function
import expression.Operand
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class FunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Function> {

    override fun parse(): Function {

        if (isNeitherLiteralNorIdentifier()) throwParserException()
        val value = consumeLiteralOrIdentifier()

        if (isNotOperand()) return Variable(value)
        var result: Function = Variable(value)

        while (isAnOperand()) {
            val operand: Operand? = Operand.getOperand(consumeAny(SUM, SUBSTRACT, MULTIPLY, DIVIDE).content)

            if (isNeitherLiteralNorIdentifier()) throwParserException()
            val next = consumeLiteralOrIdentifier()
            result = operand?.let { result.addVariable(it,  Variable(next)) }!!;
        }

        return result
    }

    private fun consumeLiteralOrIdentifier() = consumeAny(IDENTIFIER, LITERAL).content

    private fun isAnOperand() = peekAny(SUM, SUBSTRACT, MULTIPLY, DIVIDE) != null

    private fun isNotOperand() = peekAny(SUM, SUBSTRACT, MULTIPLY, DIVIDE) == null

    private fun isNeitherLiteralNorIdentifier() = peekAny(IDENTIFIER, LITERAL) == null

    private fun throwParserException() {
        throw ParserException("Expected an identifier or literal", current().range.startCol, current().range.startLine)
    }

}

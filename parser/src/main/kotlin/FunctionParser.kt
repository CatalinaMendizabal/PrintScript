import PrintScript.lexer.lexerEnums.Types
import expression.Variable
import expression.Function
import expression.Operand
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull


class FunctionParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Function> {

    @Throws(ParserException::class)
    override fun parse(): Function {
        if (peekAny(Types.IDENTIFIER, Types.LITERAL) == null) throw ParserException(
            "identifier/literal",
            current().range.startCol,
            current().range.startLine
        )
        val value = consumeAny(Types.IDENTIFIER, Types.LITERAL).content
        if (peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) == null) return Variable(value)
        var result: Function = Variable(value)
        while (peekAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE) != null) {
            val operand: Operand? = Operand.getOperand(consumeAny(Types.SUM, Types.SUBSTRACT, Types.MULTIPLY, Types.DIVIDE).content)
            if (peekAny(
                    Types.IDENTIFIER,
                    Types.LITERAL
                ) == null
            ) throw ParserException(
                "identifier/literal",
                current().range.startCol,
                current().range.startLine
            )
            val next = consumeAny(Types.IDENTIFIER, Types.LITERAL).content
          //  result = result.addVariable(operand, Variable(next))
            result = operand?.let { result.addVariable(it, Variable(next)) }!!
        }
        return result
    }
}

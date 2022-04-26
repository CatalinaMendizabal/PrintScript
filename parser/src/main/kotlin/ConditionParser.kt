import expression.Expression
import lexerEnums.Type
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class ConditionParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Condition> {
    private val parserCode: ParserImplementation = ParserImplementation(stream)

    /*
    if (a) {
        pritnln(a);
        val b: string = "a";
        print(b);
    }
     */
    override fun parse(): Condition {
        var ifCode = CodeBlock()
        var elseCode = CodeBlock()
//        var expression = FunctionParser(stream)

        ifStatement()
        ifCode = executeConditionCode()

        if (peek(Type.ELSE) != null) {
            consume(Type.ELSE).content
            elseCode = executeConditionCode()
        }
        return Condition(ifCode, elseCode)
    }

    private fun ifStatement() {
        consume(Type.IF).content
        if (peek(Type.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(Type.LEFTPARENTHESIS)

        if (peek(Type.BOOLEANTYPE) == null) throwParserException("boolean")
        consume(Type.BOOLEANTYPE)

        if (peek(Type.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(Type.RIGHTPARENTHESIS)
    }

    private fun executeConditionCode(): CodeBlock {
        val codeBlock = CodeBlock()
        if (peek(Type.LEFTBRACKET) == null) throwParserException("{")
        consume(Type.LEFTBRACKET)

        codeBlock.addChild(parserCode.parse())

        if (peek(Type.RIGTHBRACKET) == null) throwParserException("}")
        consume(Type.RIGTHBRACKET)
        return codeBlock
    }

    private fun throwParserException(boolean: String) {
        throw ParserException(
            "Expected an $boolean",
            current().range.startCol,
            current().range.startLine
        )
    }

}
import expression.Expression
import lexerEnums.Type
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class DeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val functionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Declaration {
        // consumeAny(Types.CONST, Types.LET)
        consume(Type.LET)
        if (peek(Type.IDENTIFIER) == null) throwParserError("Expected identifier")
        val variable = consume(Type.IDENTIFIER).content

        if (peek(Type.COLON) == null) throwParserError("Expected :")
        consume(Type.COLON, ":")

        /*TODO for version 1.1
            if (peekAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT, Types.BOOLEANTYPE, Types.CONST) == null) throwParserError("Expected type")
             val type = consumeAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT, Types.BOOLEANTYPE, Types.CONST).content*/

        if (peekAny(
                Type.STRINGTYPE,
                Type.LET,
                Type.NUMBERTYPE,
                Type.PRINT
            ) == null
        ) throwParserError("Expected type")
        val type = consumeAny(Type.STRINGTYPE, Type.LET, Type.NUMBERTYPE, Type.PRINT).content
        if (peek(Type.SEMICOLON, ";") != null) return Declaration(variable, type)

        if (peek(Type.EQUAL, "=") == null) throwParserError("Expected =")
        consume(Type.EQUAL, "=")

        val expression: Expression = functionParser.parse()
        return Declaration(variable, type, expression)
    }

    private fun throwParserError(text: String) {
        throw ParserException(text, current().range.startCol, current().range.startLine)
    }
}

import PrintScript.lexer.lexerEnums.Types
import expression.Function
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class DeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val functionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Declaration {
        consume(Types.LET, "let")
        if (peek(Types.IDENTIFIER) == null) throwParserError("Expected identifier")
        val variable = consume(Types.IDENTIFIER).content

        if (peek(Types.COLON) == null) throwParserError("Expected :")
        consume(Types.COLON, ":")

        if (peekAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT) == null) throwParserError("Expected type")
        val type = consumeAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT).content
        if (peek(Types.SEMICOLON, ";") != null) return Declaration(variable, type)

        if (peek(Types.EQUAL, "=") == null) throwParserError("Expected =")
        consume(Types.EQUAL, "=")

        val function: Function = functionParser.parse()
        return Declaration(variable, type, function)
    }

    private fun throwParserError(text: String) {
        throw ParserException(text, current().range.startCol, current().range.startLine)
    }
}

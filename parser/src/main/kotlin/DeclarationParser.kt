import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import expression.Function

class DeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val functionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Declaration {
        consume(Types.LET, "let")
        if (peek(Types.IDENTIFIER) == null) throw ParserException(
            "identifier",
            current().range.startCol,
            current().range.startLine
        )
        val variable = consume(Types.IDENTIFIER).content
        if (peek(Types.COLON) == null) throw ParserException(
            ":",
            current().range.startCol,
            current().range.startLine
        )
        consume(Types.COLON, ":")
        if (peekAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT) == null) throw ParserException(
            "type",
            current().range.startCol,
            current().range.startLine
        )
        val type = consumeAny(Types.STRINGTYPE, Types.LET, Types.NUMBERTYPE, Types.PRINT).content
        if (peek(Types.SEMICOLON, ";") != null) {
            return Declaration(variable, type)
        }
        if (peek(Types.EQUAL, "=") == null) throw ParserException(
            "=",
            current().range.startCol,
            current().range.startLine
        )
        consume(Types.EQUAL, "=")
        val function: Function = functionParser.parse()
        return Declaration(variable, type, function)
    }
}

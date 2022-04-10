import PrintScript.lexer.lexerEnums.Types.*
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import expression.Function

class DeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val functionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Declaration {
        consume(LET, "let")
        if (peek(IDENTIFIER) == null) throw ParserException(
            "Expected identifier",
            current().range.startCol,
            current().range.startLine
        )
        val variable = consume(IDENTIFIER).content
        if (peek(COLON) == null) throw ParserException(
            "Expected :",
            current().range.startCol,
            current().range.startLine
        )
        consume(COLON, ":")
        if (peekAny(STRINGTYPE, LET, NUMBERTYPE, PRINT) == null) throw ParserException(
            "Expected type",
            current().range.startCol,
            current().range.startLine
        )
        val type = consumeAny(STRINGTYPE, LET, NUMBERTYPE, PRINT).content
        if (peek(SEMICOLON, ";") != null) {
            return Declaration(variable, type)
        }
        if (peek(EQUAL, "=") == null) throw ParserException(
            "Expected =",
            current().range.startCol,
            current().range.startLine
        )
        consume(EQUAL, "=")
        val function: Function = functionParser.parse()
        return Declaration(variable, type, function)
    }
}

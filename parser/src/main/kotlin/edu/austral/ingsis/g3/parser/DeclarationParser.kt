package edu.austral.ingsis.g3.parser

import Declaration
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class DeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val functionParser: FunctionParser = FunctionParser(stream)

    override fun parse(): Declaration {
        consumeAny(TokenTypes.CONST, TokenTypes.LET)
        if (peek(TokenTypes.IDENTIFIER) == null) throwParserError("Expected identifier")
        val variable = consume(TokenTypes.IDENTIFIER).content

        if (peek(TokenTypes.COLON) == null) throwParserError("Expected :")
        consume(TokenTypes.COLON, ":")

        if (peekAny(TokenTypes.STRINGTYPE, TokenTypes.LET, TokenTypes.NUMBERTYPE, TokenTypes.PRINT, TokenTypes.BOOLEANTYPE, TokenTypes.CONST) == null) throwParserError("Expected type")
        val type = consumeAny(TokenTypes.STRINGTYPE, TokenTypes.LET, TokenTypes.NUMBERTYPE, TokenTypes.PRINT, TokenTypes.BOOLEANTYPE, TokenTypes.CONST).content

        if (peek(TokenTypes.SEMICOLON, ";") != null) return Declaration(variable, type)

        if (peek(TokenTypes.EQUAL, "=") == null) throwParserError("Expected =")
        consume(TokenTypes.EQUAL, "=")

        val expression: Expression = functionParser.parse()
        return Declaration(variable, type, expression)
    }

    private fun throwParserError(text: String) {
        throw ParserException(text, current().range.startCol, current().range.startLine)
    }
}

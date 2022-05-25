package edu.austral.ingsis.g3.parser

import Declaration
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

abstract class AbstractDeclarationParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    var functionParser: AbstractFunctionParser = FunctionParserV1_0(stream)

    override fun parse() : Declaration {
        val isConst: Boolean = consumeDeclarationKeyword()
        val variable: String = consume(TokenTypes.IDENTIFIER).content

        if (peek(TokenTypes.COLON) == null) throwParserError("Expected :")
        consume(TokenTypes.COLON)

        if (peekAny(TokenTypes.TYPESTRING, TokenTypes.LET, TokenTypes.TYPENUMBER, TokenTypes.PRINTLN, TokenTypes.TYPEBOOLEAN, TokenTypes.CONST, TokenTypes.READINPUT) == null) throwParserError("Expected type")
        val type = consumeAny(TokenTypes.TYPESTRING, TokenTypes.LET, TokenTypes.TYPENUMBER, TokenTypes.PRINTLN/* TODO, TokenTypes.TYPEBOOLEAN, TokenTypes.CONST, TokenTypes.READINPUT*/).content

        if (peek(TokenTypes.SEMICOLON) != null) return Declaration(variable, type, isConst)

        if (peek(TokenTypes.EQUAL) == null) throwParserError("Expected =")
        consume(TokenTypes.EQUAL)

        val expression: Expression = functionParser.parse()
        return Declaration(variable, type, isConst, expression)

    }

   private fun consumeDeclarationKeyword() : Boolean {
        consume(TokenTypes.LET)
       if (peek(TokenTypes.IDENTIFIER) == null) throwParserError("Expected identifier")
       return false
    }

    private fun throwParserError(text: String) {
        throw ParserException(text, current().range.startCol, current().range.startLine)
    }
}

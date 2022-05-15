package edu.austral.ingsis.g3.parser

import Print
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    override fun parse(): Print {

        consume(TokenTypes.PRINT)

        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(TokenTypes.LEFTPARENTHESIS)

        val content: Expression = expressionParser.parse()

        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(TokenTypes.RIGHTPARENTHESIS)

        return Print(content)
    }

    private fun throwParserException(value: String) {
        throw ParserException("Expected $value", current().range.startCol, current().range.startLine)
    }
}
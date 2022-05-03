package edu.austral.ingsis.g3.parser

import Print
import edu.austral.ingsis.g3.lexer.lexerEnums.Type
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class PrintParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Print> {

    private val expressionParser = FunctionParser(stream)

    override fun parse(): Print {

        consume(Type.PRINT)

        if (peek(Type.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(Type.LEFTPARENTHESIS)

        val content: Expression = expressionParser.parse()

        if (peek(Type.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(Type.RIGHTPARENTHESIS)

        return Print(content)
    }

    private fun throwParserException(value: String) {
        throw ParserException("Expected $value", current().range.startCol, current().range.startLine)
    }
}

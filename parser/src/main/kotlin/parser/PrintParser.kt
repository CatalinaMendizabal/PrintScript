package parser

import ast.expression.Expression
import ast.node.Print
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.exceptions.UnexpectedTokenException
import parser.function.AbstractFunctionParser
import parser.function.FunctionParserV1

class PrintParser(stream: TokenIterator) :
    TokenConsumer(stream),
    Parser<Print> {
    private val expressionParser: AbstractFunctionParser = FunctionParserV1(stream)

    override fun parse(): Print {
        consume(TokenTypes.PRINTLN)
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throw UnexpectedTokenException(
            "(",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.LEFTPARENTHESIS)
        val content: Expression = expressionParser.parse()
        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throw UnexpectedTokenException(
            ")",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.RIGHTPARENTHESIS)
        return Print(content)
    }
}

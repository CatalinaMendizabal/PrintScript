package parser

import ast.expression.Expression
import ast.expression.ReadInput
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.exceptions.UnexpectedTokenException
import parser.function.AbstractFunctionParser

class ReadInputParser(stream: TokenIterator?, var expressionParser: AbstractFunctionParser) :
    TokenConsumer(stream!!), Parser<ReadInput> {
    override fun parse(): ReadInput {
        consume(TokenTypes.READINPUT)
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throw UnexpectedTokenException(
            "(",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.LEFTPARENTHESIS)
        val message: Expression = expressionParser.parse()
        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throw UnexpectedTokenException(
            ")",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.RIGHTPARENTHESIS)
        return ReadInput(message)
    }
}

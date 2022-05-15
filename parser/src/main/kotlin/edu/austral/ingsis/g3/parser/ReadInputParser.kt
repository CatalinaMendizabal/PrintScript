package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import expression.Expression
import expression.ReadInput
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class ReadInputParser(stream: TokenIterator, expressionParser: FunctionParser) :
    TokenConsumer(stream), Parser<ReadInput> {
    var expressionParser: FunctionParser

    init {
        this.expressionParser = expressionParser
    }

    override fun parse(): ReadInput {
        consume(TokenTypes.READINPUT, "readInput")
        if (isLeftParenthesis()) throwException("(")
        consume(TokenTypes.LEFTPARENTHESIS, "(")
        val message: Expression = expressionParser.parse()
        if (isRightParenthesis()) throwException(")")
        consume(TokenTypes.RIGHTPARENTHESIS, ")")
        return ReadInput(message)
    }

    private fun throwException(value: String) {
        throw ParserException(value, current().range.startCol, current().range.startLine)
    }

    private fun isRightParenthesis() = peek(TokenTypes.RIGHTPARENTHESIS, ")") == null

    private fun isLeftParenthesis() = peek(TokenTypes.LEFTPARENTHESIS, "(") == null
}

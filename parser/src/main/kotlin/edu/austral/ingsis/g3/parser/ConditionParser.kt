package edu.austral.ingsis.g3.parser

import CodeBlock
import Condition
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator

class ConditionParser(stream: TokenIterator, private val statementParser: StatementParser) : TokenConsumer(stream), Parser<Condition> {
    private val expressionParser: AbstractFunctionParser = FunctionParserV1_1(stream)

    private lateinit var booleanValue: String

    override fun parse(): Condition {
        var elseCode = CodeBlock()

        consume(TokenTypes.IF).content
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(TokenTypes.LEFTPARENTHESIS)

        val condition: Expression = expressionParser.parse()
        // if (peekAny(TokenTypes.BOOLEAN, TokenTypes.IDENTIFIER) == null) throwParserException("boolean")
        // booleanValue = consumeAny(TokenTypes.BOOLEAN, TokenTypes.IDENTIFIER).content

        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(TokenTypes.RIGHTPARENTHESIS)
        val ifCode: CodeBlock = executeConditionCode()

        if (peek(TokenTypes.ELSE) != null) {
            consume(TokenTypes.ELSE).content
            elseCode = executeConditionCode()
        }
        return Condition(booleanValue, ifCode, elseCode, condition)
    }

    private fun ifStatement() {
       /* consume(TokenTypes.IF).content
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(TokenTypes.LEFTPARENTHESIS)

        val condition: Expression = expressionParser.parse()
        // if (peekAny(TokenTypes.BOOLEAN, TokenTypes.IDENTIFIER) == null) throwParserException("boolean")
        // booleanValue = consumeAny(TokenTypes.BOOLEAN, TokenTypes.IDENTIFIER).content

        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(TokenTypes.RIGHTPARENTHESIS)*/
    }

    private fun executeConditionCode(): CodeBlock {
        val codeBlock = CodeBlock()
        if (peek(TokenTypes.LEFTBRACKET) == null) throwParserException("{")
        consume(TokenTypes.LEFTBRACKET)

        while (peek(TokenTypes.RIGHTBRACKET) == null) {
            if (peek(TokenTypes.EOF) != null) {
                throw UnclosedCodeBlockException("Code block not closed with '}'")
            }
            codeBlock.addChild(statementParser.parse())
        }
        consume(TokenTypes.RIGHTBRACKET)
        return codeBlock
    }

    private fun throwParserException(boolean: String) {
        throw ParserException(
            "Expected an $boolean",
            current().range.startCol,
            current().range.startLine
        )
    }
}

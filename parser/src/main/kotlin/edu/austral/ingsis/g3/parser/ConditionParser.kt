package edu.austral.ingsis.g3.parser

import CodeBlock
import Condition
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator

class ConditionParser(stream: TokenIterator, private val statementParser: StatementParser) : TokenConsumer(stream), Parser<Condition> {
    private val expressionParser: AbstractFunctionParser = FunctionParserV1_1(stream)

    private lateinit var booleanValue: String

    override fun parse(): Condition {
        var elseCode = CodeBlock()

        ifStatement()
        val ifCode: CodeBlock = executeConditionCode()

        if (peek(TokenTypes.ELSE) != null) {
            consume(TokenTypes.ELSE).content
            elseCode = executeConditionCode()
        }
        return Condition(booleanValue, ifCode, elseCode)
    }

    private fun ifStatement() {
        consume(TokenTypes.IF).content
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(TokenTypes.LEFTPARENTHESIS)

        if (peek(TokenTypes.BOOLEAN) == null) throwParserException("boolean")
        booleanValue = consume(TokenTypes.BOOLEAN).content

        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(TokenTypes.RIGHTPARENTHESIS)
    }

    private fun executeConditionCode(): CodeBlock {
        val codeBlock = CodeBlock()
        if (peek(TokenTypes.LEFTBRACKET) == null) throwParserException("{")
        consume(TokenTypes.LEFTBRACKET)

        while (peek(TokenTypes.RIGHTBRACKET) == null) {
            if (peek(TokenTypes.EOF) != null) {
                throw UnclosedCodeBlockException("Code block not closed with '}'");
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

    private fun throwParserException(nextContent: Content<String>) {
        throw ParserException(
            nextContent.content,
            nextContent.token.range.startCol,
            nextContent.token.range.startLine
        )
    }
}


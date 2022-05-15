package edu.austral.ingsis.g3.parser

import CodeBlock
import Condition
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator

class ConditionParser(stream: TokenIterator) : TokenConsumer(stream), Parser<Condition> {
    private val declarationParser = DeclarationParser(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream)

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

        val nextContent: Content<String>? =
            peekAny(TokenTypes.LET, TokenTypes.PRINTLN, TokenTypes.TYPESTRING, TokenTypes.TYPENUMBER, TokenTypes.TYPEBOOLEAN, TokenTypes.CONST)

        if (nextContent != null) {
            when (nextContent.content) {
                "let", "const" -> {
                    codeBlock.addChild(declarationParser.parse())
                }
                "println" -> {
                    codeBlock.addChild(printParser.parse())
                }
                else -> throwParserException(nextContent)
            }
        } else assignmentParser.parse()
        if (peek(TokenTypes.EOF) == null) {
            consume(TokenTypes.SEMICOLON)
        }

        if (peek(TokenTypes.RIGHTBRACKET) == null) throwParserException("}")
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

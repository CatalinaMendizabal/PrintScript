package edu.austral.ingsis.g3.parser

import CodeBlock
import Condition
import edu.austral.ingsis.g3.lexer.lexerEnums.Type
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

        if (peek(Type.ELSE) != null) {
            consume(Type.ELSE).content
            elseCode = executeConditionCode()
        }
        return Condition(booleanValue, ifCode, elseCode)
    }

    private fun ifStatement() {
        consume(Type.IF).content
        if (peek(Type.LEFTPARENTHESIS) == null) throwParserException("(")
        consume(Type.LEFTPARENTHESIS)

        if (peek(Type.BOOLEAN) == null) throwParserException("boolean")
        booleanValue = consume(Type.BOOLEAN).content

        if (peek(Type.RIGHTPARENTHESIS) == null) throwParserException(")")
        consume(Type.RIGHTPARENTHESIS)
    }

    private fun executeConditionCode(): CodeBlock {
        val codeBlock = CodeBlock()
        if (peek(Type.LEFTBRACKET) == null) throwParserException("{")
        consume(Type.LEFTBRACKET)

        val nextContent: Content<String>? =
            peekAny(Type.LET, Type.PRINT, Type.STRINGTYPE, Type.NUMBERTYPE, Type.BOOLEANTYPE, Type.CONST)

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
        if (peek(Type.EOF) == null) {
            consume(Type.SEMICOLON)
        }

        if (peek(Type.RIGTHBRACKET) == null) throwParserException("}")
        consume(Type.RIGTHBRACKET)
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

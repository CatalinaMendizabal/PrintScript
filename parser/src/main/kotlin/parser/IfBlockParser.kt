package parser

import ast.expression.Expression
import ast.node.CodeBlock
import ast.node.IfBlock
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.exceptions.UnclosedCodeBlockException
import parser.exceptions.UnexpectedTokenException
import parser.function.AbstractFunctionParser
import parser.function.FunctionParserV2

class IfBlockParser(stream: TokenIterator, statementParser: StatementParser) :
    TokenConsumer(stream), Parser<IfBlock> {
    private val expressionParser: AbstractFunctionParser = FunctionParserV2(stream)
    private val statementParser: StatementParser

    init {
        this.statementParser = statementParser
    }

    override fun parse(): IfBlock {
        consume(TokenTypes.IF)
        if (peek(TokenTypes.LEFTPARENTHESIS) == null) throw UnexpectedTokenException(
            "(",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.LEFTPARENTHESIS)
        val condition: Expression = expressionParser.parse()
        if (peek(TokenTypes.RIGHTPARENTHESIS) == null) throw UnexpectedTokenException(
            ")",
            current().range.startCol,
            current().range.startLine
        )
        consume(TokenTypes.RIGHTPARENTHESIS)
        val ifBlock: CodeBlock = codeBlock
        var elseBlock = CodeBlock()
        if (peek(TokenTypes.ELSE) != null) {
            consume(TokenTypes.ELSE)
            elseBlock = codeBlock
        }
        return IfBlock(condition, ifBlock, elseBlock)
    }

    private val codeBlock: CodeBlock
        get() {
            if (peek(TokenTypes.LEFTBRACKET) == null) throw UnexpectedTokenException(
                "{",
                current().range.startCol,
                current().range.startLine
            )
            consume(TokenTypes.LEFTBRACKET)
            val result = CodeBlock()
            while (peek(TokenTypes.RIGHTBRACKET) == null) {
                if (peek(TokenTypes.EOF) != null) {
                    throw UnclosedCodeBlockException("Code block not closed with '}'")
                }
                result.addChild(statementParser.parse())
            }
            consume(TokenTypes.RIGHTBRACKET)
            return result
        }
}

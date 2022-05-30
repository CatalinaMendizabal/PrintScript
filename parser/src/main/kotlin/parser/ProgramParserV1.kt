package parser

import ast.node.CodeBlock
import ast.node.Node
import enums.TokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.declaration.DeclarationParserV1
import parser.exceptions.UnexpectedKeywordException
import parser.exceptions.UnexpectedTokenException
import parser.function.FunctionParserV1

class ProgramParserV1(stream: TokenIterator) :
    TokenConsumer(stream),
    Parser<Node> {
    private val declarationParserV10 = DeclarationParserV1(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(
        stream,
        FunctionParserV1(stream)
    )

    override fun parse(): Node {
        val program = CodeBlock()
        var next: Content<String>?
        while (peek(TokenTypes.EOF) == null) {
            next = peekAny(TokenTypes.LET, TokenTypes.PRINTLN)
            if (next != null) {
                when (next.content) {
                    "let" -> {
                        program.addChild(declarationParserV10.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    else -> throw UnexpectedKeywordException(
                        next.content,
                        next.token.range.startCol,
                        next.token.range.startLine
                    )
                }
            } else {
                program.addChild(assignmentParser.parse())
            }
            if (peek(TokenTypes.SEMICOLON) == null) throw UnexpectedTokenException(
                ";",
                current().range.startCol,
                current().range.startLine
            )
            consume(TokenTypes.SEMICOLON)
        }
        return program
    }
}

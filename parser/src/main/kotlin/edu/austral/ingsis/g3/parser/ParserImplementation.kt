package edu.austral.ingsis.g3.parser

import CodeBlock
import node.Node
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ParserImplementation(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParser = DeclarationParser(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream)
    private val conditionParser = ConditionParser(stream)

    override fun parse(): Node {
        val program = CodeBlock()
        var nextContent: Content<String>?

        while (isNotAtEndOfFile()) {
            nextContent = peekAny(TokenTypes.LET, TokenTypes.PRINT, TokenTypes.STRINGTYPE, TokenTypes.NUMBERTYPE, TokenTypes.BOOLEANTYPE, TokenTypes.CONST, TokenTypes.IF)
            // nextContent = peekAny(Type.LET, Type.PRINT, Type.STRINGTYPE, Type.NUMBERTYPE)

            if (nextContent != null) {
                when (nextContent.content) {
                    "let", "const" -> {
                        program.addChild(declarationParser.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    "if" -> {
                        program.addChild(conditionParser.parse())
                    }
                    else -> throwParserException(nextContent)
                }
            } else program.addChild(assignmentParser.parse())

            consume(TokenTypes.SEMICOLON)
        }
        return program
    }

    private fun isNotAtEndOfFile() = peek(TokenTypes.EOF) == null

    private fun throwParserException(nextContent: Content<String>) {
        throw ParserException(
            nextContent.content,
            nextContent.token.range.startCol,
            nextContent.token.range.startLine
        )
    }
}
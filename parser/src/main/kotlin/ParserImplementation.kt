import PrintScript.lexer.lexerEnums.Types
import node.Node
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ParserImplementation(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParser = DeclarationParser(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream)

    override fun parse(): Node {
        val program = CodeBlock()
        var nextContent: Content<String>?

        while (isNotAtEndOfFile()) {
            // TODO VERSION 1.1    nextContent = peekAny(Types.LET, Types.PRINT, Types.STRINGTYPE, Types.NUMBERTYPE, Types.BOOLEANTYPE, Types.CONST)
            nextContent = peekAny(Types.LET, Types.PRINT, Types.STRINGTYPE, Types.NUMBERTYPE)

            if (nextContent != null) {
                when (nextContent.content) {
                    "let", "const" -> {
                        program.addChild(declarationParser.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    else -> throwParserException(nextContent)
                }
            } else program.addChild(assignmentParser.parse())

            consume(Types.SEMICOLON)
        }
        return program
    }

    private fun isNotAtEndOfFile() = peek(Types.EOF) == null

    private fun throwParserException(nextContent: Content<String>) {
        throw ParserException(
            nextContent.content,
            nextContent.token.range.startCol,
            nextContent.token.range.startLine
        )
    }
}

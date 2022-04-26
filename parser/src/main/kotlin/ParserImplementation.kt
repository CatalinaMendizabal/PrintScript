import lexerEnums.Type
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
            nextContent = peekAny(Type.LET, Type.PRINT, Type.STRINGTYPE, Type.NUMBERTYPE, Type.BOOLEANTYPE, Type.CONST)
            //nextContent = peekAny(Type.LET, Type.PRINT, Type.STRINGTYPE, Type.NUMBERTYPE)

            if (nextContent != null) {
                when (nextContent.content) {
                    "let", "const" -> {
                        program.addChild(declarationParser.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    "boolean" -> {
                        program.addChild(conditionParser.parse())
                    }
                    else -> throwParserException(nextContent)
                }
            } else program.addChild(assignmentParser.parse())

            consume(Type.SEMICOLON)
        }
        return program
    }

    private fun isNotAtEndOfFile() = peek(Type.EOF) == null

    private fun throwParserException(nextContent: Content<String>) {
        throw ParserException(
            nextContent.content,
            nextContent.token.range.startCol,
            nextContent.token.range.startLine
        )
    }
}

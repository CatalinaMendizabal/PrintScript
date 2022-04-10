import PrintScript.lexer.lexerEnums.Types
import PrintScript.lexer.lexerEnums.Types.*
import node.Node
import org.austral.ingsis.printscript.common.CoreTokenTypes
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.Content
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

class ParserImplementation(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Node> {
    private val declarationParser = DeclarationParser(stream)
    private val printParser = PrintParser(stream)
    private val assignmentParser = AssignmentParser(stream)

    @Throws( ParserException::class)
    override fun parse(): Node {
        val program = CodeBlock()
        var next: Content<String>?
        while (peek(CoreTokenTypes.EOF) == null) {
            next = peekAny(LET, NUMBERTYPE, STRINGTYPE, PRINT)
            if (next != null) {
                when (next.content) {
                    "let" -> {
                        program.addChild(declarationParser.parse())
                    }
                    "println" -> {
                        program.addChild(printParser.parse())
                    }
                    else -> throw ParserException(
                        next.content,
                        next.token.range.startCol,
                        next.token.range.startLine
                    )
                }
            } else {
                program.addChild(assignmentParser.parse())
            }
            consume(Types.SEMICOLON, ";")
        }
        return program
    }
}

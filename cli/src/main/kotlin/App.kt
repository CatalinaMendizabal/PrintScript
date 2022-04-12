import PrintScript.lexer.Lexer
import PrintScript.lexer.LexerImplementation
import PrintScript.lexer.inputContent.StringContent
import com.github.ajalt.clikt.core.CliktCommand
import node.Node
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import java.io.File

class CLI : CliktCommand() {

    private val src = "let a: Number = 0;"
    var file = File("/Users/catamendizabal/projects.ing-sis/PrintScript/ideas")

    override fun run() {

        try {
            println("Lexing...")
            val tokens = executeLexerTask()
            println("Parsing...")
            val root = executeParserTask(tokens)
            println(root)
            //  TODO interpreter
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
    }

    private fun executeLexerTask(): List<Token> {
        val lexer: Lexer = LexerImplementation()
        return lexer.lex(StringContent(src))
    }

    private fun executeParserTask(tokens: List<Token>): Node {
        val parser: Parser<Node> = ParserImplementation(TokenIterator.create(src, tokens))
        return parser.parse()
    }
}

fun main(args: Array<String>) = CLI().main(args)

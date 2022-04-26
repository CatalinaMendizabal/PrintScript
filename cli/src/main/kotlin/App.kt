import PrintScript.lexer.Lexer
import PrintScript.lexer.RegexLexer
import PrintScript.lexer.inputContent.FileContent
import com.github.ajalt.clikt.core.CliktCommand
import node.Node
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import java.io.File

class CLI : CliktCommand() {

    private val src = "let a: String = \"Hola\";" + "println(a);" + "let b: Number = 1;"
    var file = File("ideas")

    override fun run() {

        try {
            println("Lexing...")
            val tokens = executeLexerTask("1.0")
            println("Parsing...")
            val root = executeParserTask(tokens)
            println("Interpreting...")
            val consoleInt = executeInterpreterTask(root)
            println(consoleInt.readLine())
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
    }

    private fun executeLexerTask(version: String): List<Token> {
        val lexer: Lexer = RegexLexer(version)
        return lexer.lex(FileContent(file))
    }

    private fun executeParserTask(tokens: List<Token>): Node {
        val parser: Parser<Node> = ParserImplementation(TokenIterator.create(FileContent(file).convertContent(), tokens))
        return parser.parse()
    }

    private fun executeInterpreterTask(ast: Node): InterpreterConsole {
        val interpreter = Interpreter()
        return interpreter.interpret(ast)
    }
}

fun main(args: Array<String>) = CLI().main(args)

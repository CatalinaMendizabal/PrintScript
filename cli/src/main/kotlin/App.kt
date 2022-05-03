import PrintScript.lexer.Lexer
import PrintScript.lexer.RegexLexer
import PrintScript.lexer.inputContent.FileContent
import com.github.ajalt.clikt.core.CliktCommand
import edu.austral.ingsis.g3.interpreter.Interpreter
import edu.austral.ingsis.g3.interpreter.InterpreterConsole
import java.io.File
import node.Node
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import java.io.File
import java.util.Scanner

class CLI : CliktCommand() {

    var file = File("")

    override fun run() {
        val fileName: String = askForFile()
        file = getFile(fileName)
        val version: String = askForVersion()
        if ((version != "1.0") && (version != "2.0")) {
            println("Version not supported")
            println("Try again")
            println()
            run()
        }

        try {
            println("Lexing...")
            val tokens = executeLexerTask(version)
            println("Parsing...")
            val root = executeParserTask(tokens)
            println("Interpreting...")
            val consoleInt = executeInterpreterTask(root)
            println(consoleInt.readLine())
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
    }

    private fun askForFile(): String {
        println("Insert file name: ")
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }

    private fun askForVersion(): String {
        println("Select version: \n* 1.0\n* 2.0")
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }

    private fun getFile(fileName: String): File {
        val file = File(fileName)
        if (!file.exists()) {
            throw Exception("File not found")
        }
        return file
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

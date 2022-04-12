import PrintScript.lexer.Lexer
import PrintScript.lexer.LexerImplementation
import PrintScript.lexer.inputContent.StringContent
import com.github.ajalt.clikt.core.CliktCommand
import node.Node
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import java.io.File
import java.io.IOException
import java.util.*


class CLI : CliktCommand() {

    override fun run() {
        val src = "let a: Number = 0;"
        var file = File("")
        var isValid = false

        while (!isValid) {
            try {
                val filename: String = askForString()
                file = getFile(filename)
                println("File found: " + file.absolutePath)
                isValid = true
            } catch (e: IOException) {
                println("Error: " + e.message)
            }
        }

        try {
            println("Lexing...")
            val tokens = executeLexerTask(src)
            println(tokens)
            println("Parsing...")
            val root = executeParserTask(src, tokens)
            println(root)
          //  TODO interpreter
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
      //  val result = interpreter.interpret(ast)
        // print(result)
    }

    private fun executeLexerTask(aContentProvider: String): List<Token> {
        val lexer: Lexer = LexerImplementation()
        return lexer.lex(StringContent(aContentProvider))
    }

    private fun executeParserTask(aContentProvider: String, tokens: List<Token>): Node {
        val parser: Parser<Node> = ParserImplementation(TokenIterator.create(aContentProvider, tokens))
        return parser.parse()
    }

    private fun askForString(): String {
        println("Insert file name: ")
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }

    private fun getFile(filename: String): File {
        val file = File(filename)
        return if (!file.exists()) throw IOException("File not found") else file
    }

}

fun main(args: Array<String>) = CLI().main(args)

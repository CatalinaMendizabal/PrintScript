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
        val src = "let a: Number = 0;\n" + "println(a);\n" + "a = 20;\n" + "println(a);\n" +
                "a = a + 50;\n" + "println(a);\n" + "let b: String = \"Hello World\";\n" + "print(b);"

        var mode = ""
        var file: File = File("")
        var isValid = false

        while (!isValid) {
            try {
                val filename: String = askForString("Insert file name: ")
                file = getFile(filename)
                println("File found: " + file.absolutePath)
                mode = askForString("Insert execution mode (interpretation or validation): ")
                isValid = true
            } catch (e: IOException) {
                println("Error: " + e.message)
            }
        }


        try {
            println("Lexing...")
            val tokens = executeLexerTask(src)
            println("Parsing...")
            val root = executeParserTask(src, tokens)
            /*if (mode.equals(Mode.Interpretation.getMode())) executeInterpretationTask(timer, root) else if (mode.equals(
                    Mode.Validation.getMode()
                )
            ) executeValidationTask(timer, root)*/
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

    private fun askForString(question: String): String {
        println(question)
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }

    private fun getFile(filename: String): File {
        val file = File(filename)
        return if (!file.exists()) throw IOException("File not found") else file
    }

    /*private fun executeInterpretationTask( root: Node) {
        val interpreter = Interpreter
        TaskProgressPrinter.printStart(Task.Interpretation)
        timer.start()
        val writer: Writer = interpreter.run(root)
        System.out.println(writer.read())
        timer.stop()
        TaskProgressPrinter.printEnd(Task.Interpretation, timer)
    }*/

    /*@Throws(NodeException::class)
    private fun executeValidationTask(timer: Timer, root: Node) {
        val interpreter = Interpreter
        TaskProgressPrinter.printStart(Task.Validation)
        timer.start()
        interpreter.run(root)
        timer.stop()
        TaskProgressPrinter.printEnd(Task.Validation, timer)
    }*/
}

fun main(args: Array<String>) = CLI().main(args)

package edu.austral.ingisis.g3.cli

import PrintScript.lexer.inputContent.FileContent
import com.github.ajalt.clikt.core.CliktCommand
import enums.PrintScriptVersion
import impl.DefaultRegexLexer
import impl.MatchProvider
import java.io.File
import java.util.Scanner
import org.austral.ingsis.printscript.common.Token

class CLI : CliktCommand() {

    var file = File("")
    private val printScript = PrintScript(File("ideas"), "1.1")

    override fun run() {
        printScript.run()
       /* val fileName: String = askForFile()
        file = getFile(fileName)
        val version: String = askForVersion()
        if (Version.getVariableType(version) == null) {
            println("Version not supported")
            println("Try again")
            println()
            run()
        }
        val selectedVersion: Version = Version.getVariableType(version)!!
        try {
            println("Lexing...")
            val tokens = executeLexerTask(selectedVersion)
            println("Parsing...")
            val root = executeParserTask(tokens, version)
            println("Interpreting...")
            val consoleInt = executeInterpreterTask(root)
            println(consoleInt.readLine())
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }*/
    }

    private fun askForFile(): String {
        println("Insert file name: ")
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }

    private fun askForVersion(): String {
        println("Select version: \n* 1.0\n* 1.1")
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

    private fun executeLexerTask(version: PrintScriptVersion, src: File): List<Token> {
        val matchers = MatchProvider.getMatchers(version)
        val lexer = DefaultRegexLexer(matchers)
        return lexer.lex(FileContent(src))

      /*  val lexer: Lexer = DefaultRegexLexer(MatchProvider.getMatchers(version), version)
        return lexer.lex(FileContent(file))*/
    }

   /* private fun executeParserTask(tokens: List<Token>, version: String): Node {
        val parser: Parser<Node> =
            if (version == "1.0") ParserImplementatonV1(TokenIterator.create(FileContent(file).convertContent(), tokens))
            else ParserImplementatonV2(TokenIterator.create(FileContent(file).convertContent(), tokens))
        return parser.parse()
    }

    private fun executeInterpreterTask(ast: Node): InterpreterConsole {
        val interpreter = Interpreter()
        return interpreter.interpret(ast)
    }*/
}

fun main(args: Array<String>) = CLI().main(args)

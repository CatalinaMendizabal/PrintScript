package edu.austral.ingisis.g3.cli

import PrintScript.lexer.inputContent.FileContent
import edu.austral.ingsis.g3.interpreter.Interpreter
import edu.austral.ingsis.g3.interpreter.InterpreterConsole
import edu.austral.ingsis.g3.lexer.DefaultRegexLexer
import edu.austral.ingsis.g3.lexer.Lexer
import edu.austral.ingsis.g3.lexer.lexerEnums.Version
import edu.austral.ingsis.g3.lexer.matcher.MatchProvider
import edu.austral.ingsis.g3.parser.Parser
import edu.austral.ingsis.g3.parser.ParserImplementation
import java.io.File
import node.Node
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator

class PrintScript(private val file: File, private val version: String) {

    fun run() {
        val selectedVersion = Version.getVariableType(version)!!
        try {
            println("Lexing...")
            val tokens = executeLexerTask(selectedVersion)
            println("Parsing...")
            val root = executeParserTask(tokens)
            println("Interpreting...")
            val consoleInt = executeInterpreterTask(root)
            println(consoleInt.readLine())
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
    }

    private fun executeLexerTask(version: Version): List<Token> {
        val lexer: Lexer = DefaultRegexLexer(MatchProvider.getMatchers(version))
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

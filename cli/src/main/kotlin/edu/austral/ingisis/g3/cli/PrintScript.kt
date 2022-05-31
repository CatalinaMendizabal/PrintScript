package edu.austral.ingisis.g3.cli

import PrintScript.lexer.inputContent.FileContent
import ast.node.Node
import enums.PrintScriptVersion
import impl.DefaultRegexLexer
import impl.MatchProvider
import interpreter.DefaultInputProvider
import interpreter.DefaultPrintEmitter
import interpreter.Interpreter
import interpreter.InterpreterConsole
import java.io.File
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.Parser
import parser.ProgramParserV1
import parser.ProgramParserV2

class PrintScript(private val file: File, private val version: String) {
    private val selectedVersion = PrintScriptVersion.V1_1.toString()
    fun run() {
        try {
            println("Lexing...")
            val tokens = executeLexerTask()
            println("Parsing...")
            val root = executeParserTask(tokens)
            println("Interpreting...")
            executeInterpreterTask(root)
        } catch (e: Throwable) {
            println("Error: " + e.message)
        }
    }

    private fun executeLexerTask(): List<Token> {
        val matchers = MatchProvider.getMatchers(PrintScriptVersion.V1_1)
        val lexer = DefaultRegexLexer(matchers)
        return lexer.lex(FileContent(file))
    }

    private fun executeParserTask(tokens: List<Token>): Node {
        val parser: Parser<Node> = if (selectedVersion == PrintScriptVersion.V1_0.toString())
            ProgramParserV1(TokenIterator.create(FileContent(file).convertContent(), tokens))
        else ProgramParserV2(TokenIterator.create(FileContent(file).convertContent(), tokens))
        return parser.parse()
    }

    private fun executeInterpreterTask(ast: Node) {
        val interpreter = Interpreter()
        if (selectedVersion == PrintScriptVersion.V1_0.toString()) {
            val interpreterConsole: InterpreterConsole = interpreter.run(ast)
            println(interpreterConsole.read())
        } else interpreter.run(ast, DefaultInputProvider(), DefaultPrintEmitter())
    }
}

package edu.austral.ingisis.g3.cli

import PrintScript.lexer.inputContent.FileContent
import ast.node.Node
import enums.PrintScriptVersion
import impl.DefaultRegexLexer
import impl.MatchProvider
import java.io.File
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.Parser
import parser.ProgramParserV1
import parser.ProgramParserV2

class PrintScriptTck(private val file: File, private val version: String) {

   fun run(): Node {
       val tokens = executeLexerTask()
       return executeParserTask(tokens)
   }

    private fun executeLexerTask(): List<Token> {
        val matchers = MatchProvider.getMatchers(PrintScriptVersion.V1_0)
        val lexer = DefaultRegexLexer(matchers)
        return lexer.lex(FileContent(file))
    }

    private fun executeParserTask(tokens: List<Token>): Node {
        val parser: Parser<Node> = if (version == PrintScriptVersion.V1_0.toString())
            ProgramParserV1(TokenIterator.create(FileContent(file).convertContent(), tokens))
        else ProgramParserV2(TokenIterator.create(FileContent(file).convertContent(), tokens))
        return parser.parse()
    }

}

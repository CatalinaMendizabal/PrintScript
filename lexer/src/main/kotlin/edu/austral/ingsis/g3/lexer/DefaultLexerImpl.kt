package edu.austral.ingsis.g3.lexer

import PrintScript.lexer.inputContent.Content
import edu.austral.ingsis.g3.lexer.exceptions.LexerException
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.lexer.matcher.LexerMatcher
import edu.austral.ingsis.g3.lexer.matcher.LexerMatcherImpl
import java.util.regex.Matcher
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

class DefaultLexerImpl(private var matchers: HashMap<TokenTypes, LexerMatcher>) : Lexer {

    override fun lex(input: Content): List<Token> {
        val matcher = LexerMatcherImpl(matchers.values.toList()).getMatcher(input.convertContent())
        return getTokens(matcher)
    }

    private fun getTokens(matcher: Matcher): List<Token> {
        val tokens: MutableList<Token> = emptyList<Token>().toMutableList()
        var line = 0
        var position = 0
        var column = 0

        // Find matches and add to token list
        while (matcher.find()) {
            val match = matcher.group()
            println(match)

            // Check matches with tokens
            val matched: Token = matchers.keys
                .filter { matcher.group(it.type) != null }
                .map {
                    println(it)
                    val endColumn = if (it == TokenTypes.EOL) 0 else column + match.length
                    val endLine = if (it == TokenTypes.EOL) line + 1 else line
                    val endPos = position + match.length
                    val range = LexicalRange(column, line, endColumn, endLine)

                    if (it == TokenTypes.NOMATCH) throw LexerException("Lexical Error", column, line)
                    val token = Token(it, position, endPos, range)

                    column = endColumn
                    line = endLine
                    position = endPos

                    token
                }
                .first()

            tokens.add(matched)
        }

        // Add EOF token
        tokens.add(Token(TokenTypes.EOF, position, position, LexicalRange(column, line, column, line)))

        return tokens
    }
}

package edu.austral.ingsis.g3.lexer

import PrintScript.lexer.inputContent.Content
import edu.austral.ingsis.g3.lexer.exceptions.LexerException
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.lexer.matcher.LexerMatcher
import edu.austral.ingsis.g3.lexer.matcher.DefaultMatcherImpl
import java.util.EnumMap
import java.util.regex.Matcher
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

class DefaultRegexLexer(private var matchers: EnumMap<TokenTypes, LexerMatcher>) : Lexer {

    override fun lex(input: Content): List<Token> {
        val matcher = DefaultMatcherImpl(matchers.values.toList()).getMatcher(input.convertContent())
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

            // Check matches with tokens
            val matched: Token = matchers.keys
                .filter { matcher.group(it.type) != null }
                .map {
                    val endColumn = if (it == TokenTypes.EOL) 0 else column + match.length
                    val endLine = if (it == TokenTypes.EOL) line + 1 else line
                    val endPos = position + match.length
                    val range = LexicalRange(column, line, endColumn, endLine)

                    if (it == TokenTypes.ERROR) throw LexerException("Error", line, column)

                    val token = Token(it, position, endPos, range)

                    column = endColumn
                    line = endLine
                    position = endPos

                    token
                }.first()

            tokens += matched
        }

        tokens += Token(TokenTypes.EOF, position, position, LexicalRange(column, line, column, line))

        return tokens
    }
}
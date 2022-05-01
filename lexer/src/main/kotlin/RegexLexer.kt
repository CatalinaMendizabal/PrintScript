package PrintScript.lexer

import LexerException
import PrintScript.lexer.inputContent.Content
import VersionException
import java.util.Arrays.stream
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import lexerEnums.Type



import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.common.TokenType

class RegexLexer(version: String) : Lexer {

    private val patterns = HashMap<TokenType, String>()
    private var line = 0
    private var column = 0
    private var currentPos = 0
    private val version = version

    init {
        for (i in Type.values()) {
            patterns[i] = i.type
        }
    }

    override fun lex(input: Content): List<Token> {
        val tokens = ArrayList<Token>()
        val matcher: Matcher = generateMatcher(input.convertContent())

        while (matcher.find()) {
            val length = matcher.group().length

            if (checkNextRow(matcher)) {
                line++
                column = 0
                currentPos += length
                continue
            }

            if (checkWhiteSpace(matcher)) {
                column += length
                currentPos += length
                continue
            }

            val matched: Token = generateToken(matcher, length)

            tokens.add(matched)
            column += length
            currentPos += length
        }
        return tokens
    }

    private fun checkNextRow(matcher: Matcher) = matcher.group().equals(Type.EOL.type)
    private fun checkWhiteSpace(matcher: Matcher) = matcher.group().equals(Type.WHITESPACE.type)

    private fun generateToken(matcher: Matcher, length: Int): Token {
        val matched: Token = patterns.keys.stream().filter { type ->
            matcher.group(type.toString()) != null
        }
            .findFirst()
            .map { element ->
                if (element == Type.ERROR) {
                    throw LexerException("Lexical Error", column, line)
                }
                Token(
                    element,
                    currentPos,
                    currentPos + length,
                    LexicalRange(column, line, column + length, line)
                )
            }
            .map { element -> checkVersion(element) }
            .orElseThrow { throw LexerException("Invalid Token", column, line) }

        return matched
    }

    private fun checkVersion(element: Token): Token {
        if (element.type == Type.CONST && version == "1.0") throw VersionException("CONST")
        if ((element.type == Type.BOOLEANTYPE || element.type == Type.BOOLEAN) && version == "1.0") throw VersionException("BOOLEAN")
        if ((element.type == Type.IF || element.type == Type.ELSE) && version == "1.0") throw VersionException("IF/ELSE")
        return element
    }

    private fun generateMatcher(line: String): Matcher {
        return Pattern.compile(
            stream(Type.values())
                .map { key -> String.format("(?<%s>%s)", key.name, key.type) }
                .collect(Collectors.joining("|"))

        ).matcher(line)
    }
}

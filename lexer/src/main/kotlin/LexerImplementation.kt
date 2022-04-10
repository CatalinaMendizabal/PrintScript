package PrintScript.lexer

import LexerException
import PrintScript.lexer.inputContent.Content
import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token
import java.util.Arrays.stream
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors

class LexerImplementation() : Lexer {

    private val patterns = HashMap<Types, String>()
    private var line = 0
    private var column = 0
    private var currentPos = 0

    init {
        for (i in Types.values()) {
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
                continue
            }
            val matched: Token = generateToken(matcher, length)

            tokens.add(matched)
            column += length
            currentPos += length
        }

        return tokens
    }

    private fun checkNextRow(matcher: Matcher) = matcher.group().equals(Types.EOL.type)

    private fun generateToken(matcher: Matcher, length: Int): Token {
        val matched: Token = patterns.keys.stream().filter { type ->
            matcher.group(type.toString()) != null
        }
            .findFirst().map { element ->
                Token(
                    element,
                    currentPos,
                    currentPos + length,
                    LexicalRange(column, line, column + length, line)
                )
            }
            .orElseThrow { throw LexerException("Invalid Token", column, line) }

        return matched
    }

    private fun generateMatcher(line: String): Matcher {
        return Pattern.compile(
            stream(Types.values())
                .map { key -> String.format("(?<%s>%s)", key.name, key.type) }
                .collect(Collectors.joining("|"))
        )
            .matcher(line)
    }
}

package edu.austral.ingsis.g3.lexer.matcher

import java.util.regex.Matcher
import java.util.regex.Pattern
import org.austral.ingsis.printscript.common.TokenType

class LexerMatcherImpl : LexerMatcher {

    private val pattern: Pattern
    private val regex: String?

    constructor(type: TokenType, regex: String) {
        pattern = Pattern.compile(String.format("(?<%s>%s)", type, regex))
        this.regex = regex
    }

    constructor(matchers: List<LexerMatcher>) {
        pattern = Pattern.compile(
            matchers.joinToString("|") { it.getPattern().toString() }
        )
        this.regex = null
    }

    constructor(type: TokenType, regex: String, notMatchers: List<LexerMatcher>) {
        pattern = Pattern.compile(
            String.format(
                "(?<%s>%s)",
                type,
                String.format(
                    "(?!(%s)\\b)\\b%s",
                    notMatchers.filter { it.getRegex() != null }.joinToString("|") { it.getRegex()!! },
                    regex
                )
            )
        )
        this.regex = null
    }

    override fun getPattern(): Pattern = pattern

    override fun getRegex(): String? = regex

    override fun getMatcher(input: String): Matcher = pattern.matcher(input)
}

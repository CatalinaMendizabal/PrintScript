package impl

import enums.PrintScriptVersion
import enums.TokenTypes
import interfaces.LexerMatcher
import java.util.*

class MatchProvider {

    companion object {

        fun getMatchers(version: PrintScriptVersion): EnumMap<TokenTypes, LexerMatcher> {
            val matchers: EnumMap<TokenTypes, LexerMatcher> = EnumMap(TokenTypes::class.java)

            matchers[TokenTypes.LET] = DefaultLexerMatcher(TokenTypes.LET, "let")
            matchers[TokenTypes.PRINTLN] = DefaultLexerMatcher(TokenTypes.PRINTLN, "println")

            matchers[TokenTypes.TYPESTRING] = DefaultLexerMatcher(TokenTypes.TYPESTRING, "string")
            matchers[TokenTypes.TYPENUMBER] = DefaultLexerMatcher(TokenTypes.TYPENUMBER, "number")

            matchers[TokenTypes.SUM] = DefaultLexerMatcher(TokenTypes.SUM, "[+]")
            matchers[TokenTypes.SUBSTRACT] = DefaultLexerMatcher(TokenTypes.SUBSTRACT, "[-]")
            matchers[TokenTypes.MULTIPLY] = DefaultLexerMatcher(TokenTypes.MULTIPLY, "[*]")
            matchers[TokenTypes.DIVIDE] = DefaultLexerMatcher(TokenTypes.DIVIDE, "[/]")
            matchers[TokenTypes.EQUAL] = DefaultLexerMatcher(TokenTypes.EQUAL, "[=]")
            matchers[TokenTypes.LEFTPARENTHESIS] = DefaultLexerMatcher(TokenTypes.LEFTPARENTHESIS, "[(]")
            matchers[TokenTypes.RIGHTPARENTHESIS] = DefaultLexerMatcher(TokenTypes.RIGHTPARENTHESIS, "[)]")

            matchers[TokenTypes.NUMBER] = DefaultLexerMatcher(TokenTypes.NUMBER, "-?\\d+\\.?\\d*")
            matchers[TokenTypes.STRING] = DefaultLexerMatcher(TokenTypes.STRING, "\".*\" |\'.*\' |\".*\"|'.*'")

            matchers[TokenTypes.WHITESPACE] = DefaultLexerMatcher(TokenTypes.WHITESPACE, " ")
            matchers[TokenTypes.COLON] = DefaultLexerMatcher(TokenTypes.COLON, "[:]")
            matchers[TokenTypes.SEMICOLON] = DefaultLexerMatcher(TokenTypes.SEMICOLON, "[;]")
            matchers[TokenTypes.EOL] = DefaultLexerMatcher(TokenTypes.EOL, "\n")

            matchers[TokenTypes.ERROR] = DefaultLexerMatcher(TokenTypes.ERROR, ".+")

            return when (version) {
                PrintScriptVersion.V1_0 -> {

                    matchers[TokenTypes.IDENTIFIER] = DefaultLexerMatcher(
                        TokenTypes.IDENTIFIER, "[_a-zA-Z][_a-zA-Z0-9]*",
                        listOf(
                            matchers[TokenTypes.LET]!!,
                            matchers[TokenTypes.PRINTLN]!!,
                            matchers[TokenTypes.TYPESTRING]!!,
                            matchers[TokenTypes.TYPENUMBER]!!
                        )
                    )
                    matchers
                }

                PrintScriptVersion.V1_1 -> {

                    matchers[TokenTypes.CONST] = DefaultLexerMatcher(TokenTypes.CONST, "const")
                    matchers[TokenTypes.READINPUT] = DefaultLexerMatcher(TokenTypes.READINPUT, "readInput")
                    matchers[TokenTypes.IF] = DefaultLexerMatcher(TokenTypes.IF, "if")
                    matchers[TokenTypes.ELSE] = DefaultLexerMatcher(TokenTypes.ELSE, "else")

                    matchers[TokenTypes.TYPEBOOLEAN] = DefaultLexerMatcher(TokenTypes.TYPEBOOLEAN, "boolean")

                    matchers[TokenTypes.LEFTBRACKET] = DefaultLexerMatcher(TokenTypes.LEFTBRACKET, "[{]")
                    matchers[TokenTypes.RIGHTBRACKET] = DefaultLexerMatcher(TokenTypes.RIGHTBRACKET, "[}]")

                    matchers[TokenTypes.BOOLEAN] = DefaultLexerMatcher(TokenTypes.BOOLEAN, "true|false")

                    matchers[TokenTypes.IDENTIFIER] = DefaultLexerMatcher(
                        TokenTypes.IDENTIFIER, "[_a-zA-Z][_a-zA-Z0-9]*",
                        listOf(
                            matchers[TokenTypes.LET]!!,
                            matchers[TokenTypes.CONST]!!,
                            matchers[TokenTypes.PRINTLN]!!,
                            matchers[TokenTypes.READINPUT]!!,
                            matchers[TokenTypes.IF]!!,
                            matchers[TokenTypes.ELSE]!!,
                            matchers[TokenTypes.TYPESTRING]!!,
                            matchers[TokenTypes.TYPENUMBER]!!,
                            matchers[TokenTypes.TYPEBOOLEAN]!!,
                            matchers[TokenTypes.BOOLEAN]!!
                        )
                    )
                    matchers
                }
            }
        }
    }
}

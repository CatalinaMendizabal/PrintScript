package edu.austral.ingsis.g3.lexer.matcher

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.lexer.lexerEnums.Version
import java.util.EnumMap

class MatchProvider {

    companion object {

        fun getMatchers(version: Version): EnumMap<TokenTypes, LexerMatcher> {
            val matchers: EnumMap<TokenTypes, LexerMatcher> = EnumMap(TokenTypes::class.java)

            matchers[TokenTypes.LET] = DefaultMatcherImpl(TokenTypes.LET, "let")
            matchers[TokenTypes.PRINTLN] = DefaultMatcherImpl(TokenTypes.PRINTLN, "println")

            matchers[TokenTypes.TYPESTRING] = DefaultMatcherImpl(TokenTypes.TYPESTRING, "string")
            matchers[TokenTypes.TYPENUMBER] = DefaultMatcherImpl(TokenTypes.TYPENUMBER, "number")

            matchers[TokenTypes.SUM] = DefaultMatcherImpl(TokenTypes.SUM, "[+]")
            matchers[TokenTypes.SUBSTRACT] = DefaultMatcherImpl(TokenTypes.SUBSTRACT, "[-]")
            matchers[TokenTypes.MULTIPLY] = DefaultMatcherImpl(TokenTypes.MULTIPLY, "[*]")
            matchers[TokenTypes.DIVIDE] = DefaultMatcherImpl(TokenTypes.DIVIDE, "[/]")
            matchers[TokenTypes.EQUAL] = DefaultMatcherImpl(TokenTypes.EQUAL, "[=]")
            matchers[TokenTypes.LEFTPARENTHESIS] = DefaultMatcherImpl(TokenTypes.LEFTPARENTHESIS, "[(]")
            matchers[TokenTypes.RIGHTPARENTHESIS] = DefaultMatcherImpl(TokenTypes.RIGHTPARENTHESIS, "[)]")

            matchers[TokenTypes.NUMBER] = DefaultMatcherImpl(TokenTypes.NUMBER, "-?\\d+\\.?\\d*")
            matchers[TokenTypes.STRING] = DefaultMatcherImpl(TokenTypes.STRING, "\".*\"|\'.*\'")

            matchers[TokenTypes.WHITESPACE] = DefaultMatcherImpl(TokenTypes.WHITESPACE, " ")
            matchers[TokenTypes.COLON] = DefaultMatcherImpl(TokenTypes.COLON, "[:]")
            matchers[TokenTypes.SEMICOLON] = DefaultMatcherImpl(TokenTypes.SEMICOLON, "[;]")
            matchers[TokenTypes.EOL] = DefaultMatcherImpl(TokenTypes.EOL, "\n")

            matchers[TokenTypes.ERROR] = DefaultMatcherImpl(TokenTypes.ERROR, ".+")

            return when (version) {
                Version.V1_0 -> {

                    matchers[TokenTypes.IDENTIFIER] = DefaultMatcherImpl(
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

                Version.V1_1 -> {

                    matchers[TokenTypes.CONST] = DefaultMatcherImpl(TokenTypes.CONST, "const")
                    matchers[TokenTypes.READINPUT] = DefaultMatcherImpl(TokenTypes.READINPUT, "readInput")
                    matchers[TokenTypes.IF] = DefaultMatcherImpl(TokenTypes.IF, "if")
                    matchers[TokenTypes.ELSE] = DefaultMatcherImpl(TokenTypes.ELSE, "else")

                    matchers[TokenTypes.TYPEBOOLEAN] = DefaultMatcherImpl(TokenTypes.TYPEBOOLEAN, "boolean")

                    matchers[TokenTypes.LEFTBRACKET] = DefaultMatcherImpl(TokenTypes.LEFTBRACKET, "[{]")
                    matchers[TokenTypes.RIGHTBRACKET] = DefaultMatcherImpl(TokenTypes.RIGHTBRACKET, "[}]")

                    matchers[TokenTypes.BOOLEAN] = DefaultMatcherImpl(TokenTypes.BOOLEAN, "true|false")

                    matchers[TokenTypes.IDENTIFIER] = DefaultMatcherImpl(
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

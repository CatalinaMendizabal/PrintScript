package edu.austral.ingsis.g3.lexer.matcher

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.lexer.lexerEnums.Version

class MatcherProvider {
    companion object {

        fun getMatchers(version: Version): HashMap<TokenTypes, LexerMatcher> {
            val matchers = HashMap<TokenTypes, LexerMatcher>()

            matchers[TokenTypes.LET] = LexerMatcherImpl(TokenTypes.LET, "let")
            matchers[TokenTypes.CONST] = LexerMatcherImpl(TokenTypes.CONST, "const")
            matchers[TokenTypes.IF] = LexerMatcherImpl(TokenTypes.IF, "if")
            matchers[TokenTypes.ELSE] = LexerMatcherImpl(TokenTypes.ELSE, "else")
            matchers[TokenTypes.PRINT] = LexerMatcherImpl(TokenTypes.PRINT, "println")


            matchers[TokenTypes.BOOLEANTYPE] = LexerMatcherImpl(TokenTypes.BOOLEANTYPE, "boolean")
            matchers[TokenTypes.STRINGTYPE] = LexerMatcherImpl(TokenTypes.STRINGTYPE, "string")
            matchers[TokenTypes.NUMBERTYPE] = LexerMatcherImpl(TokenTypes.NUMBERTYPE, "number")

            matchers[TokenTypes.SUM] = LexerMatcherImpl(TokenTypes.SUM, "[+]")
            matchers[TokenTypes.SUBSTRACT] = LexerMatcherImpl(TokenTypes.SUBSTRACT, "[-]")
            matchers[TokenTypes.MULTIPLY] = LexerMatcherImpl(TokenTypes.MULTIPLY, "[*]")
            matchers[TokenTypes.DIVIDE] = LexerMatcherImpl(TokenTypes.DIVIDE, "[/]")
            matchers[TokenTypes.EQUAL] = LexerMatcherImpl(TokenTypes.EQUAL, "[=]")
            matchers[TokenTypes.LEFTBRACKET] = LexerMatcherImpl(TokenTypes.LEFTBRACKET, "[{]")
            matchers[TokenTypes.LEFTPARENTHESIS] = LexerMatcherImpl(TokenTypes.LEFTPARENTHESIS, "[(]")
            matchers[TokenTypes.RIGHTPARENTHESIS] = LexerMatcherImpl(TokenTypes.RIGHTPARENTHESIS, "[)]")
            matchers[TokenTypes.RIGTHBRACKET] = LexerMatcherImpl(TokenTypes.RIGTHBRACKET, "[}]")

            matchers[TokenTypes.BOOLEAN] = LexerMatcherImpl(TokenTypes.BOOLEAN, "true|false")
            matchers[TokenTypes.NUMBER] = LexerMatcherImpl(TokenTypes.NUMBER, "-?[0-9.]+")
            matchers[TokenTypes.STRING] = LexerMatcherImpl(TokenTypes.STRING, "\".*\"|\'.*\'")

            // patterns[TType.IDENTIFIER] = LexerMatcherImpl(TType.IDENTIFIER ,"[_a-zA-Z][_a-zA-Z0-9]*")

            matchers[TokenTypes.COLON] = LexerMatcherImpl(TokenTypes.COLON, ":")
            matchers[TokenTypes.SEMICOLON] = LexerMatcherImpl(TokenTypes.SEMICOLON, ";")
            matchers[TokenTypes.WHITESPACE] = LexerMatcherImpl(TokenTypes.WHITESPACE, " ")
            matchers[TokenTypes.EOL] = LexerMatcherImpl(TokenTypes.EOL, "\n")
            matchers[TokenTypes.NOMATCH] = LexerMatcherImpl(TokenTypes.NOMATCH, ".+")

            when (version) {
                Version.V1_0 -> {
                    matchers[TokenTypes.IDENTIFIER] = LexerMatcherImpl(
                        TokenTypes.IDENTIFIER, "[_a-zA-Z][_a-zA-Z0-9]*",
                        listOf(
                            matchers[TokenTypes.LET]!!,
                            matchers[TokenTypes.CONST]!!,
                            matchers[TokenTypes.PRINT]!!,
                            matchers[TokenTypes.IF]!!,
                            matchers[TokenTypes.ELSE]!!,
                            matchers[TokenTypes.STRINGTYPE]!!,
                            matchers[TokenTypes.NUMBERTYPE]!!,
                            matchers[TokenTypes.BOOLEANTYPE]!!,
                            matchers[TokenTypes.BOOLEAN]!!
                        )
                    )
                }
                Version.V1_1 -> {
                    matchers[TokenTypes.IDENTIFIER] = LexerMatcherImpl(
                        TokenTypes.IDENTIFIER, "[_a-zA-Z][_a-zA-Z0-9]*",
                        listOf(
                            matchers[TokenTypes.LET]!!,
                            matchers[TokenTypes.CONST]!!,
                            matchers[TokenTypes.PRINT]!!,
                            matchers[TokenTypes.READINPUT]!!,
                            matchers[TokenTypes.IF]!!,
                            matchers[TokenTypes.ELSE]!!,
                            matchers[TokenTypes.STRINGTYPE]!!,
                            matchers[TokenTypes.NUMBERTYPE]!!,
                            matchers[TokenTypes.BOOLEANTYPE]!!,
                            matchers[TokenTypes.BOOLEAN]!!
                        )
                    )
                    matchers[TokenTypes.CONST] = LexerMatcherImpl(TokenTypes.CONST, "const")
                    matchers[TokenTypes.READINPUT] = LexerMatcherImpl(TokenTypes.READINPUT, "readInput")
                }
            }

            return matchers
        }
    }
}

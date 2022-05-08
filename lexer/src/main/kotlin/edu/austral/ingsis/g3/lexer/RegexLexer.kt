package edu.austral.ingsis.g3.lexer

import PrintScript.lexer.inputContent.Content
import edu.austral.ingsis.g3.lexer.exceptions.LexerException
import edu.austral.ingsis.g3.lexer.exceptions.VersionException
import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.lexer.lexerEnums.Type
import edu.austral.ingsis.g3.lexer.matcher.LexerMatcher
import edu.austral.ingsis.g3.lexer.matcher.LexerMatcherImpl
import java.util.Arrays.stream
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

class RegexLexer(version: String) : Lexer {

    private val patterns = HashMap<TokenTypes, LexerMatcher>()
    private var line = 0
    private var column = 0
    private var currentPos = 0
    private val version = version

    init {
        /*for (i in Type.values()) {
            patterns[i] = i.type
        }*/
        patterns[TokenTypes.LET] = LexerMatcherImpl(TokenTypes.LET,"let")
        patterns[TokenTypes.CONST] = LexerMatcherImpl(TokenTypes.CONST ,"const")
        patterns[TokenTypes.IF] = LexerMatcherImpl(TokenTypes.IF ,"if")
        patterns[TokenTypes.ELSE] = LexerMatcherImpl(TokenTypes.ELSE ,"else")
        patterns[TokenTypes.PRINT] = LexerMatcherImpl(TokenTypes.PRINT ,"println")


        patterns[TokenTypes.BOOLEANTYPE] = LexerMatcherImpl(TokenTypes.BOOLEANTYPE ,"boolean")
        patterns[TokenTypes.STRINGTYPE] = LexerMatcherImpl(TokenTypes.STRINGTYPE ,"string")
        patterns[TokenTypes.NUMBERTYPE] = LexerMatcherImpl(TokenTypes.NUMBERTYPE ,"number")

        patterns[TokenTypes.SUM] = LexerMatcherImpl(TokenTypes.SUM ,"[+]")
        patterns[TokenTypes.SUBSTRACT] = LexerMatcherImpl(TokenTypes.SUBSTRACT ,"[-]")
        patterns[TokenTypes.MULTIPLY] = LexerMatcherImpl(TokenTypes.MULTIPLY ,"[*]")
        patterns[TokenTypes.DIVIDE] = LexerMatcherImpl(TokenTypes.DIVIDE ,"[/]")
        patterns[TokenTypes.EQUAL] = LexerMatcherImpl(TokenTypes.EQUAL ,"[=]")
        patterns[TokenTypes.LEFTBRACKET] = LexerMatcherImpl(TokenTypes.LEFTBRACKET ,"[{]")
        patterns[TokenTypes.LEFTPARENTHESIS] = LexerMatcherImpl(TokenTypes.LEFTPARENTHESIS ,"[(]")
        patterns[TokenTypes.RIGHTPARENTHESIS] = LexerMatcherImpl(TokenTypes.RIGHTPARENTHESIS ,"[)]")
        patterns[TokenTypes.RIGTHBRACKET] = LexerMatcherImpl(TokenTypes.RIGTHBRACKET ,"[}]")

        patterns[TokenTypes.BOOLEAN] = LexerMatcherImpl(TokenTypes.BOOLEAN ,"true|false")
        patterns[TokenTypes.NUMBER] = LexerMatcherImpl(TokenTypes.NUMBER ,"-?[0-9.]+")
        patterns[TokenTypes.STRING] = LexerMatcherImpl(TokenTypes.STRING ,"\".*\"|\'.*\'")

        patterns[TokenTypes.IDENTIFIER] = LexerMatcherImpl(TokenTypes.IDENTIFIER ,"[_a-zA-Z][_a-zA-Z0-9]*")
       // patterns[TType.LITERAL] = "\"([_a-zA-Z0-9 !\\/.])*\"|'([_a-zA-Z0-9 !\\/.])*'";

        patterns[TokenTypes.COLON] = LexerMatcherImpl(TokenTypes.COLON ,":")
        patterns[TokenTypes.SEMICOLON] = LexerMatcherImpl(TokenTypes.SEMICOLON ,";")
        patterns[TokenTypes.WHITESPACE] = LexerMatcherImpl(TokenTypes.WHITESPACE ," ")
        patterns[TokenTypes.EOL] = LexerMatcherImpl(TokenTypes.EOL ,"\n")
        patterns[TokenTypes.NOMATCH] = LexerMatcherImpl(TokenTypes.NOMATCH ,".+")
    }


    override fun lex(input: Content): List<Token> {
        val tokens = ArrayList<Token>()
        val matcher: Matcher = generateMatcher(input.convertContent())

        /*while (matcher.find()) {
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
        }*/
        while (matcher.find()) {
            val match = matcher.group()
            val matched: Token = patterns.keys
                .filter { matcher.group(it.type) != null }
                .map {
                    val endColumn = if (it == TokenTypes.EOL) 0 else column + match.length
                    val endLine = if (it == TokenTypes.EOL) line + 1 else line
                    val endPos = currentPos + match.length
                    val range = LexicalRange(column, line, endColumn, endLine)

                    if (it == TokenTypes.NOMATCH) throw LexerException("ERROR", line, column)
                    val token = Token(it, currentPos, endPos, range)

                    column = endColumn
                    line = endLine
                    currentPos = endPos

                    token
                }.first()

            tokens += matched
        }

        // Add EOF token
        tokens += Token(TokenTypes.EOF, currentPos, currentPos, LexicalRange(column, line, column, line))
        println(tokens)
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
                if (element == TokenTypes.NOMATCH) {
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
            stream(TokenTypes.values())
                .map { key -> String.format("?(<%s>%s)", key.name, patterns[key]) }
                .collect(Collectors.joining())
                .substring(1))
            .matcher(line)
    }
}

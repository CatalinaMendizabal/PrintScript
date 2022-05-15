package edu.austral.ingsis.g3.lexer.lexerEnums

import org.austral.ingsis.printscript.common.TokenType

enum class TokenTypes(override val type: String) : TokenType {
    IDENTIFIER("IDENTIFIER"),

    LET("LET"),
    CONST("CONST"),
    PRINTLN("PRINTLN"),
    READINPUT("READINPUT"),
    IF("IF"),
    ELSE("ELSE"),

    TYPESTRING("TYPESTRING"),
    TYPENUMBER("TYPENUMBER"),
    TYPEBOOLEAN("TYPEBOOLEAN"),

    SUM("SUM"),
    SUBSTRACT("SUBSTRACT"),
    MULTIPLY("MULTIPLY"),
    DIVIDE("DIVIDE"),
    EQUAL("EQUAL"),
    LEFTPARENTHESIS("LEFTPARENTHESIS"),
    RIGHTPARENTHESIS("RIGHTPARENTHESIS"),
    LEFTBRACKET("LEFTBRACKET"),
    RIGHTBRACKET("RIGHTBRACKET"),

    NUMBER("NUMBER"),
    STRING("STRING"),
    BOOLEAN("BOOLEAN"),

    WHITESPACE("WHITESPACE"),
    EOF("EOF"),
    EOL("EOL"),
    COLON("COLON"),
    SEMICOLON("SEMICOLON"),

    ERROR("ERROR")
}

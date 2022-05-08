package edu.austral.ingsis.g3.lexer.lexerEnums

import org.austral.ingsis.printscript.common.TokenType

enum class TokenTypes(override val type: String) : TokenType {
    // Variables
    IDENTIFIER("IDENTIFIER"),

    // Keywords
    LET("LET"),
    CONST("CONST"),
    PRINT("PRINT"),
    READINPUT("READINPUT"),
    IF("IF"),
    ELSE("ELSE"),

    // Types
    STRINGTYPE("STRINGTYPE"),
    NUMBERTYPE("NUMBERTYPE"),
    BOOLEANTYPE("BOOLEANTYPE"),

    // Operations
    SUM("SUM"),
    SUBSTRACT("SUBSTRACT"),
    MULTIPLY("MULTIPLY"),
    DIVIDE("DIVIDE"),
    EQUAL("EQUAL"),
    LEFTPARENTHESIS("LEFTPARENTHESIS"),
    RIGHTPARENTHESIS("RIGHTPARENTHESIS"),
    LEFTBRACKET("LEFTBRACKET"),
    RIGTHBRACKET("RIGTHBRACKET"),

    // Declarations
    NUMBER("NUMBER"),
    STRING("STRING"),
    BOOLEAN("BOOLEAN"),

    // Elements
    WHITESPACE("WHITESPACE"),
    EOF("EOF"),
    EOL("EOL"),
    COLON("COLON"),
    SEMICOLON("SEMICOLON"),

    // No match token
    NOMATCH("NOMATCH")
}

package edu.austral.ingsis.g3.lexer.lexerEnums

import org.austral.ingsis.printscript.common.TokenType

enum class Type(override val type: String) : TokenType {
    LET("let"),
    CONST("const"),
    PRINT("println"),
    STRINGTYPE("string"),
    STRING("\".*\"|\'.*\'"),
    NUMBERTYPE("number"),
    NUMBER("-?\\d+\\.?\\d*"),
    BOOLEANTYPE("boolean"),
    BOOLEAN("true|false"),
    LEFTPARENTHESIS("[(]"),
    RIGHTPARENTHESIS("[)]"),
    RIGTHBRACKET("[}]"),
    LEFTBRACKET("[{]"),
    IF("if"),
    ELSE("else"),
    IDENTIFIER("[_a-zA-Z][_a-zA-Z0-9]*"),
    SUM("[+]"),
    SUBSTRACT("[-]"),
    MULTIPLY("[*]"),
    DIVIDE("[/]"),
    EQUAL("[=]"),
    SEMICOLON("[;]"),
    COLON("[:]"),
    EOF("[^\\S\\n]*$"),
    LITERAL("[0-9]{1,9}(\\\\.[0-9]*)?"),
    WHITESPACE(" "),
    EOL("\n"),
    ERROR(".+");
}
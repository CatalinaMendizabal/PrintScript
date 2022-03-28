package PrintScript.lexer.lexerEnums

import org.austral.ingsis.printscript.common.TokenType

enum class Types(override val type: String): TokenType {
    LET("let"),
    STRING("string"),
    NUMBER( "number"),
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
    ENTER("\n");
}

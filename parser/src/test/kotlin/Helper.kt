import PrintScript.lexer.lexerEnums.Types
import PrintScript.lexer.lexerEnums.Types.*
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

val token_001 = listOf(
    Token(IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(LITERAL, 4, 5, LexicalRange(4, 0, 5, 1))
)

val token_002 = listOf(
    Token(IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 1))
)

val token_003 = listOf(
    Token(IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(LITERAL, 4, 7, LexicalRange(4, 0, 7, 1))
)
val token_004 = listOf(
    Token(LITERAL, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(LITERAL, 4, 5, LexicalRange(4, 0, 5, 0))
)
val token_005 = listOf(
    Token(LITERAL, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(LITERAL, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(MULTIPLY, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(LITERAL, 8, 9, LexicalRange(8, 0, 9, 0)),
    Token(SUBSTRACT, 10, 11, LexicalRange(10, 0, 11, 0)),
    Token(LITERAL, 12, 14, LexicalRange(12, 0, 14, 0)),
    Token(DIVIDE, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(LITERAL, 17, 18, LexicalRange(17, 0, 18, 0))
)

val token_006 = listOf(
    Token(LITERAL, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0))
)

val token_007 = listOf(
    Token(LITERAL, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(MULTIPLY, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(LITERAL, 8, 14, LexicalRange(8, 0, 14, 0)),
    Token(SUBSTRACT, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(LITERAL, 17, 18, LexicalRange(17, 0, 18, 0))
)

val token_008 = listOf(
    Token(PRINT, 0, 7, LexicalRange(0, 0, 7, 0)),
    Token(LEFTPARENTHESIS, 7, 8, LexicalRange(7, 0, 8, 0)),
    Token(LITERAL, 8, 14, LexicalRange(8, 0, 14, 0)),
    Token(RIGHTPARENTHESIS, 14, 15, LexicalRange(14, 0, 15, 0))
)

val token_009 = listOf(
    Token(LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(NUMBERTYPE, 6, 12, LexicalRange(6, 0, 12, 0)),
    Token(SEMICOLON, 12, 13, LexicalRange(12, 0, 13, 0))
)

val token_010 = listOf(
    Token(LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(NUMBERTYPE, 6, 12, LexicalRange(6, 0, 12, 0)),
    Token(EQUAL, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(LITERAL, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(SEMICOLON, 16, 17, LexicalRange(16, 0, 17, 0))
)

val token_011 = listOf(
    Token(LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(NUMBERTYPE, 6, 12, LexicalRange(6, 0, 12, 0))
)

val token_012 = listOf(Token(STRINGTYPE, 0, 6, LexicalRange(0, 0, 6, 0)))

val token_6 = listOf(
    Token(LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(NUMBERTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(LITERAL, 16, 21, LexicalRange(16, 0, 21, 0)),
    Token(SEMICOLON, 21, 22, LexicalRange(21, 0, 22, 0)),
    Token(EOF, 22, 22, LexicalRange(22, 0, 22, 0))
)

import enums.TokenTypes
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

const val src_001 = "a = 5"

const val src_002 = "a = b"

const val src_003 = "a = 'a'"

const val src_004 = "2 + 3"

const val src_005 = "2 + 3 * 4 - 10 / 5"

const val src_006 = "2 + a"

const val src_007 = "let a:number = 8;\nlet b:string = '8';\na = a + b;\nprintln(a);"

val token_001 = listOf(
    Token(TokenTypes.IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(TokenTypes.NUMBER, 4, 5, LexicalRange(4, 0, 5, 1))
)

val token_002 = listOf(
    Token(TokenTypes.IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 1))
)

val token_003 = listOf(
    Token(TokenTypes.IDENTIFIER, 0, 1, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.EQUAL, 2, 3, LexicalRange(2, 0, 3, 1)),
    Token(TokenTypes.STRING, 4, 7, LexicalRange(4, 0, 7, 1))
)

val token_004 = listOf(
    Token(TokenTypes.NUMBER, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(TokenTypes.SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(TokenTypes.NUMBER, 4, 5, LexicalRange(4, 0, 5, 0))
)
val token_005 = listOf(
    Token(TokenTypes.NUMBER, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(TokenTypes.SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(TokenTypes.NUMBER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.MULTIPLY, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(TokenTypes.NUMBER, 8, 9, LexicalRange(8, 0, 9, 0)),
    Token(TokenTypes.SUBSTRACT, 10, 11, LexicalRange(10, 0, 11, 0)),
    Token(TokenTypes.NUMBER, 12, 14, LexicalRange(12, 0, 14, 0)),
    Token(TokenTypes.DIVIDE, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(TokenTypes.NUMBER, 17, 18, LexicalRange(17, 0, 18, 0))
)

val token_006 = listOf(
    Token(TokenTypes.NUMBER, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(TokenTypes.SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0))
)

val token_007 = listOf(
    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
    Token(TokenTypes.EQUAL, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(TokenTypes.NUMBER, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(TokenTypes.SEMICOLON, 16, 17, LexicalRange(16, 0, 17, 0)),
    Token(TokenTypes.LET, 18, 21, LexicalRange(0, 1, 3, 1)),
    Token(TokenTypes.IDENTIFIER, 22, 23, LexicalRange(4, 1, 5, 1)),
    Token(TokenTypes.COLON, 23, 24, LexicalRange(5, 1, 6, 1)),
    Token(TokenTypes.TYPESTRING, 24, 30, LexicalRange(6, 1, 12, 1)),
    Token(TokenTypes.EQUAL, 31, 32, LexicalRange(13, 1, 14, 1)),
    Token(TokenTypes.STRING, 33, 36, LexicalRange(15, 1, 16, 1)),
    Token(TokenTypes.SEMICOLON, 36, 37, LexicalRange(16, 1, 17, 1)),
    Token(TokenTypes.IDENTIFIER, 38, 39, LexicalRange(0, 2, 1, 2)),
    Token(TokenTypes.EQUAL, 40, 41, LexicalRange(2, 2, 3, 2)),
    Token(TokenTypes.IDENTIFIER, 42, 43, LexicalRange(4, 2, 5, 2)),
    Token(TokenTypes.SUM, 44, 45, LexicalRange(6, 2, 7, 2)),
    Token(TokenTypes.IDENTIFIER, 46, 47, LexicalRange(8, 2, 9, 2)),
    Token(TokenTypes.SEMICOLON, 47, 48, LexicalRange(9, 2, 10, 2)),
    Token(TokenTypes.PRINTLN, 49, 56, LexicalRange(0, 3, 7, 3)),
    Token(TokenTypes.LEFTPARENTHESIS, 56, 57, LexicalRange(7, 3, 8, 3)),
    Token(TokenTypes.IDENTIFIER, 57, 58, LexicalRange(8, 3, 9, 3)),
    Token(TokenTypes.RIGHTPARENTHESIS, 58, 59, LexicalRange(9, 3, 10, 3)),
    Token(TokenTypes.SEMICOLON, 59, 60, LexicalRange(10, 3, 11, 3)),
    Token(TokenTypes.EOF, 60, 60, LexicalRange(10, 3, 11, 3))
)

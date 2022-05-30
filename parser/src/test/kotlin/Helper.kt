import enums.TokenTypes
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

const val src_001 = "a = 5"

const val src_002 = "a = b"

const val src_003 = "a = 'a'"

const val src_004 = "2 + 3"

const val src_005 = "2 + 3 * 4 - 10 / 5"

const val src_006 = "2 + a"

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

import PrintScript.lexer.lexerEnums.Types.*
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

const val src_001 = "let a: String;"

const val src_002 = "let a: String = \"This is a test!\";"

const val src_003 = "let a: Number = 2.123;"

const val src_04 = "\'Hello"


val tokens_001 = listOf(
    Token(LET,  0, 3, LexicalRange(0, 0, 3,  0)),
    Token(WHITESPACE, 3, 4, LexicalRange( 3, 0, 4, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON,  5, 6, LexicalRange(5, 0, 6, 0)),
    Token(WHITESPACE, 6,  7,  LexicalRange(6, 0, 7, 0)),
    Token(STRINGTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(SEMICOLON, 13,14, LexicalRange(13, 0, 14, 0)),
    Token(EOF, 14, 14,  LexicalRange(14, 0, 14, 0))
)

val tokens_002 = listOf(
    Token(LET, 0, 3, range = LexicalRange(0, 0, 3, 0)),
    Token(WHITESPACE, 3, 4, LexicalRange(3, 0, 4, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(WHITESPACE, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(STRINGTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(WHITESPACE, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(WHITESPACE, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(STRING, 16, 33, LexicalRange(16, 0, 33, 0)),
    Token(SEMICOLON, 33, 34, LexicalRange(33, 0, 34, 0)),
    Token(EOF, 34, 34, LexicalRange(34, 0, 34, 0))
)


val tokens_003 = listOf(
    Token(LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(WHITESPACE, 3, 4, LexicalRange(3, 0, 4, 0)),
    Token(IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(WHITESPACE, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(NUMBERTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(WHITESPACE, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(WHITESPACE, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(NUMBER, 16, 21, LexicalRange(16, 0, 21, 0)),
    Token(SEMICOLON, 21, 22, LexicalRange(21, 0, 22, 0)),
    Token(EOF, 22, 22, LexicalRange(22, 0, 22, 0))
)

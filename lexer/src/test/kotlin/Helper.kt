import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

const val src_001 = "let a: String;"

const val src_002 = "let a: String = \"This is a test!\";"

const val src_003 = "let a: number = 2.123;"

const val src_04 = "\'Hello"

val tokens_001 = listOf(
    Token(Types.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(Types.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(Types.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(Types.STRINGTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(Types.SEMICOLON, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(Types.EOF, 14, 14, LexicalRange(14, 0, 14, 0))
)

val tokens_002 = listOf(
    Token(Types.LET, 0, 3, range = LexicalRange(0, 0, 3, 0)),
    Token(Types.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(Types.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(Types.STRINGTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(Types.EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(Types.STRING, 16, 33, LexicalRange(16, 0, 33, 0)),
    Token(Types.SEMICOLON, 33, 34, LexicalRange(33, 0, 34, 0)),
    Token(Types.EOF, 34, 34, LexicalRange(34, 0, 34, 0))
)

val tokens_003 = listOf(
    Token(Types.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(Types.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(Types.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(Types.NUMBERTYPE, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(Types.EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(Types.NUMBER, 16, 21, LexicalRange(16, 0, 21, 0)),
    Token(Types.SEMICOLON, 21, 22, LexicalRange(21, 0, 22, 0)),
    Token(Types.EOF, 22, 22, LexicalRange(22, 0, 22, 0))
)

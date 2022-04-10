import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

const val src_001 = "let a: String;"

const val src_002 = "let a: String = \"This is a test!\";"

const val src_003 = "let a: Number = 2.123;"

const val src_047 = "\'Hello"

val tokens_001 = listOf(
    Token(type = Types.LET, from = 0, to = 3, range = LexicalRange(startCol = 0, startLine = 0, endCol = 3, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 3, to = 4, range = LexicalRange(startCol = 3, startLine = 0, endCol = 4, endLine = 0)),
    Token(type = Types.IDENTIFIER, from = 4, to = 5, range = LexicalRange(startCol = 4, startLine = 0, endCol = 5, endLine = 0)),
    Token(type = Types.COLON, from = 5, to = 6, range = LexicalRange(startCol = 5, startLine = 0, endCol = 6, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 6, to = 7, range = LexicalRange(startCol = 6, startLine = 0, endCol = 7, endLine = 0)),
    Token(type = Types.STRINGTYPE, from = 7, to = 13, range = LexicalRange(startCol = 7, startLine = 0, endCol = 13, endLine = 0)),
    Token(type = Types.SEMICOLON, from = 13, to = 14, range = LexicalRange(startCol = 13, startLine = 0, endCol = 14, endLine = 0)),
    Token(type = Types.EOF, from = 14, to = 14, range = LexicalRange(startCol = 14, startLine = 0, endCol = 14, endLine = 0))
)

val tokens_002 = listOf(
    Token(type = Types.LET, from = 0, to = 3, range = LexicalRange(startCol = 0, startLine = 0, endCol = 3, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 3, to = 4, range = LexicalRange(startCol = 3, startLine = 0, endCol = 4, endLine = 0)),
    Token(type = Types.IDENTIFIER, from = 4, to = 5, range = LexicalRange(startCol = 4, startLine = 0, endCol = 5, endLine = 0)),
    Token(type = Types.COLON, from = 5, to = 6, range = LexicalRange(startCol = 5, startLine = 0, endCol = 6, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 6, to = 7, range = LexicalRange(startCol = 6, startLine = 0, endCol = 7, endLine = 0)),
    Token(type = Types.STRINGTYPE, from = 7, to = 13, range = LexicalRange(startCol = 7, startLine = 0, endCol = 13, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 13, to = 14, range = LexicalRange(startCol = 13, startLine = 0, endCol = 14, endLine = 0)),
    Token(type = Types.EQUAL, from = 14, to = 15, range = LexicalRange(startCol = 14, startLine = 0, endCol = 15, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 15, to = 16, range = LexicalRange(startCol = 15, startLine = 0, endCol = 16, endLine = 0)),
    Token(type = Types.STRING, from = 16, to = 33, range = LexicalRange(startCol = 16, startLine = 0, endCol = 33, endLine = 0)),
    Token(type = Types.SEMICOLON, from = 33, to = 34, range = LexicalRange(startCol = 33, startLine = 0, endCol = 34, endLine = 0)),
    Token(type = Types.EOF, from = 34, to = 34, range = LexicalRange(startCol = 34, startLine = 0, endCol = 34, endLine = 0))
)


val tokens_003 = listOf(
    Token(type = Types.LET, from = 0, to = 3, range = LexicalRange(startCol = 0, startLine = 0, endCol = 3, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 3, to = 4, range = LexicalRange(startCol = 3, startLine = 0, endCol = 4, endLine = 0)),
    Token(type = Types.IDENTIFIER, from = 4, to = 5, range = LexicalRange(startCol = 4, startLine = 0, endCol = 5, endLine = 0)),
    Token(type = Types.COLON, from = 5, to = 6, range = LexicalRange(startCol = 5, startLine = 0, endCol = 6, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 6, to = 7, range = LexicalRange(startCol = 6, startLine = 0, endCol = 7, endLine = 0)),
    Token(type = Types.NUMBERTYPE, from = 7, to = 13, range = LexicalRange(startCol = 7, startLine = 0, endCol = 13, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 13, to = 14, range = LexicalRange(startCol = 13, startLine = 0, endCol = 14, endLine = 0)),
    Token(type = Types.EQUAL, from = 14, to = 15, range = LexicalRange(startCol = 14, startLine = 0, endCol = 15, endLine = 0)),
    Token(type = Types.WHITESPACE, from = 15, to = 16, range = LexicalRange(startCol = 15, startLine = 0, endCol = 16, endLine = 0)),
    Token(type = Types.NUMBER, from = 16, to = 21, range = LexicalRange(startCol = 16, startLine = 0, endCol = 21, endLine = 0)),
    Token(type = Types.SEMICOLON, from = 21, to = 22, range = LexicalRange(startCol = 21, startLine = 0, endCol = 22, endLine = 0)),
    Token(type = Types.EOF, from = 22, to = 22, range = LexicalRange(startCol = 22, startLine = 0, endCol = 22, endLine = 0))
)

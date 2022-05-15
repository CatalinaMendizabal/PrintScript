import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token

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
    Token(TokenTypes.NUMBER, 0, 1, LexicalRange(0, 0, 1, 0)),
    Token(TokenTypes.SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.MULTIPLY, 6, 7, LexicalRange(6, 0, 7, 0)),
    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
    Token(TokenTypes.SUBSTRACT, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(TokenTypes.NUMBER, 17, 18, LexicalRange(17, 0, 18, 0))
)

val token_008 = listOf(
    Token(TokenTypes.PRINTLN, 0, 7, LexicalRange(0, 0, 7, 0)),
    Token(TokenTypes.LEFTPARENTHESIS, 7, 8, LexicalRange(7, 0, 8, 0)),
    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
    Token(TokenTypes.RIGHTPARENTHESIS, 14, 15, LexicalRange(14, 0, 15, 0))
)

val token_009 = listOf(
    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
    Token(TokenTypes.SEMICOLON, 12, 13, LexicalRange(12, 0, 13, 0))
)

val token_010 = listOf(
    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
    Token(TokenTypes.EQUAL, 13, 14, LexicalRange(13, 0, 14, 0)),
    Token(TokenTypes.NUMBER, 15, 16, LexicalRange(15, 0, 16, 0)),
    Token(TokenTypes.SEMICOLON, 16, 17, LexicalRange(16, 0, 17, 0))
)

val token_011 = listOf(
    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0))
)

val token_012 = listOf(Token(TokenTypes.TYPESTRING, 0, 6, LexicalRange(0, 0, 6, 0)))

val token_6 = listOf(
    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
    Token(TokenTypes.TYPENUMBER, 7, 13, LexicalRange(7, 0, 13, 0)),
    Token(TokenTypes.EQUAL, 14, 15, LexicalRange(14, 0, 15, 0)),
    Token(TokenTypes.NUMBER, 16, 21, LexicalRange(16, 0, 21, 0)),
    Token(TokenTypes.SEMICOLON, 21, 22, LexicalRange(21, 0, 22, 0)),
    Token(TokenTypes.EOF, 22, 22, LexicalRange(22, 0, 22, 0))
)

val token_013 = listOf(
    Token(TokenTypes.IF, 0, 2, LexicalRange(0, 0, 2, 0)),
    Token(TokenTypes.LEFTPARENTHESIS, 2, 3, LexicalRange(2, 0, 3, 0)),
    Token(TokenTypes.BOOLEAN, 3, 8, LexicalRange(3, 0, 8, 0)),
    Token(TokenTypes.RIGHTPARENTHESIS, 8, 9, LexicalRange(8, 0, 9, 0)),
    Token(TokenTypes.LEFTBRACKET, 9, 10, LexicalRange(9, 0, 10, 0)),
    Token(TokenTypes.LET, 10, 13, LexicalRange(10, 0, 13, 0)),
    Token(TokenTypes.IDENTIFIER, 14, 22, LexicalRange(14, 0, 22, 0)),
    Token(TokenTypes.COLON, 22, 23, LexicalRange(22, 0, 23, 0)),
    Token(TokenTypes.TYPESTRING, 24, 30, LexicalRange(24, 0, 30, 0)),
    Token(TokenTypes.EQUAL, 31, 32, LexicalRange(31, 0, 32, 0)),
    Token(TokenTypes.STRING, 33, 47, LexicalRange(33, 0, 47, 0)),
    Token(TokenTypes.SEMICOLON, 47, 48, LexicalRange(47, 0, 48, 0)),
    Token(TokenTypes.RIGHTBRACKET, 48, 49, LexicalRange(48, 0, 49, 0)),
    Token(TokenTypes.ELSE, 49, 53, LexicalRange(49, 0, 53, 0)),
    Token(TokenTypes.LEFTBRACKET, 53, 54, LexicalRange(53, 0, 54, 0)),
    Token(TokenTypes.CONST, 56, 61, LexicalRange(0, 1, 5, 1)),
    Token(TokenTypes.IDENTIFIER, 62, 70, LexicalRange(6, 1, 14, 1)),
    Token(TokenTypes.COLON, 70, 71, LexicalRange(14, 1, 15, 1)),
    Token(TokenTypes.TYPEBOOLEAN, 71, 78, LexicalRange(15, 1, 22, 1)),
    Token(TokenTypes.EQUAL, 78, 79, LexicalRange(22, 1, 23, 1)),
    Token(TokenTypes.BOOLEAN, 79, 83, LexicalRange(23, 1, 27, 1)),
    Token(TokenTypes.SEMICOLON, 83, 84, LexicalRange(27, 1, 28, 1)),
    Token(TokenTypes.RIGHTBRACKET, 84, 85, LexicalRange(28, 1, 29, 1)),
    Token(TokenTypes.SEMICOLON, 85, 86, LexicalRange(29, 1, 30, 1)),
    Token(TokenTypes.IF, 86, 88, LexicalRange(30, 1, 32, 1)),
    Token(TokenTypes.LEFTPARENTHESIS, 88, 89, LexicalRange(32, 1, 33, 1)),
    Token(TokenTypes.BOOLEAN, 89, 97, LexicalRange(33, 1, 41, 1)),
    Token(TokenTypes.RIGHTPARENTHESIS, 97, 98, LexicalRange(41, 1, 42, 1)),
    Token(TokenTypes.LEFTBRACKET, 98, 99, LexicalRange(42, 1, 43, 1)),
    Token(TokenTypes.LET, 99, 102, LexicalRange(43, 1, 46, 1)),
    Token(TokenTypes.IDENTIFIER, 103, 111, LexicalRange(47, 1, 55, 1)),
    Token(TokenTypes.COLON, 111, 112, LexicalRange(55, 1, 56, 1)),
    Token(TokenTypes.TYPESTRING, 113, 119, LexicalRange(57, 1, 63, 1)),
    Token(TokenTypes.EQUAL, 120, 121, LexicalRange(64, 1, 65, 1)),
    Token(TokenTypes.READINPUT, 122, 131, LexicalRange(66, 1, 75, 1)),
    Token(TokenTypes.LEFTPARENTHESIS, 131, 132, LexicalRange(75, 1, 76, 1)),
    Token(TokenTypes.STRING, 132, 138, LexicalRange(76, 1, 82, 1)),
    Token(TokenTypes.SUM, 139, 140, LexicalRange(83, 1, 84, 1)),
    Token(TokenTypes.READINPUT, 141, 150, LexicalRange(85, 1, 94, 1)),
    Token(TokenTypes.LEFTPARENTHESIS, 150, 151, LexicalRange(94, 1, 95, 1)),
    Token(TokenTypes.STRING, 151, 159, LexicalRange(95, 1, 103, 1)),
    Token(TokenTypes.RIGHTPARENTHESIS, 159, 160, LexicalRange(103, 1, 104, 1)),
    Token(TokenTypes.SUM, 161, 162, LexicalRange(105, 1, 106, 1)),
    Token(TokenTypes.STRING, 163, 166, LexicalRange(107, 1, 110, 1)),
    Token(TokenTypes.RIGHTPARENTHESIS, 166, 167, LexicalRange(110, 1, 111, 1)),
    Token(TokenTypes.SEMICOLON, 167, 168, LexicalRange(111, 1, 112, 1)),
    Token(TokenTypes.RIGHTBRACKET, 168, 169, LexicalRange(112, 1, 113, 1)),
    Token(TokenTypes.SEMICOLON, 169, 170, LexicalRange(113, 1, 114, 1)),
)

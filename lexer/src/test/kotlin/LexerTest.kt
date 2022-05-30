import PrintScript.lexer.inputContent.StringContent
import enums.PrintScriptVersion
import enums.TokenTypes
import exception.LexerException
import fixtures.*
import impl.DefaultRegexLexer
import impl.MatchProvider
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import lexer.interfaces.Lexer

class LexerTest {

    private val lexer: Lexer

    init {
        MatchProvider.getMatchers(PrintScriptVersion.V1_0)
        val matchers = MatchProvider.getMatchers(PrintScriptVersion.V1_1)
        lexer = DefaultRegexLexer(matchers)
    }

    @Test
    fun test001_stringDeclarationTest() {
        val tokens = lexer.lex(StringContent(src_001))
        val expected = tokens_001.toString()

        assertEquals(6, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test002_stringDoubleQuoteAssignationTest() {
        val tokens = lexer.lex(StringContent(src_002))
        val expected = tokens_002.toString()

        assertEquals(5, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test003_stringSingleQuoteAssignationTest() {
        val tokens = lexer.lex(StringContent(src_003))
        val expected = tokens_003.toString()

        assertEquals(5, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test004_stringDeclarationAssignationTest() {
        val tokens = lexer.lex(StringContent(src_004))
        val expected = tokens_004.toString()

        assertEquals(8, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test005_numberDeclarationTest() {
        val tokens = lexer.lex(StringContent(src_005))
        val expected = tokens_005.toString()

        assertEquals(6, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test006_numberIntegerAssignationTest() {
        val tokens = lexer.lex(StringContent(src_006))
        val expected = tokens_006.toString()

        assertEquals(5, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test007_numberDeclarationAssignationTest() {
        val tokens = lexer.lex(StringContent(src_007))
        val expected = tokens_007.toString()

        assertEquals(8, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test008_printStringTest() {
        val tokens = lexer.lex(StringContent(src_008))
        val expected = tokens_008.toString()

        assertEquals(6, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test009_printNumberTest() {
        val tokens = lexer.lex(StringContent(src_009))
        val expected = tokens_009.toString()

        assertEquals(6, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test010_newLineTest() {
        val tokens = lexer.lex(StringContent(src_010))
        val expected = tokens_010.toString()

        assertEquals(9, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test011_additionTest() {
        val tokens = lexer.lex(StringContent(src_011))
        val expected = tokens_011.toString()

        assertEquals(7, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test012_subtractionTest() {
        val tokens = lexer.lex(StringContent(src_012))
        val expected = tokens_012.toString()

        assertEquals(7, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test013_multiplicationTest() {
        val tokens = lexer.lex(StringContent(src_013))
        val expected = tokens_013.toString()

        assertEquals(7, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test014_divisionTest() {
        val tokens = lexer.lex(StringContent(src_014))
        val expected = tokens_014.toString()

        assertEquals(7, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    // Token Regex Test

    @Test
    fun test015_letTokenTest() {
        val tokens = lexer.lex(StringContent(src_015))
        val expected = TokenTypes.LET

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test016_typeStringTokenTest() {
        val tokens = lexer.lex(StringContent(src_016))
        val expected = TokenTypes.TYPESTRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test017_typeNumberTokenTest() {
        val tokens = lexer.lex(StringContent(src_017))
        val expected = TokenTypes.TYPENUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test018_printlnTokenTest() {
        val tokens = lexer.lex(StringContent(src_018))
        val expected = TokenTypes.PRINTLN

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test019_plusTokenTest() {
        val tokens = lexer.lex(StringContent(src_019))
        val expected = TokenTypes.SUM

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test020_minusTokenTest() {
        val tokens = lexer.lex(StringContent(src_020))
        val expected = TokenTypes.SUBSTRACT

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test021_timesTokenTest() {
        val tokens = lexer.lex(StringContent(src_021))
        val expected = TokenTypes.MULTIPLY

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test022_dividedByTokenTest() {
        val tokens = lexer.lex(StringContent(src_022))
        val expected = TokenTypes.DIVIDE

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test023_assignmentTokenTest() {
        val tokens = lexer.lex(StringContent(src_023))
        val expected = TokenTypes.EQUAL

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test024_openParenTokenTest() {
        val tokens = lexer.lex(StringContent(src_024))
        val expected = TokenTypes.LEFTPARENTHESIS

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test025_closeParenTokenTest() {
        val tokens = lexer.lex(StringContent(src_025))
        val expected = TokenTypes.RIGHTPARENTHESIS

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test026_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_026))
        val expected = TokenTypes.NUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test027_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_027))
        val expected = TokenTypes.NUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test028_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_028))
        val expected = TokenTypes.NUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test029_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_029))
        val expected = TokenTypes.NUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test030_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_030))
        val expected = TokenTypes.NUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test031_stringTokenTest() {
        val tokens = lexer.lex(StringContent(src_031))
        val expected = TokenTypes.STRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test032_stringTokenTest() {
        val tokens = lexer.lex(StringContent(src_032))
        val expected = TokenTypes.STRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test033_stringTokenTest() {
        val tokens = lexer.lex(StringContent(src_033))
        val expected = TokenTypes.STRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test034_stringTokenTest() {
        val tokens = lexer.lex(StringContent(src_034))
        val expected = TokenTypes.STRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test035_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_035))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test036_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_036))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test037_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_037))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test038_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_038))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test039_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_039))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test040_identifierTokenTest() {
        val tokens = lexer.lex(StringContent(src_040))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test042_eofTokenTest() {
        val tokens = lexer.lex(StringContent(src_042))
        val expected = TokenTypes.EOF

        assertEquals(1, tokens.size)
        assertEquals(expected, tokens[0].type)
    }


    @Test
    fun test044_colonTokenTest() {
        val tokens = lexer.lex(StringContent(src_044))
        val expected = TokenTypes.COLON

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test045_semicolonTokenTest() {
        val tokens = lexer.lex(StringContent(src_045))
        val expected = TokenTypes.SEMICOLON

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test046_identifierWithKeywordsTokenTest() {
        val tokens = lexer.lex(StringContent(src_046))
        val expected = TokenTypes.IDENTIFIER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    // Exception test

    @Test
    fun test047_exceptionTest() {
        assertFailsWith<LexerException>(
            block = {
                lexer.lex(StringContent(src_047))
            }
        )
    }

    @Test
    fun test048_exceptionTest() {
        assertFailsWith<LexerException>(
            block = {
                lexer.lex(StringContent(src_048))
            }
        )
    }

    @Test
    fun test049_exceptionTest() {
        assertFailsWith<LexerException>(
            block = {
                lexer.lex(StringContent(src_049))
            }
        )
    }

    @Test
    fun test050_exceptionTest() {
        assertFailsWith<LexerException>(
            block = {
                lexer.lex(StringContent(src_050))
            }
        )
    }

    // v1.1 tokens test

    @Test
    fun test051_constTokenTest() {
        val tokens = lexer.lex(StringContent(src_051))
        val expected = TokenTypes.CONST

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test052_ifTokenTest() {
        val tokens = lexer.lex(StringContent(src_052))
        val expected = TokenTypes.IF

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test053_elseTokenTest() {
        val tokens = lexer.lex(StringContent(src_053))
        val expected = TokenTypes.ELSE

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test054_readInputTokenTest() {
        val tokens = lexer.lex(StringContent(src_054))
        val expected = TokenTypes.READINPUT

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test055_booleanTokenTest() {
        val tokens = lexer.lex(StringContent(src_055))
        val expected = TokenTypes.TYPEBOOLEAN

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }


    @Test
    fun test057_numberTokenTest() {
        val tokens = lexer.lex(StringContent(src_057))
        val expected = TokenTypes.TYPENUMBER

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test058_stringTokenTest() {
        val tokens = lexer.lex(StringContent(src_058))
        val expected = TokenTypes.TYPESTRING

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test059_trueTokenTest() {
        val tokens = lexer.lex(StringContent(src_059))
        val expected = TokenTypes.BOOLEAN

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test060_falseTokenTest() {
        val tokens = lexer.lex(StringContent(src_060))
        val expected = TokenTypes.BOOLEAN

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test061_openBracesTokenTest() {
        val tokens = lexer.lex(StringContent(src_061))
        val expected = TokenTypes.LEFTBRACKET

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }

    @Test
    fun test062_closeBracesTokenTest() {
        val tokens = lexer.lex(StringContent(src_062))
        val expected = TokenTypes.RIGHTBRACKET

        assertEquals(2, tokens.size)
        assertEquals(expected, tokens[0].type)
    }
}

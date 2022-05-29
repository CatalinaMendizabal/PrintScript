import PrintScript.lexer.inputContent.StringContent
import edu.austral.ingsis.g3.lexer.DefaultRegexLexer
import edu.austral.ingsis.g3.lexer.Lexer
import edu.austral.ingsis.g3.lexer.lexerEnums.Version
import edu.austral.ingsis.g3.lexer.matcher.MatchProvider
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class LexTest {

    private val lexer: Lexer

    init {
        val matchers = MatchProvider.getMatchers(Version.V1_0)
        lexer = DefaultRegexLexer(matchers, Version.V1_0)
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
}

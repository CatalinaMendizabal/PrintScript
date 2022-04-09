import PrintScript.lexer.Lexer
import PrintScript.lexer.LexerImplementation
import PrintScript.lexer.inputContent.StringContent
import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token
import org.junit.Test
import kotlin.test.assertEquals

class LexerImplTest {

//    @Test
//    fun lexerLexGiven_FileContent_ShouldReturnCorrectTokens() {
//        val file = File("/Users/catamendizabal/projects/ing-sis/PrintScript/ideas")
//        val list = LexerImplementation().lex(FileContent(file))
//        // val str = StringContent(list)
//        println("TEST 001")
//        assertEquals(17, list.size)
//    }

   private val lexer: Lexer = LexerImplementation()

    @Test
   fun test001_stringDeclarationTest() {
       val tokens = lexer.lex(StringContent(src_001))
       val expected = tokens_001.toString()

       assertEquals(8, tokens.size)
       assertEquals(expected, tokens.toString())
   }

    @Test
    fun test002_stringDeclarationAssignationTest() {
        val tokens = lexer.lex(StringContent(src_002))
        val expected = tokens_002.toString()

        assertEquals(12, tokens.size)
        assertEquals(expected, tokens.toString())
    }

    @Test
    fun test003_numberDeclarationAssignationTest() {
        val tokens = lexer.lex(StringContent(src_003))
        val expected = tokens_003.toString()

        assertEquals(12, tokens.size)
        assertEquals(expected, tokens.toString())
    }


}

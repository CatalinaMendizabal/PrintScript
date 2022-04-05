import PrintScript.lexer.LexerImplementation
import PrintScript.lexer.inputContent.FileContent
import PrintScript.lexer.inputContent.StringContent
import junit.framework.TestCase
import org.junit.Test
import java.io.File

class LexerImplTest {

        @Test
        fun lexerLexGiven_FileContent_ShouldReturnCorrectTokens() {
            val file = File("/Users/catamendizabal/projects/ing-sis/PrintScript/ideas")
            val list = LexerImplementation().lex(FileContent(file))
            // val str = StringContent(list)
            println("TEST 001")
            TestCase.assertEquals(17, list.size)
        }

        @Test
        fun lexerLexGiven_StringContent_ShouldReturnCorrectTokens() {
            val myString = "let a = 3;\n" +
                    "let b = 4;"
            val list = LexerImplementation().lex(StringContent(myString))
            println("TEST 002")
            list.forEach(::println)
            TestCase.assertEquals(17, list.size)
        }

}
package PrintScript.lexer

import PrintScript.lexer.inputContent.FileContent
import PrintScript.lexer.inputContent.StringContent
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals


class LexerImplementationTest {

    @Test
    fun lexerLexGiven_FileContent_ShouldReturnCorrectTokens() {
        val file = File("/Users/catamendizabal/projects/ing-sis/prinscript/app/src/main/kotlin/PrintScript/ideas")
        val list = LexerImplementation().lex(FileContent(file))
        //  val str = StringContent(list)
        println(list)
        assertEquals(16, list.size)
    }

    @Test
    fun lexerLexGiven_StringContent_ShouldReturnCorrectTokens() {
        val myString = "let a = 3;\n" +
                        "let b = 4;"
        val list = LexerImplementation().lex(StringContent(myString))
        println(list)
        assertEquals(16, list.size)
    }
}

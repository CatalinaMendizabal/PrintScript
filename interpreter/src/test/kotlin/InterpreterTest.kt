import edu.austral.ingsis.g3.interpreter.Interpreter
import edu.austral.ingsis.g3.parser.Parser
import edu.austral.ingsis.g3.parser.PrintParser
import expression.Variable
import kotlin.test.assertEquals
import org.austral.ingsis.printscript.parser.TokenIterator
import org.junit.jupiter.api.Test

class InterpreterTest {

    @Test
    fun test_001_Interpret() {
        val parser: Parser<Print> = PrintParser(TokenIterator.create("println('hola')", token_008))
        val interpreter = Interpreter().interpret(parser.parse())
        assertEquals("'hola'", interpreter.readLine())
    }

    @Test
    fun test_002() {
        val interpreter = Interpreter()
        val print = Print(Variable("3"))
        assertEquals("3", interpreter.interpret(print).readLine())
    }
}

import edu.austral.ingsis.g3.interpreter.DefaultReadInputProvider
import edu.austral.ingsis.g3.interpreter.Interpreter
import edu.austral.ingsis.g3.parser.Parser
import edu.austral.ingsis.g3.parser.ParserImplementation
import edu.austral.ingsis.g3.parser.PrintParser
import expression.Operand
import expression.Operation
import expression.ReadInput
import expression.Variable
import kotlin.test.assertEquals
import node.Node
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

    @Test
    fun test_003() {
        val parser: Parser<Node> = ParserImplementation(
            TokenIterator.create(
                "let variable: string = readInput('hola' + readInput(' mundo') + '!');",
                token_014
            )
        )
        val code = CodeBlock()
        code.addChild(
            Declaration(
                "variable",
                "string",
                ReadInput(
                    Operation(
                        Operation(
                            Variable("'hola'"),
                            Operand.SUM,
                            ReadInput(Variable("' mundo'"))
                        ),
                        Operand.SUM,
                        Variable("'!'")
                    )
                )
            )
        )
        val interpreter = Interpreter()
        assertEquals("'hola mundo!'", interpreter.interpret(parser.parse()).readLine())
    }
}

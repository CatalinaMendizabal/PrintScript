
import ast.expression.Operand
import ast.expression.Operation
import ast.expression.Variable
import interpreter.exception.UndeclaredVariableException
import interpreter.solverVisitor.SolverVisitorV1
import java.util.HashMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class SolverVisitorTest {
    @Test
    fun test001_WhenReceivingSimpleAdditionShouldReturnCorrectResult() {
        val input = Operation(Variable("1"), Operand.SUM, Variable("2"))
        val expected = "3.0"
        val visitor = SolverVisitorV1()
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test002_WhenReceivingAdditionAndMultiplicationOperationShouldReturnCorrectResult() {
        val input = Operation(
            Variable("1"),
            Operand.SUM,
            Operation(Variable("2"), Operand.MUL, Variable("3"))
        )
        val expected = "7.0"
        val visitor = SolverVisitorV1()
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test003_WhenReceivingComplexNumericalOperationShouldReturnCorrectResult() {
        val input = Operation(
            Operation(Variable("5"), Operand.DIV, Variable("2")),
            Operand.SUM,
            Operation(
                Variable("2"),
                Operand.MUL,
                Operation(Variable("3"), Operand.SUB, Variable("4"))
            )
        )
        val expected = "0.5"
        val visitor = SolverVisitorV1()
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test004_WhenReceivingSimpleNumberAndVariableOperationShouldReturnCorrectResult() {
        val input = Operation(Variable("aNumber"), Operand.SUM, Variable("2"))
        val expected = "7.0"
        val variables = HashMap<String, String>()
        variables["aNumber"] = "5"
        val visitor = SolverVisitorV1(variables)
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test005_WhenReceivingComplexNumberAndVariableOperationShouldReturnCorrectResult() {
        val input = Operation(
            Operation(Variable("aNumber"), Operand.DIV, Variable("2")),
            Operand.SUM,
            Operation(
                Variable("2"),
                Operand.MUL,
                Operation(Variable("anotherNumber"), Operand.SUB, Variable("4"))
            )
        )
        val expected = "0.5"
        val variables = HashMap<String, String>()
        variables["aNumber"] = "5"
        variables["anotherNumber"] = "3"
        val visitor = SolverVisitorV1(variables)
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test006_WhenReceivingStringConcatenationShouldReturnCorrectResult() {
        val input = Operation(Variable("'Hello'"), Operand.SUM, Variable("\" world!\""))
        val expected = "Hello world!"
        val visitor = SolverVisitorV1()
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test007_WhenReceivingStringConcatenationWithVariableShouldReturnCorrectResult() {
        val input = Operation(
            Variable("'Hello'"),
            Operand.SUM,
            Operation(Variable("aString"), Operand.SUM, Variable("\"!!!\""))
        )
        val expected = "\"Hello world!!!\""
        val variables = HashMap<String, String>()
        variables["aString"] = "\" world\""
        val visitor = SolverVisitorV1(variables)
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test008_WhenReceivingStringConcatenationWithNumberShouldReturnCorrectResult() {
        val input = Operation(
            Variable("'Hello'"),
            Operand.SUM,
            Operation(Variable("5.12"), Operand.SUM, Variable("\"!!!\""))
        )
        val expected = "\"Hello5.12!!!\""
        val visitor = SolverVisitorV1()
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test009_WhenReceivingStringsNumbersAndVariablesOperationShouldReturnCorrectResult() {
        val input = Operation(
            Operation(Variable("'Hello'"), Operand.SUM, Variable("\" world!\"")),
            Operand.SUM,
            Operation(
                Operation(Variable("5.5"), Operand.MUL, Variable("2")),
                Operand.SUM,
                Variable("aString")
            )
        )
        val expected = "\"Hello world!11!!!\""
        val variables = HashMap<String, String>()
        variables["aString"] = "\"!!!\""
        val visitor = SolverVisitorV1(variables)
        input.accept(visitor)
        val actual: String = visitor.result
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test010_WhenReceivingUndeclaredVariableShouldThrowException() {
        val input = Operation(Variable("aVariable"), Operand.SUM, Variable("\"2\""))
        val visitor = SolverVisitorV1()
        Assertions.assertThrows(UndeclaredVariableException::class.java, Executable { input.accept(visitor) })
    }

    @Test
    fun test011_WhenReceivingUndeclaredVariableShouldThrowException() {
        val input = Operation(Variable("aVariable"), Operand.SUM, Variable("2"))
        val visitor = SolverVisitorV1()
        Assertions.assertThrows(UndeclaredVariableException::class.java, Executable { input.accept(visitor) })
    }

    @Test
    fun test012_WhenReceivingUndeclaredVariablesShouldThrowException() {
        val input = Operation(Variable("aVariable"), Operand.SUM, Variable("anotherVariable"))
        val visitor = SolverVisitorV1()
        Assertions.assertThrows(UndeclaredVariableException::class.java, Executable { input.accept(visitor) })
    }
}

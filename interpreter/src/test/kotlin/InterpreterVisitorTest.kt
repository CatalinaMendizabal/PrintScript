import ast.expression.Operand
import ast.expression.Operation
import ast.expression.Variable
import ast.node.Assignment
import ast.node.CodeBlock
import ast.node.Declaration
import ast.node.Print
import interpreter.interpreterVisitor.InterpreterVisitorV1
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class InterpreterVisitorTest {
    @Test
    fun test001_WhenVisitingAPrintStatementThenItShouldBeWrittenToResult() {
        val visitor = InterpreterVisitorV1()
        val printStatement = Print(Variable("45"))
        printStatement.accept(visitor)
        assertEquals("45", visitor.result.read())
    }

    @Test
    fun test002_WhenVisitingAPrintStatementWithAnExpressionThenItsResultShouldBeWritten() {
        val visitor = InterpreterVisitorV1()
        val printStatement = Print(Operation(Variable("45"), Operand.SUM, Variable("5")))
        printStatement.accept(visitor)
        assertEquals("50.0", visitor.result.read())
    }

    @Test
    fun test003_WhenVisitingAPrintStatementWithAMixedExpressionThenItsResultShouldBeWritten() {
        val visitor = InterpreterVisitorV1()
        val printStatement = Print(
            Operation(
                Variable("100"),
                Operand.SUM,
                Operation(Variable("\"Hello\""), Operand.SUM, Variable("\" world!\""))
            )
        )
        printStatement.accept(visitor)
        assertEquals("100Hello world!", visitor.result.read())
    }

    @Test
    fun test004_WhenReceivingFullCodeBlockThenPrintStatementsShouldBeWrittenToResult() {
        val visitor = InterpreterVisitorV1()
        val program = CodeBlock()
        program.addChild(Print(Variable("45")))
        program.addChild(Declaration("x", "number", false, Variable("2")))
        program.addChild(Print(Variable("x")))
        program.addChild(Declaration("y", "number"))
        program.addChild(Assignment("y", Operation(Variable("x"), Operand.MUL, Variable("3"))))
        program.addChild(Print(Variable("y")))
        program.addChild(Declaration("string", "string", false, Variable("\"Hello\"")))
        program.addChild(Print(Operation(Variable("string"), Operand.SUM, Variable("\" world!\""))))
        program.accept(visitor)
        assertEquals("4526.0Hello world!", visitor.result.read())
    }
}

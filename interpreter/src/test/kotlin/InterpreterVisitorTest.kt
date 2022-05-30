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
        assertEquals("50", visitor.result.read())
    }

    /*
    TODO
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
        assertEquals("4526Hello world!", visitor.result.read())
    }*/

   /* @Test
    fun test005_WhenReceivingFullCodeBlockThenPrintStatementsShouldBeWrittenToResultV1_1() {
        val visitor = InterpreterVisitorV1_1({ input -> "Hello" }) { s -> }
        val program = CodeBlock()
        program.addChild(Print(Variable("45")))
        program.addChild(Declaration("x", "number", false, Variable("2")))
        program.addChild(Print(Variable("x")))
        program.addChild(Declaration("y", "number"))
        program.addChild(Assignment("y", Expression(Variable("x"), Operand.MUL, Variable("3"))))
        program.addChild(Print(Variable("y")))
        program.addChild(
            Declaration("string", "string", false, ReadInput(Variable("\"Enter a string: \"")))
        )
        program.addChild(Print(Expression(Variable("string"), Operand.SUM, Variable("\" world!\""))))
        val ifBlock = CodeBlock()
        ifBlock.addChild(Print(Variable("\" Entered if! \"")))
        program.addChild(IfBlock(Variable("true"), ifBlock, CodeBlock()))
        program.addChild(Declaration("aBoolean", "boolean", true, Variable("false")))
        val elseBlock = CodeBlock()
        elseBlock.addChild(Print(Variable("\" Entered else! \"")))
        program.addChild(IfBlock(Variable("aBoolean"), CodeBlock(), elseBlock))
        program.accept(visitor)
        assertEquals("4526Hello world! Entered if!  Entered else! ", visitor.getResult().read())
    }*/
}

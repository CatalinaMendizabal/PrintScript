import expression.Expression
import expression.Function
import expression.Operand
import expression.Variable
import node.Node
import org.austral.ingsis.printscript.parser.TokenIterator
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParserTestImpl {
    @Test
    fun assignmentParserTestForSingleLiteral() {
        val parser: Parser<Assignment> = AssignmentParser(TokenIterator.Companion.create("a = 5", token_001))
        val assignment = Assignment("a", Variable("5"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleIdentifier() {
        val parser: Parser<Assignment> = AssignmentParser(TokenIterator.create("a = b", token_002))
        val assignment = Assignment("a", Variable("b"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleStringLiteral() {
        val parser: Parser<Assignment> = AssignmentParser(TokenIterator.create("a = 'a'", token_003))
        val assignment = Assignment("a", Variable("'a'"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForSimpleSum() {
        val parser: Parser<Function> = FunctionParser(TokenIterator.create("2 + 3", token_004))
        val expression = Expression(Variable("2"), Operand.SUM, Variable("3"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberOperation() {
        val parser: Parser<Function> = FunctionParser(TokenIterator.create("2 + 3 * 4 - 10 / 5", token_005))
        val expression = Expression(
            Expression(Variable("2"), Operand.SUM, Expression(Variable("3"), Operand.MUL, Variable("4"))),
            Operand.SUB,
            Expression(
                Variable("10"), Operand.DIV, Variable("5")
            )
        )
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberAndVariableOperation() {
        val parser: Parser<Function> = FunctionParser(TokenIterator.create("2 + a", token_006))
        val expression = Expression(Variable("2"), Operand.SUM, Variable("a"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForMixedOperation() {
        val parser: Parser<Function> = FunctionParser(TokenIterator.create("2 + a * 'hola' - 8", token_007))
        val expression = Expression(
            Expression(
                Variable("2"),
                Operand.SUM,
                Expression(Variable("a"), Operand.MUL, Variable("'hola'"))
            ),
            Operand.SUB,
            Variable("8")
        )
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
//    @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun printParserTest() {
        val parser: Parser<Print> = PrintParser(
            TokenIterator.create(
                "println('hola')",
                token_008
            )
        )
        val print = Print(Variable("'hola'"))
        assertEquals(print.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitialization() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a:Number;", token_009))
        val declaration = Declaration("a", "Number")
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitializationAndAssignment() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a:Number = 8;", token_010))
        val declaration = Declaration("a", "Number", Variable("8"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun testNumberAssignation() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a: Number = 2.123;", token_6))
        val declaration = Declaration("a", "Number", Variable("2.123"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserWithMissingTypeSeparatorShouldThrowExceptions() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let x Number", token_011))
        assertFailsWith<ParserException> {
            parser.parse()
        }
    }

    @Test
    fun defaultParserTestForNonValidBlockShouldThrowException() {
        val parser: Parser<Node> = ParserImplementation(TokenIterator.create("String", token_012))
        assertFailsWith<ParserException> {
            print(parser.parse())
        }
    }
}

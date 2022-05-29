
import edu.austral.ingsis.g3.parser.AssignmentParser
import edu.austral.ingsis.g3.parser.function.FunctionParserV1
import edu.austral.ingsis.g3.parser.Parser
import expression.Variable
import kotlin.test.assertEquals
import org.austral.ingsis.printscript.parser.TokenIterator
import org.junit.jupiter.api.Test

class ParserTestImpl {
    @Test
    fun assignmentParserTestForSingleLiteral() {
        val tokenIterator = TokenIterator.create("a = 5", token_001)
        val parser: Parser<Assignment> = AssignmentParser(tokenIterator, FunctionParserV1(tokenIterator))
        val assignment = Assignment("a", Variable("5"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

   /* @Test
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
        val parser: Parser<Expression> = FunctionParser(TokenIterator.create("2 + 3", token_004))
        val operation = Operation(Variable("2"), Operand.SUM, Variable("3"))
        assertEquals(operation.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberOperation() {
        val parser: Parser<Expression> = FunctionParser(TokenIterator.create("2 + 3 * 4 - 10 / 5", token_005))
        val operation = Operation(
            Operation(Variable("2"), Operand.SUM, Operation(Variable("3"), Operand.MULTIPLY, Variable("4"))),
            Operand.SUBSTRACT,
            Operation(
                Variable("10"), Operand.DIVIDE, Variable("5")
            )
        )
        assertEquals(operation.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberAndVariableOperation() {
        val parser: Parser<Expression> = FunctionParser(TokenIterator.create("2 + a", token_006))
        val operation = Operation(Variable("2"), Operand.SUM, Variable("a"))
        assertEquals(operation.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForMixedOperation() {
        val parser: Parser<Expression> = FunctionParser(TokenIterator.create("2 + a * 'hola' - 8", token_007))
        val operation = Operation(
            Operation(
                Variable("2"),
                Operand.SUM,
                Operation(Variable("a"), Operand.MULTIPLY, Variable("'hola'"))
            ),
            Operand.SUBSTRACT,
            Variable("8")
        )
        assertEquals(operation.toString(), parser.parse().toString())
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
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a:number;", token_009))
        val declaration = Declaration("a", "number")
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitializationAndAssignment() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a:number = 8;", token_010))
        val declaration = Declaration("a", "number", Variable("8"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun testNumberAssignation() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let a: number = 2.123;", token_6))
        val declaration = Declaration("a", "number", Variable("2.123"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserWithMissingTypeSeparatorShouldThrowExceptions() {
        val parser: Parser<Declaration> = DeclarationParser(TokenIterator.create("let x number", token_011))
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

    @Test
    fun parserTestV1_1() {
        // "if(false){let variable: string = 'Hello World!';}else{ \nconst aBoolean:boolean=true;};if(aBoolean){let variable: string = readInput('hola' + readInput(' mundo') + '!');};",
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
        val b = parser.parse().toString()
        assertEquals(code.toString(), b)
    }*/
}

import ast.expression.Expression
import ast.expression.Operand
import ast.expression.Operation
import ast.expression.Variable
import ast.node.Assignment
import ast.node.CodeBlock
import ast.node.Declaration
import ast.node.Node
import ast.node.Print
import enums.TokenTypes
import kotlin.test.assertEquals
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator.Companion.create
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import parser.AssignmentParser
import parser.Parser
import parser.PrintParser
import parser.ProgramParserV1
import parser.declaration.DeclarationParserV1
import parser.exceptions.UnexpectedTokenException
import parser.function.FunctionParserV1

internal class ParserTest {
    @Test
    fun assignmentParserTestForSingleLiteral() {
        val tokenIterator = create(src_001, token_001)
        val parser: Parser<Assignment> = AssignmentParser(tokenIterator, FunctionParserV1(tokenIterator))
        val assignment = Assignment("a", Variable("5"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleIdentifier() {
        val tokenIterator = create(src_002, token_002)
        val parser: Parser<Assignment> = AssignmentParser(
            tokenIterator,
            FunctionParserV1(tokenIterator)
        )
        val assignment = Assignment("a", Variable("b"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleStringLiteral() {
        val tokenIterator = create(src_003, token_003)
        val parser: Parser<Assignment> = AssignmentParser(
            tokenIterator,
            FunctionParserV1(tokenIterator)
        )
        val assignment = Assignment("a", Variable("'a'"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForSimpleSum() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_004, token_004)
        )
        val expression = Operation(Variable("2"), Operand.SUM, Variable("3"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_005, token_005)
        )
        val expression = Operation(
            Operation(
                Variable("2"),
                Operand.SUM,
                Operation(Variable("3"), Operand.MUL, Variable("4"))
            ),
            Operand.SUB,
            Operation(
                Variable("10"),
                Operand.DIV,
                Variable("5")
            )
        )
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberAndVariableOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_006, token_006)
        )
        val expression = Operation(Variable("2"), Operand.SUM, Variable("a"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForMixedOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(
                "2 + a * 'hola' - 8",
                listOf(
                    Token(TokenTypes.NUMBER, 0, 1, LexicalRange(0, 0, 1, 0)),
                    Token(TokenTypes.SUM, 2, 3, LexicalRange(2, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.MULTIPLY, 6, 7, LexicalRange(6, 0, 7, 0)),
                    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
                    Token(TokenTypes.SUBSTRACT, 15, 16, LexicalRange(15, 0, 16, 0)),
                    Token(TokenTypes.NUMBER, 17, 18, LexicalRange(17, 0, 18, 0))
                )
            )
        )
        val expression = Operation(
            Operation(
                Variable("2"),
                Operand.SUM,
                Operation(Variable("a"), Operand.MUL, Variable("'hola'"))
            ),
            Operand.SUB,
            Variable("8")
        )
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun printParserTest() {
        val parser: Parser<Print> = PrintParser(
            create(
                "println('hola')",
                listOf(
                    Token(TokenTypes.PRINTLN, 0, 7, LexicalRange(0, 0, 7, 0)),
                    Token(TokenTypes.LEFTPARENTHESIS, 7, 8, LexicalRange(7, 0, 8, 0)),
                    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
                    Token(TokenTypes.RIGHTPARENTHESIS, 14, 15, LexicalRange(14, 0, 15, 0))
                )
            )
        )
        val print = Print(Variable("'hola'"))
        assertEquals(print.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitialization() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let a:number;",
                listOf(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
                    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
                    Token(TokenTypes.SEMICOLON, 12, 13, LexicalRange(12, 0, 13, 0))
                )
            )
        )
        val declaration = Declaration("a", "number")
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitializationAndAssignment() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let a:number = 8;",
                listOf(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
                    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
                    Token(TokenTypes.EQUAL, 13, 14, LexicalRange(13, 0, 14, 0)),
                    Token(TokenTypes.NUMBER, 15, 16, LexicalRange(15, 0, 16, 0)),
                    Token(TokenTypes.SEMICOLON, 16, 17, LexicalRange(16, 0, 17, 0))
                )
            )
        )
        val declaration = Declaration("a", "number", false, Variable("8"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun defaultParserTestForSimpleCodeBlockTest() {
        val parser: Parser<Node> = ProgramParserV1(create(src_007, token_007))
        val codeBlock = CodeBlock()
        codeBlock.addChild(Declaration("a", "number", false, Variable("8")))
        codeBlock.addChild(Declaration("b", "string", false, Variable("'8'")))
        codeBlock.addChild(
            Assignment(
                "a",
                Operation(Variable("a"), Operand.SUM, Variable("b"))
            )
        )
        codeBlock.addChild(Print(Variable("a")))
        assertEquals(codeBlock.toString(), parser.parse().toString())
    }

    @Test
    fun defaultParserTestForNonValidBlockShouldThrowException() {
        val parser: Parser<Node> = ProgramParserV1(
            create(
                "number",
                listOf(
                    Token(TokenTypes.TYPENUMBER, 0, 6, LexicalRange(0, 0, 6, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun declarationParserWithMissingIdentifierShouldThrowExceptions() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let :",
                listOf(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.COLON, 4, 5, LexicalRange(4, 0, 5, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun declarationParserWithMissingTypeSeparatorShouldThrowExceptions() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let x number",
                listOf(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.NUMBER, 6, 12, LexicalRange(6, 0, 12, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun declarationParserWithMissingTypeShouldThrowExceptions() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let x : ;",
                listOf(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.COLON, 6, 7, LexicalRange(6, 0, 7, 0)),
                    Token(TokenTypes.SEMICOLON, 8, 9, LexicalRange(8, 0, 9, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun printParserWithMissingLeftParenthesisShouldThrowException() {
        val parser: Parser<Print> = PrintParser(
            create(
                "println 'hola')",
                listOf(
                    Token(TokenTypes.PRINTLN, 0, 7, LexicalRange(0, 0, 7, 0)),
                    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
                    Token(TokenTypes.RIGHTPARENTHESIS, 14, 15, LexicalRange(14, 0, 15, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun printParserWithMissingRightParenthesisShouldThrowException() {
        val parser: Parser<Print> = PrintParser(
            create(
                "println('hola'",
                listOf(
                    Token(TokenTypes.PRINTLN, 0, 7, LexicalRange(0, 0, 7, 0)),
                    Token(TokenTypes.LEFTPARENTHESIS, 7, 8, LexicalRange(7, 0, 8, 0)),
                    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0))
                )
            )
        )
        Assertions.assertThrows(
            UnexpectedTokenException::class.java
        ) { parser.parse() }
    }

    @Test
    fun parseTestV2() {
    }
}

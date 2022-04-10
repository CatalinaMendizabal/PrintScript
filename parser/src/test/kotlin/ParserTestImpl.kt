
import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import expression.Operand
import expression.Variable
import org.austral.ingsis.printscript.common.LexicalRange
import org.austral.ingsis.printscript.common.Token
import org.austral.ingsis.printscript.parser.TokenIterator
import org.junit.Test
import kotlin.test.assertEquals
import expression.Function
import kotlin.test.assertFailsWith


class ParserTestImpl {
    @Test
   // @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun assignmentParserTestForSingleLiteral() {
        val parser: Parser<Assignment> = AssignmentParser(
            TokenIterator.Companion.create(
                "a = 5",
                listOf(
                    Token(
                        Types.IDENTIFIER,
                        0,
                        1,
                        LexicalRange(0, 0, 3, 0)
                    ),
                    Token(
                        Types.EQUAL,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 1)
                    ),
                    Token(
                        Types.LITERAL,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 1)
                    )
                )
            )
        )
        val assignment = Assignment("a", Variable("5"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleIdentifier() {
        val parser: Parser<Assignment> = AssignmentParser(
            TokenIterator.create(
                "a = b",
                listOf(
                    Token(
                        Types.IDENTIFIER,
                        0,
                        1,
                        LexicalRange(0, 0, 3, 0)
                    ),
                    Token(
                        Types.EQUAL,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 1)
                    ),
                    Token(
                        Types.IDENTIFIER,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 1)
                    )
                )
            )
        )
        val assignment = Assignment("a", Variable("b"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
   // @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun assignmentParserTestForSingleStringLiteral() {
        val parser: Parser<Assignment> = AssignmentParser(
            TokenIterator.create(
                "a = 'a'",
                listOf(
                    Token(
                        Types.IDENTIFIER,
                        0,
                        1,
                        LexicalRange(0, 0, 3, 0)
                    ),
                    Token(
                        Types.EQUAL,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 1)
                    ),
                    Token(
                        Types.LITERAL,
                        4,
                        7,
                        LexicalRange(4, 0, 7, 1)
                    )
                )
            )
        )
        val assignment = Assignment("a", Variable("'a'"))
        assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
 //   @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun expressionParserTestForSimpleSum() {
        val parser: Parser<Function> = FunctionParser(
            TokenIterator.create(
                "2 + 3",
                listOf(
                    Token(
                        Types.LITERAL,
                        0,
                        1,
                        LexicalRange(0, 0, 1, 0)
                    ),
                    Token(
                        Types.SUM,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    )
                )
            )
        )
        val expression = Expression(Variable("2"), Operand.SUM, Variable("3"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
   // @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun expressionParserTestForNumberOperation() {
        val parser: Parser<Function> = FunctionParser(
            TokenIterator.create(
                "2 + 3 * 4 - 10 / 5",
                listOf(
                    Token(
                        Types.LITERAL,
                        0,
                        1,
                        LexicalRange(0, 0, 1, 0)
                    ),
                    Token(
                        Types.SUM,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    ),
                    Token(
                        Types.MULTIPLY,
                        6,
                        7,
                        LexicalRange(6, 0, 7, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        8,
                        9,
                        LexicalRange(8, 0, 9, 0)
                    ),
                    Token(
                        Types.SUBSTRACT,
                        10,
                        11,
                        LexicalRange(10, 0, 11, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        12,
                        14,
                        LexicalRange(12, 0, 14, 0)
                    ),
                    Token(
                        Types.DIVIDE,
                        15,
                        16,
                        LexicalRange(15, 0, 16, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        17,
                        18,
                        LexicalRange(17, 0, 18, 0)
                    )
                )
            )
        )
        val expression = Expression(
            Expression(
                Variable("2"),
                Operand.SUM,
                Expression(Variable("3"), Operand.MUL, Variable("4"))
            ),
            Operand.SUB,
            Expression(
                Variable("10"),
                Operand.DIV,
                Variable("5")
            )
        )
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
  //  @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun expressionParserTestForNumberAndVariableOperation() {
        val parser: Parser<Function> = FunctionParser(
            TokenIterator.create(
                "2 + a",
                listOf(
                    Token(
                        Types.LITERAL,
                        0,
                        1,
                        LexicalRange(0, 0, 1, 0)
                    ),
                    Token(
                        Types.SUM,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 0)
                    ),
                    Token(
                        Types.IDENTIFIER,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    )
                )
            )
        )
        val expression = Expression(Variable("2"), Operand.SUM, Variable("a"))
        assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
 //   @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun expressionParserTestForMixedOperation() {
        val parser: Parser<Function> = FunctionParser(
            TokenIterator.create(
                "2 + a * 'hola' - 8",
                listOf(
                    Token(
                        Types.LITERAL,
                        0,
                        1,
                        LexicalRange(0, 0, 1, 0)
                    ),
                    Token(
                        Types.SUM,
                        2,
                        3,
                        LexicalRange(2, 0, 3, 0)
                    ),
                    Token(
                        Types.IDENTIFIER,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    ),
                    Token(
                        Types.MULTIPLY,
                        6,
                        7,
                        LexicalRange(6, 0, 7, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        8,
                        14,
                        LexicalRange(8, 0, 14, 0)
                    ),
                    Token(
                        Types.SUBSTRACT,
                        15,
                        16,
                        LexicalRange(15, 0, 16, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        17,
                        18,
                        LexicalRange(17, 0, 18, 0)
                    )
                )
            )
        )
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
                listOf(
                    Token(
                        Types.PRINT,
                        0,
                        7,
                        LexicalRange(0, 0, 7, 0)
                    ),
                    Token(
                        Types.LEFTPARENTHESIS,
                        7,
                        8,
                        LexicalRange(7, 0, 8, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        8,
                        14,
                        LexicalRange(8, 0, 14, 0)
                    ),
                    Token(
                        Types.RIGHTPARENTHESIS,
                        14,
                        15,
                        LexicalRange(14, 0, 15, 0)
                    )
                )
            )
        )
        val print = Print(Variable("'hola'"))
        assertEquals(print.toString(), parser.parse().toString())
    }

    @Test
  //  @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun declarationParserTestForInitialization() {
        val parser: Parser<Declaration> = DeclarationParser(
            TokenIterator.create(
                "let a:number;",
                listOf(
                    Token(
                        Types.LET,
                        0,
                        3,
                        LexicalRange(0, 0, 3, 0)
                    ),
                    Token(
                        Types.IDENTIFIER,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    ),
                    Token(
                        Types.COLON,
                        5,
                        6,
                        LexicalRange(5, 0, 6, 0)
                    ),
                    Token(
                        Types.NUMBERTYPE,
                        6,
                        12,
                        LexicalRange(6, 0, 12, 0)
                    ),
                    Token(
                        Types.SEMICOLON,
                        12,
                        13,
                        LexicalRange(12, 0, 13, 0)
                    )
                )
            )
        )
        val declaration = Declaration("a", "number")
        assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
  //  @Throws(UnexpectedTokenException::class, UnexpectedKeywordException::class)
    fun declarationParserTestForInitializationAndAssignment() {
        val parser: Parser<Declaration> = DeclarationParser(
            TokenIterator.create(
                "let a:number = 8;",
                listOf(
                    Token(
                        Types.LET,
                        0,
                        3,
                        LexicalRange(0, 0, 3, 0)
                    ),
                    Token(
                        Types.IDENTIFIER,
                        4,
                        5,
                        LexicalRange(4, 0, 5, 0)
                    ),
                    Token(
                        Types.COLON,
                        5,
                        6,
                        LexicalRange(5, 0, 6, 0)
                    ),
                    Token(
                        Types.NUMBERTYPE,
                        6,
                        12,
                        LexicalRange(6, 0, 12, 0)
                    ),
                    Token(
                        Types.EQUAL,
                        13,
                        14,
                        LexicalRange(13, 0, 14, 0)
                    ),
                    Token(
                        Types.LITERAL,
                        15,
                        16,
                        LexicalRange(15, 0, 16, 0)
                    ),
                    Token(
                        Types.SEMICOLON,
                        16,
                        17,
                        LexicalRange(16, 0, 17, 0)
                    )
                )
            )
        )
        val declaration = Declaration("a", "number", Variable("8"))
        assertEquals(declaration.toString(), parser.parse().toString())
    }

        @Test
        fun declarationParserWithMissingTypeSeparatorShouldThrowExceptions() {
            val parser: Parser<Declaration> = DeclarationParser(
                TokenIterator.create(
                    "let x number",
                    listOf(
                        Token(
                            Types.LET,
                            0,
                            3,
                            LexicalRange(0, 0, 3, 0)
                        ),
                        Token(
                            Types.IDENTIFIER,
                            4,
                            5,
                            LexicalRange(4, 0, 5, 0)
                        ),
                        Token(
                            Types.NUMBERTYPE,
                            6,
                            12,
                            LexicalRange(6, 0, 12, 0)
                        )
                    )
                )
            )
            assertFailsWith<ParserException> {
                parser.parse()
            }
        }
}

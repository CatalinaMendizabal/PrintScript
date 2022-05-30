import ast.expression.Expression
import ast.expression.Operand
import ast.expression.Operation
import ast.expression.Variable
import ast.node.Assignment
import ast.node.Declaration
import ast.node.Node
import ast.node.Print
import enums.TokenTypes
import java.util.List
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
        Assertions.assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleIdentifier() {
        val tokenIterator = create(src_002, token_002)
        val parser: Parser<Assignment> = AssignmentParser(
            tokenIterator,
            FunctionParserV1(tokenIterator)
        )
        val assignment = Assignment("a", Variable("b"))
        Assertions.assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun assignmentParserTestForSingleStringLiteral() {
        val tokenIterator = create(src_003, token_003)
        val parser: Parser<Assignment> = AssignmentParser(
            tokenIterator,
            FunctionParserV1(tokenIterator)
        )
        val assignment = Assignment("a", Variable("'a'"))
        Assertions.assertEquals(assignment.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForSimpleSum() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_004, token_004))
        val expression = Operation(Variable("2"), Operand.SUM, Variable("3"))
        Assertions.assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_005, token_005))
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
        Assertions.assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForNumberAndVariableOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(src_006, token_006)
        )
        val expression = Operation(Variable("2"), Operand.SUM, Variable("a"))
        Assertions.assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun expressionParserTestForMixedOperation() {
        val parser: Parser<Expression> = FunctionParserV1(
            create(
                "2 + a * 'hola' - 8",
                List.of(
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
        Assertions.assertEquals(expression.toString(), parser.parse().toString())
    }

    @Test
    fun printParserTest() {
        val parser: Parser<Print> = PrintParser(
            create(
                "println('hola')",
                List.of(
                    Token(TokenTypes.PRINTLN, 0, 7, LexicalRange(0, 0, 7, 0)),
                    Token(TokenTypes.LEFTPARENTHESIS, 7, 8, LexicalRange(7, 0, 8, 0)),
                    Token(TokenTypes.STRING, 8, 14, LexicalRange(8, 0, 14, 0)),
                    Token(TokenTypes.RIGHTPARENTHESIS, 14, 15, LexicalRange(14, 0, 15, 0))
                )
            )
        )
        val print = Print(Variable("'hola'"))
        Assertions.assertEquals(print.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitialization() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let a:number;",
                List.of(
                    Token(TokenTypes.LET, 0, 3, LexicalRange(0, 0, 3, 0)),
                    Token(TokenTypes.IDENTIFIER, 4, 5, LexicalRange(4, 0, 5, 0)),
                    Token(TokenTypes.COLON, 5, 6, LexicalRange(5, 0, 6, 0)),
                    Token(TokenTypes.TYPENUMBER, 6, 12, LexicalRange(6, 0, 12, 0)),
                    Token(TokenTypes.SEMICOLON, 12, 13, LexicalRange(12, 0, 13, 0))
                )
            )
        )
        val declaration = Declaration("a", "number")
        Assertions.assertEquals(declaration.toString(), parser.parse().toString())
    }

    @Test
    fun declarationParserTestForInitializationAndAssignment() {
        val parser: Parser<Declaration> = DeclarationParserV1(
            create(
                "let a:number = 8;",
                List.of(
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
        Assertions.assertEquals(declaration.toString(), parser.parse().toString())
    }


    /*    @Test
    public void defaultParserTestForSimpleCodeBlockTest(){
        Parser<Node> parser =
            new ProgramParserV1_0(
                TokenIterator.Companion.create(
                    "let a:number = 8;\nlet b:string = '8';\na = a + b;\nprintln(a);",
                    List.of(
                        new Token(TokenTypes.LET, 0, 3, new LexicalRange(0, 0, 3, 0)),
                        new Token(TokenTypes.IDENTIFIER, 4, 5, new LexicalRange(4, 0, 5, 0)),
                        new Token(TokenTypes.COLON, 5, 6, new LexicalRange(5, 0, 6, 0)),
                        new Token(TokenTypes.NUMBER, 6, 12, new LexicalRange(6, 0, 12, 0)),
                        new Token(TokenTypes.EQUAL, 13, 14, new LexicalRange(13, 0, 14, 0)),
                        new Token(TokenTypes.NUMBER, 15, 16, new LexicalRange(15, 0, 16, 0)),
                        new Token(TokenTypes.SEMICOLON, 16, 17, new LexicalRange(16, 0, 17, 0)),
                        new Token(TokenTypes.LET, 18, 21, new LexicalRange(0, 1, 3, 1)),
                        new Token(TokenTypes.IDENTIFIER, 22, 23, new LexicalRange(4, 1, 5, 1)),
                        new Token(TokenTypes.COLON, 23, 24, new LexicalRange(5, 1, 6, 1)),
                        new Token(TokenTypes.TYPESTRING, 24, 30, new LexicalRange(6, 1, 12, 1)),
                        new Token(TokenTypes.EQUAL, 31, 32, new LexicalRange(13, 1, 14, 1)),
                        new Token(TokenTypes.STRING, 33, 36, new LexicalRange(15, 1, 16, 1)),
                        new Token(TokenTypes.SEMICOLON, 36, 37, new LexicalRange(16, 1, 17, 1)),
                        new Token(TokenTypes.IDENTIFIER, 38, 39, new LexicalRange(0, 2, 1, 2)),
                        new Token(TokenTypes.EQUAL, 40, 41, new LexicalRange(2, 2, 3, 2)),
                        new Token(TokenTypes.IDENTIFIER, 42, 43, new LexicalRange(4, 2, 5, 2)),
                        new Token(TokenTypes.SUM, 44, 45, new LexicalRange(6, 2, 7, 2)),
                        new Token(TokenTypes.IDENTIFIER, 46, 47, new LexicalRange(8, 2, 9, 2)),
                        new Token(TokenTypes.SEMICOLON, 47, 48, new LexicalRange(9, 2, 10, 2)),
                        new Token(TokenTypes.PRINTLN, 49, 56, new LexicalRange(0, 3, 7, 3)),
                        new Token(TokenTypes.LEFTPARENTHESIS, 56, 57, new LexicalRange(7, 3, 8, 3)),
                        new Token(TokenTypes.IDENTIFIER, 57, 58, new LexicalRange(8, 3, 9, 3)),
                        new Token(TokenTypes.RIGHTPARENTHESIS, 58, 59, new LexicalRange(9, 3, 10, 3)),
                        new Token(TokenTypes.SEMICOLON, 59, 60, new LexicalRange(10, 3, 11, 3))
                    )
                )
            );

        CodeBlock codeBlock = new CodeBlock();
        codeBlock.addChild(new Declaration("a", "number", false, new Variable("8")));
        codeBlock.addChild(new Declaration("b", "string", false, new Variable("'8'")));
        codeBlock.addChild(
            new Assignment(
                "a",
                new Expression(new Variable("a"), Operand.SUM, new Variable("b"))
            )
        );
        codeBlock.addChild(new Print(new Variable("a")));
        assertEquals(codeBlock.toString(), parser.parse().toString());
    }*/
    @Test
    fun defaultParserTestForNonValidBlockShouldThrowException() {
        val parser: Parser<Node> = ProgramParserV1(
            create(
                "number",
                List.of(
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
                List.of(
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
                List.of(
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
                List.of(
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
                List.of(
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
                List.of(
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
    } /*
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
    }

    @SneakyThrows
    @Test
    public void parserTestV1_1() {
        List<Token> tokens = getFromFile();
        Parser<Node> parser = new ProgramParserV1_1(
                TokenIterator.Companion.create(
                    "if(false){let variable: string = 'Hello World!';}else{ \nconst aBoolean:boolean=true;};if(aBoolean){let variable: string = readInput('hola' + readInput(' mundo') + '!');};",
                    tokens
                )
        );

        CodeBlock codeBlock = new CodeBlock();
        CodeBlock ifBlock = new CodeBlock();
        CodeBlock elseBlock = new CodeBlock();
        ifBlock.addChild(new Declaration("variable", "string", false, new Variable("'Hello World!'")));
        elseBlock.addChild(new Declaration("aBoolean", "boolean", true, new Variable("true")));
        CodeBlock ifBlock2 = new CodeBlock();
        ifBlock2.addChild(
            new Declaration(
                    "variable",
                    "string",
                    false,
                    new ReadInput(
                            new Expression(
                                    new Expression(
                                            new Variable("'hola'"),
                                            Operand.SUM,
                                            new ReadInput(new Variable("' mundo'"))
                                    ),
                                    Operand.SUM,
                                    new Variable("'!'")
                            )
                    )
            )
        );
        codeBlock.addChild(new IfBlock(new Variable("false"), ifBlock, elseBlock));
        codeBlock.addChild(new IfBlock(new Variable("aBoolean"), ifBlock2, new CodeBlock()));

     //   assertEquals(codeBlock.toString(), parser.parse().toString());
    }*/
    /* @SneakyThrows
    private List<Token> getFromFile() {
        List<Token> tokens = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("lexerOutput"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            tokens.add(
                new Token(
                        DefaultTokenTypes.valueOf(values[0]),
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]),
                        new LexicalRange(
                                Integer.parseInt(values[3]),
                                Integer.parseInt(values[4]),
                                Integer.parseInt(values[5]),
                                Integer.parseInt(values[6])
                        )
                )
            );
        }
        return tokens;
    }*/
}

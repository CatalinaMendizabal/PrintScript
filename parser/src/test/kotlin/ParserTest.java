import ast.expression.Operation;
import ast.expression.Expression;
import ast.node.Print;
import ast.node.Node;
import ast.node.Declaration;
import ast.expression.Operand;
import ast.node.Assignment;
import ast.expression.Variable;
import enums.TokenTypes;
import parser.declaration.DeclarationParserV1;
import parser.exceptions.UnexpectedTokenException;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.junit.jupiter.api.Test;
import parser.*;
import parser.function.FunctionParserV1;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    public void assignmentParserTestForSingleLiteral() {
        TokenIterator tokenIterator = TokenIterator.Companion.create(
            "a = 5",
            List.of(
                new Token(TokenTypes.IDENTIFIER, 0, 1, new LexicalRange(0, 0, 3, 0)),
                new Token(TokenTypes.EQUAL, 2, 3, new LexicalRange(2, 0, 3, 1)),
                new Token(TokenTypes.NUMBER, 4, 5, new LexicalRange(4, 0, 5, 1))
            )
        );
        Parser<Assignment> parser =
            new AssignmentParser(tokenIterator, new FunctionParserV1(tokenIterator));
        Assignment assignment = new Assignment("a", new Variable("5"));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void assignmentParserTestForSingleIdentifier() {
        TokenIterator tokenIterator = TokenIterator.Companion.create(
            "a = b",
            List.of(
                new Token(
                    TokenTypes.IDENTIFIER,
                    0,
                    1,
                    new LexicalRange(0, 0, 3, 0)
                ),
                new Token(
                    TokenTypes.EQUAL,
                    2,
                    3,
                    new LexicalRange(2, 0, 3, 1)
                ),
                new Token(
                    TokenTypes.IDENTIFIER,
                    4,
                    5,
                    new LexicalRange(4, 0, 5, 1)
                )
            )
        );
        Parser<Assignment> parser =
            new AssignmentParser(
                tokenIterator,
                new FunctionParserV1(tokenIterator)
            );
        Assignment assignment = new Assignment("a", new Variable("b"));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void assignmentParserTestForSingleStringLiteral() {
        TokenIterator tokenIterator = TokenIterator.Companion.create(
            "a = 'a'",
            List.of(
                new Token(
                    TokenTypes.IDENTIFIER,
                    0,
                    1,
                    new LexicalRange(0, 0, 3, 0)
                ),
                new Token(
                    TokenTypes.EQUAL,
                    2,
                    3,
                    new LexicalRange(2, 0, 3, 1)
                ),
                new Token(
                    TokenTypes.STRING,
                    4,
                    7,
                    new LexicalRange(4, 0, 7, 1)
                )
            )
        );
        Parser<Assignment> parser =
            new AssignmentParser(
                tokenIterator,
                new FunctionParserV1(tokenIterator)
            );
        Assignment assignment = new Assignment("a", new Variable("'a'"));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForSimpleSum() {
        Parser<Expression> parser =
            new FunctionParserV1(
                TokenIterator.Companion.create(
                    "2 + 3",
                    List.of(
                        new Token(
                            TokenTypes.NUMBER,
                            0,
                            1,
                            new LexicalRange(0, 0, 1, 0)
                        ),
                        new Token(
                            TokenTypes.SUM,
                            2,
                            3,
                            new LexicalRange(2, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        )
                    )
                )
            );
        Operation expression =
            new Operation(new Variable("2"), Operand.SUM, new Variable("3"));
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForNumberOperation() {
        Parser<Expression> parser =
            new FunctionParserV1(
                TokenIterator.Companion.create(
                    "2 + 3 * 4 - 10 / 5",
                    List.of(
                        new Token(
                            TokenTypes.NUMBER,
                            0,
                            1,
                            new LexicalRange(0, 0, 1, 0)
                        ),
                        new Token(
                            TokenTypes.SUM,
                            2,
                            3,
                            new LexicalRange(2, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.MULTIPLY,
                            6,
                            7,
                            new LexicalRange(6, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            8,
                            9,
                            new LexicalRange(8, 0, 9, 0)
                        ),
                        new Token(
                            TokenTypes.SUBSTRACT,
                            10,
                            11,
                            new LexicalRange(10, 0, 11, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            12,
                            14,
                            new LexicalRange(12, 0, 14, 0)
                        ),
                        new Token(
                            TokenTypes.DIVIDE,
                            15,
                            16,
                            new LexicalRange(15, 0, 16, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            17,
                            18,
                            new LexicalRange(17, 0, 18, 0)
                        )
                    )
                )
            );
        Operation expression =
            new Operation(
                new Operation(
                    new Variable("2"),
                    Operand.SUM,
                    new Operation(new Variable("3"), Operand.MUL, new Variable("4"))
                ),
                Operand.SUB,
                new Operation(
                    new Variable("10"),
                    Operand.DIV,
                    new Variable("5")
                )
            );
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForNumberAndVariableOperation() {
        Parser<Expression> parser =
            new FunctionParserV1(
                TokenIterator.Companion.create(
                    "2 + a",
                    List.of(
                        new Token(
                            TokenTypes.NUMBER,
                            0,
                            1,
                            new LexicalRange(0, 0, 1, 0)
                        ),
                        new Token(
                            TokenTypes.SUM,
                            2,
                            3,
                            new LexicalRange(2, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        )
                    )
                )
            );
        Operation expression =
            new Operation(new Variable("2"), Operand.SUM, new Variable("a"));
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForMixedOperation(){
        Parser<Expression> parser =
            new FunctionParserV1(
                TokenIterator.Companion.create(
                    "2 + a * 'hola' - 8",
                    List.of(
                        new Token(
                            TokenTypes.NUMBER,
                            0,
                            1,
                            new LexicalRange(0, 0, 1, 0)
                        ),
                        new Token(
                            TokenTypes.SUM,
                            2,
                            3,
                            new LexicalRange(2, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.MULTIPLY,
                            6,
                            7,
                            new LexicalRange(6, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.STRING,
                            8,
                            14,
                            new LexicalRange(8, 0, 14, 0)
                        ),
                        new Token(
                            TokenTypes.SUBSTRACT,
                            15,
                            16,
                            new LexicalRange(15, 0, 16, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            17,
                            18,
                            new LexicalRange(17, 0, 18, 0)
                        )
                    )
                )
            );
        Operation expression =
            new Operation(
                new Operation(
                    new Variable("2"),
                    Operand.SUM,
                    new Operation(new Variable("a"), Operand.MUL, new Variable("'hola'"))
                ),
                Operand.SUB,
                new Variable("8")
            );
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void printParserTest() {
        Parser<Print> parser =
            new PrintParser(
                TokenIterator.Companion.create(
                    "println('hola')",
                    List.of(
                        new Token(
                            TokenTypes.PRINTLN,
                            0,
                            7,
                            new LexicalRange(0, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.LEFTPARENTHESIS,
                            7,
                            8,
                            new LexicalRange(7, 0, 8, 0)
                        ),
                        new Token(
                            TokenTypes.STRING,
                            8,
                            14,
                            new LexicalRange(8, 0, 14, 0)
                        ),
                        new Token(
                            TokenTypes.RIGHTPARENTHESIS,
                            14,
                            15,
                            new LexicalRange(14, 0, 15, 0)
                        )
                    )
                )
            );
        Print print = new Print(new Variable("'hola'"));
        assertEquals(print.toString(), parser.parse().toString());
    }

    @Test
    public void declarationParserTestForInitialization() {
        Parser<Declaration> parser =
            new DeclarationParserV1(
                TokenIterator.Companion.create(
                    "let a:number;",
                    List.of(
                        new Token(
                            TokenTypes.LET,
                            0,
                            3,
                            new LexicalRange(0, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.COLON,
                            5,
                            6,
                            new LexicalRange(5, 0, 6, 0)
                        ),
                        new Token(
                            TokenTypes.TYPENUMBER,
                            6,
                            12,
                            new LexicalRange(6, 0, 12, 0)
                        ),
                        new Token(
                            TokenTypes.SEMICOLON,
                            12,
                            13,
                            new LexicalRange(12, 0, 13, 0)
                        )
                    )
                )
            );
        Declaration declaration = new Declaration("a", "number");
        assertEquals(declaration.toString(), parser.parse().toString());
    }

    @Test
    public void declarationParserTestForInitializationAndAssignment() {
        Parser<Declaration> parser =
            new DeclarationParserV1(
                TokenIterator.Companion.create(
                    "let a:number = 8;",
                    List.of(
                        new Token(
                            TokenTypes.LET,
                            0,
                            3,
                            new LexicalRange(0, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.COLON,
                            5,
                            6,
                            new LexicalRange(5, 0, 6, 0)
                        ),
                        new Token(
                            TokenTypes.TYPENUMBER,
                            6,
                            12,
                            new LexicalRange(6, 0, 12, 0)
                        ),
                        new Token(
                            TokenTypes.EQUAL,
                            13,
                            14,
                            new LexicalRange(13, 0, 14, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            15,
                            16,
                            new LexicalRange(15, 0, 16, 0)
                        ),
                        new Token(
                            TokenTypes.SEMICOLON,
                            16,
                            17,
                            new LexicalRange(16, 0, 17, 0)
                        )
                    )
                )
            );
        Declaration declaration = new Declaration("a", "number", false, new Variable("8"));
        assertEquals(declaration.toString(), parser.parse().toString());
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
    public void defaultParserTestForNonValidBlockShouldThrowException() {
        Parser<Node> parser =
            new ProgramParserV1(
                TokenIterator.Companion.create(
                    "number",
                    List.of(
                        new Token(
                            TokenTypes.TYPENUMBER,
                            0,
                            6,
                            new LexicalRange(0, 0, 6, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void declarationParserWithMissingIdentifierShouldThrowExceptions() {
        Parser<Declaration> parser =
            new DeclarationParserV1(
                TokenIterator.Companion.create(
                    "let :",
                    List.of(
                        new Token(
                            TokenTypes.LET,
                            0,
                            3,
                            new LexicalRange(0, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.COLON,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void declarationParserWithMissingTypeSeparatorShouldThrowExceptions() {
        Parser<Declaration> parser =
            new DeclarationParserV1(
                TokenIterator.Companion.create(
                    "let x number",
                    List.of(
                        new Token(
                            TokenTypes.LET,
                            0,
                            3,
                            new LexicalRange(0, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.NUMBER,
                            6,
                            12,
                            new LexicalRange(6, 0, 12, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void declarationParserWithMissingTypeShouldThrowExceptions() {
        Parser<Declaration> parser =
            new DeclarationParserV1(
                TokenIterator.Companion.create(
                    "let x : ;",
                    List.of(
                        new Token(
                            TokenTypes.LET,
                            0,
                            3,
                            new LexicalRange(0, 0, 3, 0)
                        ),
                        new Token(
                            TokenTypes.IDENTIFIER,
                            4,
                            5,
                            new LexicalRange(4, 0, 5, 0)
                        ),
                        new Token(
                            TokenTypes.COLON,
                            6,
                            7,
                            new LexicalRange(6, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.SEMICOLON,
                            8,
                            9,
                            new LexicalRange(8, 0, 9, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void printParserWithMissingLeftParenthesisShouldThrowException() {
        Parser<Print> parser =
            new PrintParser(
                TokenIterator.Companion.create(
                    "println 'hola')",
                    List.of(
                        new Token(
                            TokenTypes.PRINTLN,
                            0,
                            7,
                            new LexicalRange(0, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.STRING,
                            8,
                            14,
                            new LexicalRange(8, 0, 14, 0)
                        ),
                        new Token(
                            TokenTypes.RIGHTPARENTHESIS,
                            14,
                            15,
                            new LexicalRange(14, 0, 15, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void printParserWithMissingRightParenthesisShouldThrowException() {
        Parser<Print> parser =
            new PrintParser(
                TokenIterator.Companion.create(
                    "println('hola'",
                    List.of(
                        new Token(
                            TokenTypes.PRINTLN,
                            0,
                            7,
                            new LexicalRange(0, 0, 7, 0)
                        ),
                        new Token(
                            TokenTypes.LEFTPARENTHESIS,
                            7,
                            8,
                            new LexicalRange(7, 0, 8, 0)
                        ),
                        new Token(
                            TokenTypes.STRING,
                            8,
                            14,
                            new LexicalRange(8, 0, 14, 0)
                        )
                    )
                )
            );
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    /*@SneakyThrows
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

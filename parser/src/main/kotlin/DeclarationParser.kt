import PrintScript.lexer.lexerEnums.Types
import expression.Expression
import org.austral.ingsis.printscript.common.TokenConsumer
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull
import java.lang.reflect.Type


class DeclarationParser(@NotNull stream: TokenIterator) : TokenConsumer(stream), Parser<Declaration> {
    private val expressionParser = ExpressionParser(stream)

    // Declaration -> Keyword Identifier Separator Keyword Separator | Keyword Identifier Separator
    // Keyword Operator Expr Separator
    override fun parse(): Declaration {
        consume(Types.LET, "let")
        val variable = consume(Types.IDENTIFIER).content
        consume(Types.COLON, ":")
       // val type = consume(DefaultTokenTypes.KEYWORD).content
        if (peek(Types.SEMICOLON, ";") != null) {
          //  consume(DefaultTokenTypes.SEPARATOR)
         //   return Declaration(variable, type)
        }
        consume(Types.EQUAL, "=")
        val expr: Expression = expressionParser.parse()
        //return Declaration(variable, type, expr)
        return Declaration(variable, "hola", expr) // BORRAR
    }
}

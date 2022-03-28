package PrintScript.parser

import PrintScript.ast.statement.Statement
import PrintScript.lexer.lexerEnums.Types
import org.austral.ingsis.printscript.common.Token


class ParserImplementation : Parser {

    //private lateinit var tokenList: List<Token>

    override fun parse(tokens: List<Token>): List<Statement> {
        val statements = ArrayList<Statement>()
        //  val subTokens = ArrayList<Token>()

        for (i in tokens) {
            if (!i.type.equals(Types.EOF)) {
                //statements.add(declaration(i))
                //  statements.add(generateStatement(subTokens, i))
                // subTokens.clear()
            }
            // subTokens.add(i)
        }
        return statements
    }

    private fun declaration(currentToken: Token) /*Statement*/ {
        if (currentToken.type.equals(Types.LET)) {
         //   generateStatement(currentToken)
        }
    }

  /*  private fun generateStatement(tokens: List<Token>, currentToken: Token) {
    }*/


}

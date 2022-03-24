package PrintScript.ast.expression

import PrintScript.visitor.expression.ExpressionVisitor
import org.austral.ingsis.printscript.common.Token

class IdentifierExpression(token: Token) : Expression{

    private var token: Token = token

    override fun accept(visitor: ExpressionVisitor): Any {
        return visitor.visit(this)
    }
}
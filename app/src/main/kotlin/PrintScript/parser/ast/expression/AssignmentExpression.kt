package PrintScript.ast.expression

import PrintScript.visitor.expression.ExpressionVisitor
import org.austral.ingsis.printscript.common.Token

class AssignmentExpression(token: Token, expression: Expression) : Expression {
    private var token: Token = token
    private var expression: Expression = expression

    override fun accept(visitor: ExpressionVisitor): Any {
        return visitor.visit(this)
    }
}
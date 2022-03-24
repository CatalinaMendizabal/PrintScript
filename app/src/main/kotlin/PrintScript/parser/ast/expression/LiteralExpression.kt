package PrintScript.ast.expression

import PrintScript.visitor.expression.ExpressionVisitor

class LiteralExpression(value: Any) : Expression {

    private var value = value;

    override fun accept(visitor: ExpressionVisitor): Any {
        return visitor.visit(this)
    }

}
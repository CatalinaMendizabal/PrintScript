package PrintScript.ast.expression

import PrintScript.visitor.expression.ExpressionVisitor

class GroupedExpression(expression: Expression): Expression{

    private var expression: Expression = expression

    override fun accept(visitor: ExpressionVisitor): Any {
        return visitor.visit(this)
    }
}
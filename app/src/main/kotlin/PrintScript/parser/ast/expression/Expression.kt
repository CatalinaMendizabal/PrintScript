package PrintScript.ast.expression

import PrintScript.visitor.expression.ExpressionVisitor

interface Expression {

    fun accept(visitor: ExpressionVisitor): Any
}
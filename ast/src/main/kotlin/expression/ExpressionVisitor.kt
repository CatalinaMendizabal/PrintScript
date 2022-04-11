package expression

interface ExpressionVisitor {
    fun visitExpression(expression: Expression)

    fun visitVariable(variable: Variable)
}

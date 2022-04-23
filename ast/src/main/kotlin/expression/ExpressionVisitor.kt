package expression

interface ExpressionVisitor {
    fun visitExpression(operation: Operation)

    fun visitVariable(variable: Variable)
}
/*
expr1Visitor = value|sum|sub
expr2Visitor = expr1|mul|div
expr3Visitor = expr2|expr1|boolean
 */

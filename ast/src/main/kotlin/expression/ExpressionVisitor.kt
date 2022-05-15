package expression

interface ExpressionVisitor {
    fun visitExpression(operation: Operation)

    fun visitVariable(variable: Variable)

    fun visitReadInput(input: ReadInput)
}

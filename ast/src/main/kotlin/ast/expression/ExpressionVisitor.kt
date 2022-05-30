package ast.expression

interface ExpressionVisitor {
    fun visitExpression(operation: Operation)

    fun visitVariable(variable: Variable)

    fun visitReadInput(readInput: ReadInput)
}

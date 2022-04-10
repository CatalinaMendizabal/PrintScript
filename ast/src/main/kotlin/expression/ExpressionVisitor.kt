package expression


interface ExpressionVisitor {
   // @Throws(NodeException::class)
    fun visitExpression(expression: Expression)

  //  @Throws(NodeException::class)
    fun visitVariable(variable: Variable)
}

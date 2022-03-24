package PrintScript.ast.statement

import PrintScript.ast.expression.Expression
import PrintScript.visitor.statement.StatementVisitor

class PrintStatement(expression: Expression) : Statement{

    private var expression: Expression = expression

    override fun getExpression(): Expression {
        return expression;
    }

    override fun accept(visitor: StatementVisitor) {
        return visitor.visit(this)
    }
}
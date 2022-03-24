package PrintScript.ast.statement

import PrintScript.ast.expression.Expression
import PrintScript.visitor.statement.StatementVisitor

interface Statement {
    fun getExpression(): Expression;
    fun accept(visitor: StatementVisitor);
}
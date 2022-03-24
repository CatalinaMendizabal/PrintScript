package PrintScript.visitor.expression

import PrintScript.ast.expression.AssignmentExpression
import PrintScript.ast.expression.GroupedExpression
import PrintScript.ast.expression.IdentifierExpression
import PrintScript.ast.expression.LiteralExpression

interface ExpressionVisitor {

    fun visit(literalExpression: LiteralExpression)

    fun visit(groupedExpression: GroupedExpression)

    fun visit(identifierExpression: IdentifierExpression)

    fun visit(assignmentExpression: AssignmentExpression)

}
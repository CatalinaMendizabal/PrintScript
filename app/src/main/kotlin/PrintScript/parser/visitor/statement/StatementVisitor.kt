package PrintScript.visitor.statement

import PrintScript.ast.statement.BlockStatement
import PrintScript.ast.statement.DeclarationStatement
import PrintScript.ast.statement.ExpressionStatement
import PrintScript.ast.statement.PrintStatement

interface StatementVisitor {

    fun visit(printStatement: PrintStatement)

    fun visit(expressionStatement: ExpressionStatement)

    fun visit(declarationStatement: DeclarationStatement)

    fun visit(blockStatement: BlockStatement)

}
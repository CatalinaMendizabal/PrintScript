package ast.node

import ast.expression.ReadInput

interface NodeVisitor {
    fun visit(codeBlock: CodeBlock)

    fun visit(declaration: Declaration)

    fun visit(assignment: Assignment)

    fun visit(print: Print)

    fun visit(ifBlock: IfBlock)

    fun visit(readInput: ReadInput)
}

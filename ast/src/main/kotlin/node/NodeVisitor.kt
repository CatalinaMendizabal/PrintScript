package node

import Assignment
import CodeBlock
import Declaration
import Print

interface NodeVisitor {
    fun visit(codeBlock: CodeBlock)

    fun visit(declaration: Declaration)

    fun visit(assignment: Assignment)

    fun visit(print: Print)
}

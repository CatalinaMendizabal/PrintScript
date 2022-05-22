package node

import Assignment
import CodeBlock
import Condition
import Declaration
import Print
import expression.ReadInput

interface NodeVisitor {
    fun visit(codeBlock: CodeBlock)

    fun visit(declaration: Declaration)

    fun visit(assignment: Assignment)

    fun visit(print: Print)

    fun visit(condition: Condition)

    fun visit(input: ReadInput)
}

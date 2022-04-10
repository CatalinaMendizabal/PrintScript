package node

import Assignment
import CodeBlock
import Declaration
import Print


interface NodeVisitor {
   // @Throws(NodeException::class)
    fun visit(codeBlock: CodeBlock)

  ///  @Throws(NodeException::class)
    fun visit(declaration: Declaration)

 //   @Throws(NodeException::class)
    fun visit(assignment: Assignment?)

  //  @Throws(NodeException::class)
    fun visit(print: Print)
}

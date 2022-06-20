package interpreter.interpreterVisitor

import ast.expression.ReadInput
import ast.node.IfBlock
import ast.node.NodeException
import ast.node.Print
import interpreter.IInputProvider
import interpreter.IPrintEmitter
import interpreter.exception.TypeMismatchException
import interpreter.solverVisitor.SolverVisitorV2

class InterpreterVisitorV2(inputProvider: IInputProvider, printEmitter: IPrintEmitter) : AbstractInterpreterVisitor() {
    private val printEmitter: IPrintEmitter

    init {
        solverVisitor = SolverVisitorV2(inputProvider, printEmitter)
        this.printEmitter = printEmitter
    }

    override fun checkType(name: String?, type: String?) {
        super.checkType(name, type)
        if (type == "boolean") {
            if (!isBoolean(solverVisitor.result)) {
                throw TypeMismatchException(name!!, type)
            }
        }
    }

    private fun isBoolean(result: String): Boolean {
        return result == "true" || result == "false"
    }

    override fun visit(ifBlock: IfBlock) {
        ifBlock.condition.accept(solverVisitor)
        val result = solverVisitor.result
        when (result) {
            "true" -> {
                ifBlock.ifCodeBlock.accept(this)
            }
            "false" -> {
                ifBlock.elseCodeBlock.accept(this)
            }
            else -> throw NodeException("If block condition is not boolean")
        }
    }

    override fun visit(readInput: ReadInput) {
    }

    override fun visit(print: Print) {
        print.content.accept(solverVisitor)
        val newResult = solverVisitor.result.replace("[\"']", "")
        result.write(newResult)
        printEmitter.print(newResult)
      /*  print.content.accept(solverVisitor)
        result.write(solverVisitor.result)
        printEmitter.print(solverVisitor.result)*/

     /*   print.content.accept(solverVisitor)
        val result: String = solverVisitor.result.replace("^\"|\"|^\'|\'", "")
        this.result.write(result)
        printEmitter.print(result)*/
    }
}

package interpreter.interpreterVisitor

import ast.expression.Expression
import ast.expression.ReadInput
import ast.node.Assignment
import ast.node.CodeBlock
import ast.node.Declaration
import ast.node.IfBlock
import ast.node.NodeVisitor
import ast.node.Print
import interpreter.InterpreterConsole
import interpreter.exception.TypeMismatchException
import interpreter.exception.UndeclaredVariableException
import interpreter.solverVisitor.AbstractSolverVisitor
import interpreter.solverVisitor.SolverVisitorV1

abstract class AbstractInterpreterVisitor : NodeVisitor {
    val result: InterpreterConsole = InterpreterConsole()
    var solverVisitor: AbstractSolverVisitor = SolverVisitorV1()
    private val varTypes: MutableMap<String, String> = HashMap()

    override fun visit(codeBlock: CodeBlock) {
        for (child in codeBlock.children) {
            child.accept(this)
        }
    }

    override fun visit(declaration: Declaration) {
        val type: String = declaration.type
        val name: String = declaration.varName
        val expression: Expression? = declaration.value
        solverVisitor.declareVariable(name, declaration.isConstant)
        if (expression != null) {
            expression.accept(solverVisitor)
            checkType(name, type)
            solverVisitor.assignVariable(name)
        }
        varTypes[name] = type
    }

    private fun isString(result: String): Boolean {
        return result.matches(Regex("\".*\" |\'.*\' |\".*\"|'.*'|.*"))
    }

    private fun isNumber(result: String): Boolean {
        return result.matches(Regex("-?\\d+\\.?\\d*"))
    }

    override fun visit(assignment: Assignment) {
        val name: String = assignment.name
        val value: Expression = assignment.value
        value.accept(solverVisitor)
        val type = varTypes[name] ?: throw UndeclaredVariableException(name)
        checkType(name, type)
        solverVisitor.assignVariable(name)
    }

    protected open fun checkType(name: String?, type: String?) {
        when (type) {
            "number" -> if (!isNumber(solverVisitor.result)) throw name?.let { TypeMismatchException(it, type) }!!
            "string" -> if (!isString(solverVisitor.result)) throw name?.let { TypeMismatchException(it, type) }!!
        }
    }

    override fun visit(print: Print) {
        print.content.accept(solverVisitor)
        result.write(solverVisitor.result)
        /* print.content.accept(solverVisitor)
         val result: String = solverVisitor.result.replace("[\"']", "")
         this.result.write(result)*/
    }

    override fun visit(ifBlock: IfBlock) {}

    override fun visit(readInput: ReadInput) {}
}

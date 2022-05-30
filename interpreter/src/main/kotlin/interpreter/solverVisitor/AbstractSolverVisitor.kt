package interpreter.solverVisitor

import ast.expression.Expression
import ast.expression.ExpressionVisitor
import ast.expression.Operand
import ast.expression.Operation
import ast.expression.ReadInput
import ast.expression.Variable
import interpreter.exception.InvalidOperationException
import interpreter.exception.UndeclaredVariableException

abstract class AbstractSolverVisitor : ExpressionVisitor {

    var result: String = ""
    protected var variables: MutableMap<String?, String?> = HashMap()
    protected val stringRegex = Regex("\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'")
    protected val numberRegex = Regex("-?[0-9]{1,9}(\\.[0-9]*)?")

    constructor()

    constructor(variables: HashMap<String?, String?>) {
        this.variables = variables
    }

    override fun visitExpression(operation: Operation) {
        val operand: Operand? = operation.operand
        var leftResult = operation.left?.let { getResult(it) }
        var rightResult = operation.right?.let { getResult(it) }
        if (variables.containsKey(leftResult)) leftResult = variables[leftResult]
        if (variables.containsKey(rightResult)) rightResult = variables[rightResult]
        val result: String = solveOperation(operand, leftResult, rightResult)
            ?: throw UndeclaredVariableException("Missing variable declaration")
        this.result = result
    }

    open fun solveOperation(operand: Operand?, leftResult: String?, rightResult: String?): String? {
        val result: String? = if (isStringOperation(leftResult, rightResult)) {
            stringOperation(leftResult, rightResult, operand)
        } else if (leftResult!!.matches(numberRegex) && rightResult!!.matches(numberRegex)) {
            numericOperation(leftResult, rightResult, operand)
        } else {
            null
        }
        return result
    }

    protected fun isStringOperation(leftResult: String?, rightResult: String?): Boolean {
        return (
            leftResult!!.matches(stringRegex) && rightResult!!.matches(numberRegex) ||
                leftResult.matches(numberRegex) && rightResult!!.matches(stringRegex) ||
                leftResult.matches(stringRegex) && rightResult!!.matches(stringRegex)
            )
    }

    protected fun stringOperation(leftResult: String?, rightResult: String?, operand: Operand?): String {
        if (operand !== Operand.SUM) throw InvalidOperationException(leftResult!!, rightResult!!, operand!!)
        val left = leftResult!!.replace("[\"']".toRegex(), "")
        val right = rightResult!!.replace("[\"']".toRegex(), "")
        return "\"" + left + right + "\""
    }

    protected fun numericOperation(leftResult: String?, rightResult: String?, operand: Operand?): String {
        var result = ""
        val left = leftResult!!.toDouble()
        val right = rightResult!!.toDouble()
        when (operand) {
            Operand.SUM -> result = format(left + right)
            Operand.SUB -> result = format(left - right)
            Operand.MUL -> result = format(left * right)
            Operand.DIV -> result = format(left / right)
            else -> {}
        }
        return result
    }

    protected fun format(value: Double): String {
        var result = value.toString() + ""
        while (result[result.length - 1] == '0') result = result.substring(0, result.length - 1)
        if (result[result.length - 1] == '.') result = result.substring(0, result.length - 1)
        return result
    }

    override fun visitVariable(variable: Variable) {
        result = if (variables.containsKey(variable.value)) variables[variable.value].toString() else variable.value
        if (!result.matches(numberRegex) && !result.matches(stringRegex)) throw UndeclaredVariableException(variable.value)
    }

    fun getResult(expression: Expression): String {
        expression.accept(this)
        return result
    }

    open fun declareVariable(name: String, isConstant: Boolean) {
        variables[name] = null
    }

    open fun assignVariable(name: String) {
        variables[name] = result
    }

    override fun visitReadInput(readInput: ReadInput) {}
}

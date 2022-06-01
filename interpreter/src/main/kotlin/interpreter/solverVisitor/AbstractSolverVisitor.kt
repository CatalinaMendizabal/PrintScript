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
    protected val stringRegex = Regex("\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'|\".*\"|'.*'|.*\"|[\\s\\S]")
    protected val numberRegex = Regex("-?[0-9]{1,9}(\\.[0-9]*)?")

    constructor()

    constructor(variables: HashMap<String?, String?>) {
        this.variables = variables
    }

    override fun visitExpression(operation: Operation) {
        val operand: Operand? = operation.operand
        var leftResult = operation.left?.let { getExpression(it) }
        var rightResult = operation.right?.let { getExpression(it) }

        if (variables.containsKey(leftResult)) leftResult = variables[leftResult]
        if (variables.containsKey(rightResult)) rightResult = variables[rightResult]

        val result: String = getOperationResult(operand, leftResult, rightResult)
            ?: throw UndeclaredVariableException("Missing variable declaration")
        this.result = result
    }

    open fun getOperationResult(operand: Operand?, leftResult: String?, rightResult: String?): String? {
        return if (isString(leftResult, rightResult)) operateOverString(leftResult, rightResult, operand)
        else if (isNumber(leftResult, rightResult)) operateOverNumber(leftResult, rightResult, operand)
        else throw IllegalArgumentException("Invalid expression")
    }

    private fun isNumber(leftResult: String?, rightResult: String?) =
        leftResult!!.matches(numberRegex) && rightResult!!.matches(numberRegex)

    private fun isString(leftResult: String?, rightResult: String?): Boolean {
        if (stringRegex.matches(leftResult!!) && stringRegex.matches(rightResult!!)) return true
        if (stringRegex.matches(leftResult) && numberRegex.matches(rightResult!!)) return true
        if (stringRegex.matches(rightResult!!) && numberRegex.matches(leftResult)) return true
        return false
    }

    private fun operateOverString(leftResult: String?, rightResult: String?, operand: Operand?): String {
        if (operand !== Operand.SUM) throw InvalidOperationException(leftResult!!, rightResult!!, operand!!)
        else {
            return leftResult?.replace(Regex("^\"|\"|^'|'"), "") + rightResult?.replace(Regex("^\"|\"|^\'|\'"), "")
        }
/*        val left = leftResult!!.replace("[\"']".toRegex(), "")
        val right = rightResult!!.replace("[\"']".toRegex(), "")
        return "\"" + left + right + "\""*/
    }

    private fun operateOverNumber(leftResult: String?, rightResult: String?, operand: Operand?): String {
        var result = when (operand) {
            Operand.SUM -> (leftResult!!.toDouble() + rightResult!!.toDouble()).toString()
            Operand.SUB -> (leftResult!!.toDouble() - rightResult!!.toDouble()).toString()
            Operand.MUL -> (leftResult!!.toDouble() * rightResult!!.toDouble()).toString()
            Operand.DIV -> (leftResult!!.toDouble() / rightResult!!.toDouble()).toString()
            else -> { "" }
        }
        return result
        /*var result = ""
        val left = leftResult!!.toDouble()
        val right = rightResult!!.toDouble()
        when (operand) {
            Operand.SUM -> result = format(left + right)
            Operand.SUB -> result = format(left - right)
            Operand.MUL -> result = format(left * right)
            Operand.DIV -> result = format(left / right)
        }
        return result*/
    }

 /*   private fun format(value: Double): String {
        var result = value.toString() + ""
        while (result[result.length - 1] == '0') result = result.substring(0, result.length - 1)
        if (result[result.length - 1] == '.') result = result.substring(0, result.length - 1)
        return result
    }*/

    override fun visitVariable(variable: Variable) {
        result = if (variables.containsKey(variable.value)) variables[variable.value].toString() else variable.value
        if (!result.matches(numberRegex) && !result.matches(stringRegex)) throw UndeclaredVariableException(variable.value)
    }

    private fun getExpression(expression: Expression): String {
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

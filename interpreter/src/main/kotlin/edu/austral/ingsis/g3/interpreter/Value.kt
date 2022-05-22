package edu.austral.ingsis.g3.interpreter

import expression.Expression
import expression.ExpressionVisitor
import expression.Operand
import expression.Operation
import expression.ReadInput
import expression.Variable

class Value : ExpressionVisitor {

    var expressionResult = ""
    var variables = HashMap<String, String>()
    private lateinit var inputProvider: ReadInputProvider

    val stringRegex = Regex("\".*\"|'.*'")
    private val ifRegex = Regex("true|false")
    private val numberRegex = Regex("-?\\d+\\.?\\d*")

    constructor(variables: HashMap<String, String>) {
        this.variables = variables
    }

    constructor(inputProvider: ReadInputProvider) {
        this.inputProvider = inputProvider
    }

    constructor(variables: HashMap<String, String>, inputProvider: ReadInputProvider) {
        this.variables = variables
        this.inputProvider = inputProvider
    }

    private fun evaluateExpression(operation: Operation) {
        val operand: Operand = operation.operand
        var leftValue: String = getExpression(operation.left)
        var rightValue: String = getExpression(operation.right)

        if (variables.containsKey(leftValue)) leftValue = variables.getValue(leftValue)
        if (variables.containsKey(rightValue)) rightValue = variables.getValue(rightValue)

        val result = getOperationResult(leftValue, rightValue, operand)
        this.expressionResult = result
    }

    fun getOperationResult(leftValue: String, rightValue: String, operand: Operand): String {
        return if (isString(leftValue, rightValue)) operateOverString(operand, leftValue, rightValue)
        else if (isNumber(leftValue, rightValue)) operateOverNumber(operand, leftValue, rightValue)
        else throw IllegalArgumentException("Invalid expression")
    }

    private fun getExpression(expression: Expression): String {
        expression.accept(this)
        return expressionResult
    }

    private fun isString(leftValue: String, rightValue: String): Boolean {
        if (stringRegex.matches(leftValue) && stringRegex.matches(rightValue)) return true
        if (stringRegex.matches(leftValue) && numberRegex.matches(rightValue)) return true
        if (stringRegex.matches(rightValue) && numberRegex.matches(leftValue)) return true
        return false
    }

    private fun isNumber(leftValue: String, rightValue: String): Boolean {
        return numberRegex.matches(leftValue) && numberRegex.matches(rightValue)
    }

    private fun isNotDefined(variable: String): Boolean {
        if (stringRegex.containsMatchIn(variable)) return true
        if (numberRegex.containsMatchIn(variable)) return true
        if (ifRegex.containsMatchIn(variable)) return true
        return false
    }

    private fun operateOverNumber(operand: Operand, leftValue: String, rightValue: String): String {
        val result: String = when (operand) {
            Operand.SUM -> (leftValue.toDouble() + rightValue.toDouble()).toString()
            Operand.SUBSTRACT -> (leftValue.toDouble() - rightValue.toDouble()).toString()
            Operand.MULTIPLY -> (leftValue.toDouble() * rightValue.toDouble()).toString()
            Operand.DIVIDE -> (leftValue.toDouble() / rightValue.toDouble()).toString()
        }
        return result
    }

    private fun operateOverString(operand: Operand, leftValue: String, rightValue: String): String {
        if (operand != Operand.SUM) {
            throw IllegalArgumentException("Operand $operand is not supported for value type String")
        } else {
            return leftValue.replace(Regex("^\"|\"$"), "") + rightValue.replace(Regex("^\"|\"$"), "")
        }
    }

    private fun operateOverReadInput() {}

    fun declaration(variable: String) {
        variables[variable] = ""
    }

    fun assignation(variable: String) {
        variables[variable] = expressionResult
    }

    override fun visitExpression(operation: Operation) {
        evaluateExpression(operation)
    }

    override fun visitVariable(variable: Variable) {
        if (variables.containsKey(variable.getValue())) {
            this.expressionResult = variables[variable.getValue()].toString()
        } else {
            expressionResult = variable.getValue()
        }
        if (!isNotDefined(expressionResult)) {
            throw IllegalArgumentException("Variable $expressionResult is not defined!")
        }
    }

    override fun visitReadInput(input: ReadInput) {
        //  val values = AbstractValue(variables, inputProvider)
        input.prompt.accept(this)
        val res = this.expressionResult
        if (res.matches(stringRegex)) {
            expressionResult = inputProvider.getInput(res)
            var a = 0
            // Todo
        } else throw IllegalArgumentException("Input should be string")
    }
}

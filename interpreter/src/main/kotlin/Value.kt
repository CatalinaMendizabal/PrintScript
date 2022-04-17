import expression.Expression
import expression.ExpressionVisitor
import expression.Operand
import expression.Variable

class Value() : ExpressionVisitor {

    private var expressionResult: String = ""
    private var variables: MutableMap<String, String> = mutableMapOf()

    // string regex with numbers and letters and double quotation marks
    private val stringRegex = Regex("[a-zA-Z0-9\"]+")

    // string regex with numbers points and numbers
    private var numberRegex = Regex("^[0-9]+[.][0-9]+$")

    constructor(variables: MutableMap<String, String>) : this() {
        this.variables = variables
    }

    private fun evaluateExpression(expression: Expression) {
        var operand: Operand = expression.operand
        var leftValue: String = expression.left.toString()
        var rightValue: String = expression.right.toString()

        if (variables.containsKey(leftValue)) {
            leftValue = variables.getValue(leftValue)
        }
        if (variables.containsKey(rightValue)) {
            rightValue = variables.getValue(rightValue)
        }

        val result: String = ""

        if (isString(leftValue, rightValue)) {
            expressionResult = operateOverString(operand, leftValue, rightValue)
        }

        if (isNumber(leftValue, rightValue)) {
            expressionResult = operateOverNumber(operand, leftValue, rightValue)
        } else {
            throw IllegalArgumentException("Invalid expression")
        }

        this.expressionResult = result
    }

    private fun isString(leftValue: String, rightValue: String): Boolean {
        if (stringRegex.matches(leftValue) && stringRegex.matches(rightValue)) {
            return true
        }
        if (stringRegex.matches(leftValue) && numberRegex.matches(rightValue)) {
            return true
        }
        if (stringRegex.matches(rightValue) && numberRegex.matches(leftValue)) {
            return true
        }
        return false
    }

    private fun isNumber(leftValue: String, rightValue: String): Boolean {
        return numberRegex.matches(leftValue) && numberRegex.matches(rightValue)
    }

    private fun isNotDefined(variable: String): Boolean {
        if (!stringRegex.containsMatchIn(variable)) return true
        if (!numberRegex.containsMatchIn(variable)) return true
        return false
    }

    private fun operateOverNumber(operand: Operand, leftValue: String, rightValue: String): String {
        val result: String = when (operand) {
            Operand.SUM -> (leftValue.toDouble() + rightValue.toDouble()).toString()
            Operand.SUB -> (leftValue.toDouble() - rightValue.toDouble()).toString()
            Operand.MUL -> (leftValue.toDouble() * rightValue.toDouble()).toString()
            Operand.DIV -> (leftValue.toDouble() / rightValue.toDouble()).toString()
        }
        return result
    }

    private fun operateOverString(operand: Operand, leftValue: String, rightValue: String): String {
        if (operand != Operand.SUM) {
            throw IllegalArgumentException("Operand $operand is not supported for value type String")
        } else {
            return leftValue + rightValue
        }
    }

    fun declaration(variable: String) {
        variables[variable] = ""
    }

    fun assignation(variable: String) {
        variables[variable] = expressionResult
    }

    override fun visitExpression(expression: Expression) {
        evaluateExpression(expression)
    }

    override fun visitVariable(variable: Variable) {
        expressionResult = variables.getOrDefault(variable.getValue(), variable.getValue())
      /*  if (isNotDefined(expressionResult)) {
            throw IllegalArgumentException("Variable $expressionResult is not defined!")
        }*/
    }

    // getter
    fun getStringRegex(): Regex {
        return stringRegex
    }

    fun getNumberRegex(): Regex {
        return numberRegex
    }

    fun getExpressionResult(): String {
        return expressionResult
    }
}

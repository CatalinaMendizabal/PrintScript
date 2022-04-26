import expression.Expression
import expression.ExpressionVisitor
import expression.Operand
import expression.Operation
import expression.Variable

class Value() : ExpressionVisitor {

    private var expressionResult: String = ""
    private var variables = HashMap<String, String>()

    // string regex with numbers and letters and double quotation marks
    private val stringRegex = Regex("\".*\"|'.*'")

    // if regex
    private val ifRegex = Regex("if\\s*\\(.*\\)\\s*\\{.*\\}")

    // string regex with numbers points and numbers
    private var numberRegex = Regex("-?\\d+\\.?\\d*")

    constructor(variables: HashMap<String, String>) : this() {
        this.variables = variables
    }

    private fun evaluateExpression(operation: Operation) {
        var operand: Operand = operation.operand
        var leftValue: String = getExpression(operation.left)
        var rightValue: String = getExpression(operation.right)
//        if(expression.left.accept(this) != null) {
//            leftValue = expression.left.accept(this).toString()
//        }
//        if (expression.right.accept(this) != null){
//            rightValue = expression.right.accept(this).toString()
//        }

        if (variables.containsKey(leftValue)) {
            leftValue = variables.getValue(leftValue)
        }
        if (variables.containsKey(rightValue)) {
            rightValue = variables.getValue(rightValue)
        }

        var result = ""

        if (isString(leftValue, rightValue)) {
            result = operateOverString(operand, leftValue, rightValue)
        } else if (isNumber(leftValue, rightValue)) {
            result = operateOverNumber(operand, leftValue, rightValue)
        } else {
            throw IllegalArgumentException("Invalid expression")
        }

        this.expressionResult = result
    }

    private fun getExpression(expression: Expression): String {
        expression.accept(this)
        return expressionResult
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
        if (stringRegex.containsMatchIn(variable)) return true
        if (numberRegex.containsMatchIn(variable)) return true
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
            this.expressionResult = variables.get(variable.getValue()).toString()
        } else {
            expressionResult = variable.getValue()
        }
        if (!isNotDefined(expressionResult)) {
            throw IllegalArgumentException("Variable $expressionResult is not defined!")
        }
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

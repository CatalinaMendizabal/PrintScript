import expression.Expression
import expression.ExpressionVisitor
import expression.Operand
import expression.Operation
import expression.Variable

abstract class Value() : ExpressionVisitor {

    var expressionResult = ""
    var variables = HashMap<String, String>()

    // string regex with numbers and letters and double quotation marks
    val stringRegex = Regex("\".*\"|'.*'")

    // if regex
    val ifRegex = Regex("true|false")

    // string regex with numbers points and numbers
    var numberRegex = Regex("-?\\d+\\.?\\d*")

    constructor(variables: HashMap<String, String>) : this() {
        this.variables = variables
    }

    private fun evaluateExpression(operation: Operation) {
        val operand: Operand = operation.operand
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

        val result =  getOperationResult(leftValue, rightValue, operand)

        this.expressionResult = result
    }

    fun getOperationResult(leftValue: String, rightValue: String, operand: Operand): String {
        var aux = ""
        if (isString(leftValue, rightValue)) {
            aux =  operateOverString(operand, leftValue, rightValue)
        } else if (isNumber(leftValue, rightValue)) {
            aux =  operateOverNumber(operand, leftValue, rightValue)
        } else if (isBoolean(leftValue, rightValue)) {
           // operateOverCondition(operand, operation.left, operation.right)
        } else {
            throw IllegalArgumentException("Invalid expression")
        }
        return aux
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

    private fun isBoolean(leftValue: String, rightValue: String): Boolean {
        return leftValue.matches(ifRegex) || rightValue.matches(ifRegex)
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

    private fun operateOverCondition(operand: Operand, leftValue: Expression, rightValue: Expression)  {
        if (getExpression(leftValue).matches(ifRegex) || getExpression(rightValue).matches(ifRegex)) return
            //evaluateExpression(Operation(leftValue, operand, rightValue))
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
//
//    override fun visitCondition(condition: Condition) {
//        TODO("Not yet implemented")
//    }

}

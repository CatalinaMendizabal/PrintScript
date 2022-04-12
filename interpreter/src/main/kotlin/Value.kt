import expression.*
import expression.Function

class Value(): ExpressionVisitor {

    private var expressionResult: String = ""
    private lateinit var variables: MutableMap<String, String>

    //string regex with numbers and letters
    private var stringRegex = Regex("^[a-zA-Z0-9]+$")
    //string regex with numbers points and numbers
    private var numberRegex = Regex("^[0-9]+[.][0-9]+$")

    constructor(variables: MutableMap<String, String>): this() {
        this.variables = variables
    }

    fun evaluateExpression(expression: Expression){
        var operand: Operand = expression.operand
        var leftValue: String = expression.left.toString()
        var rightValue: String = expression.right.toString()

        if (variables.containsKey(leftValue)){
            leftValue = variables.getValue(leftValue)
        }
        if (variables.containsKey(rightValue)){
            rightValue = variables.getValue(rightValue)
        }

        var result: String = ""

        if (isString(leftValue, rightValue)){
            expressionResult = operateOverString(operand, leftValue, rightValue)
        }

        if (isNumber(leftValue, rightValue)){
            expressionResult = operateOverNumber(operand, leftValue, rightValue)
        }

        else{
            throw IllegalArgumentException("Invalid expression")
        }

        this.expressionResult = result
    }

    fun isString(leftValue: String, rightValue: String): Boolean {
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

    fun isNumber(leftValue: String, rightValue: String): Boolean {
        return numberRegex.matches(leftValue) && numberRegex.matches(rightValue)
    }

    fun isNotDefined(variable: String): Boolean {
        return !stringRegex.matches(variable) && !numberRegex.matches(variable)
    }

    fun operateOverNumber(operand: Operand, leftValue: String, rightValue: String): String {
        var result = ""
        result = when (operand) {
            Operand.SUM -> (leftValue.toDouble() + rightValue.toDouble()).toString()
            Operand.SUB -> (leftValue.toDouble() - rightValue.toDouble()).toString()
            Operand.MUL -> (leftValue.toDouble() * rightValue.toDouble()).toString()
            Operand.DIV -> (leftValue.toDouble() / rightValue.toDouble()).toString()
        }
        return result
    }

    fun operateOverString(operand: Operand, leftValue: String, rightValue: String): String {
        if (operand != Operand.SUM) {
            throw IllegalArgumentException("Operand $operand is not supported for value type String")
        }else{
            return leftValue + rightValue
        }
    }

    public fun declaration(variable: String){
        variables[variable]= ""
    }

    fun assigation(variable: String){
        variables[variable] = expressionResult
    }

    override fun visitExpression(expression: Expression) {
        evaluateExpression(expression)
    }

    override fun visitVariable(variable: Variable) {
        expressionResult = variables.getOrDefault(variable.getValue(), variable.getValue())
        if (isNotDefined(expressionResult)){
            throw IllegalArgumentException("Variable $expressionResult is not defined!")
        }
    }

    //getter
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
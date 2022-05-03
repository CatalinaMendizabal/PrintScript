package edu.austral.ingsis.g3.interpreter

import expression.Operand
import expression.Variable

open class Value : AbstractValue() {

    private val constants: MutableMap<String, String?> = HashMap()

    protected fun solveOperation(operand: Operand, leftResult: String, rightResult: String): String {
        return super.getOperationResult(leftResult, rightResult, operand)
    }

    override fun visitVariable(variable: Variable) {
        expressionResult = if (variables.containsKey(variable.getValue())) {
            variables[variable.getValue()].toString()
        } else if (constants.containsKey(variable.getValue())) {
            constants[variable.getValue()].toString()
        } else {
            variable.getValue()
        }
        if (!expressionResult.matches(numberRegex) && !expressionResult.matches(stringRegex) && !expressionResult.matches(ifRegex)) {
            throw IllegalArgumentException("${variable.getValue()} not declared")
        }
    }

    fun declareVariable(name: String, isConstant: Boolean) {
        if (isConstant) {
            constants[name] = variables[name]
        } else {
            super.declaration(name)
        }
    }

    fun assignVariable(name: String) {
        if (constants.containsKey(name)) {
            if (constants[name] == null) {
                constants[name] = expressionResult
                return
            }
            throw IllegalArgumentException(name)
        }
        super.assignation(name)
    }
}

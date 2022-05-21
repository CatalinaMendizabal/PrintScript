package edu.austral.ingsis.g3.interpreter

import Assignment
import CodeBlock
import Condition
import Declaration
import Print
import expression.ReadInput
import node.NodeVisitor

class InterpreterVisitor(readInputProvider: ReadInputProvider) : NodeVisitor {

    var finalValue = Value(readInputProvider)

    private var variables = HashMap<String, String>()
    val interpreterConsole: InterpreterConsole = InterpreterConsole()

     private fun checkType(name: String, type: String) {
        if (type == "string") {
            if (finalValue.stringRegex.equals(name)) {
                throw Exception("Type mismatch")
            }
        }
        if (type == "number") {
            if (finalValue.expressionResult.contains(Regex("\".*\"|'.*'"))) {
                throw Exception("Type mismatch. Variable $name must be a number")
            }
        }
        if (type == "boolean") {
            if (!isBoolean(finalValue.expressionResult)) {
                throw Exception("Type mismatch")
            }
        }
    }

    private fun isBoolean(result: String): Boolean {
        return result == "true" || result == "false"
    }

    override fun visit(codeBlock: CodeBlock) {
        codeBlock.getChildren().forEach {
            it.accept(this)
        }
    }

    override fun visit(declaration: Declaration) {
        val varName = declaration.getVarName()
        val value = declaration.getValue()
        val valueType = declaration.getType()

        finalValue.declaration(varName)
        value.accept(finalValue)
        finalValue.assignation(varName)

        checkType(varName, valueType)
        variables[varName] = valueType
    }

    override fun visit(assignment: Assignment) {
        val varName = assignment.name
        val value = assignment.value
        value.accept(finalValue)
        val type: String = variables[varName] ?: throw IllegalArgumentException("Variable $varName is not declared")
        checkType(varName, type)
        finalValue.assignation(varName)
    }

    override fun visit(print: Print) {
        val content = print.content
        content.accept(finalValue)
        interpreterConsole.print(finalValue.expressionResult)
    }

    override fun visit(condition: Condition) {
        val result: String = condition.booleanValue
        if (result == "true") {
            condition.ifCode.accept(this)
        } else if (result == "false") {
            condition.elseCode.accept(this)
        }
    }

    override fun visit(input: ReadInput) {
        TODO("Not yet implemented")
    }

    /* override fun visit(input: ReadInput) {
        // TODO val default = Default
         var input = input.prompt.accept(this)
         default.getInput(input.toString())
     }*/
}

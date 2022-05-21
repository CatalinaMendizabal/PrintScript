package edu.austral.ingsis.g3.interpreter

import Assignment
import CodeBlock
import Condition
import Declaration
import Print
import expression.ReadInput
import node.NodeVisitor

abstract class AbstractInterpreterVisitor : NodeVisitor {

    var finalValue: AbstractValue = Value()
    private var variables = HashMap<String, String>()
    open val interpreterConsole: InterpreterConsole = InterpreterConsole()

    open fun checkType(name: String, type: String) {
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
        // condition.().accept(solverVisitor)
        val result: String = finalValue.expressionResult
        if (result == "true") {
            condition.ifCode.accept(this)
        } else if (result == "false") {
            condition.elseCode.accept(this)
        }
        /* TODO
             condition.accept(this)
        // val content = finalValue.*/
    }

    override fun visit(input: ReadInput) {
        // input.prompt.accept()
    }
}

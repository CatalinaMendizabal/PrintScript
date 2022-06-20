package interpreter.solverVisitor

import ast.expression.Operand
import ast.expression.ReadInput
import ast.expression.Variable
import interpreter.IInputProvider
import interpreter.IPrintEmitter
import interpreter.exception.BooleanOperationException
import interpreter.exception.ConstantReassignmentException
import interpreter.exception.UndeclaredVariableException

class SolverVisitorV2 : AbstractSolverVisitor {
    private val boolRegex = Regex("true|false")
    private val constants: MutableMap<String, String?> = HashMap()
    private val inputProvider: IInputProvider
    private val printEmitter: IPrintEmitter

    constructor(inputProvider: IInputProvider, printEmitter: IPrintEmitter) {
        this.inputProvider = inputProvider
        this.printEmitter = printEmitter
    }

    constructor(variables: Map<String?, String?>, inputProvider: IInputProvider, printEmitter: IPrintEmitter) : super(variables as HashMap<String?, String?>) {
        this.inputProvider = inputProvider
        this.printEmitter = printEmitter
    }

    override fun getOperationResult(operand: Operand?, leftResult: String?, rightResult: String?): String? {
        val result = super.getOperationResult(operand, leftResult, rightResult)
        if (result == null) {
            if (rightResult != null) {
                if (leftResult != null) {
                    if (leftResult.matches(boolRegex) || rightResult.matches(boolRegex)) {
                        throw BooleanOperationException(leftResult, rightResult, operand!!)
                    }
                }
            }
        }
        return result
    }

    override fun visitVariable(variable: Variable) {
        result = if (variables.containsKey(variable.value)) (
            {
                variables[variable.value]
            }.toString()
            ) else (
            (
                if (constants.containsKey(variable.value)) {
                    constants[variable.value]
                } else {
                    variable.value
                }
                ).toString()
            )
        if (!result.matches(numberRegex) && !result.matches(stringRegex) && !result.matches(boolRegex)) {
            throw UndeclaredVariableException(variable.value)
        }
    }

    override fun declareVariable(name: String, isConstant: Boolean) {
        if (isConstant) {
            constants[name] = variables[name]
        } else {
            super.declareVariable(name, false)
        }
    }

    override fun assignVariable(name: String) {
        if (constants.containsKey(name)) {
            if (constants[name] == null) {
                constants[name] = result
                return
            }
            throw ConstantReassignmentException(name)
        }
        super.assignVariable(name)
    }

    override fun visitReadInput(readInput: ReadInput) {
        val visitor = SolverVisitorV2(variables, inputProvider, printEmitter)
        readInput.prompt.accept(visitor)
        val prompt = visitor.result
        result = if (prompt.matches(stringRegex)) {
            printEmitter.print(prompt.replace("\"".toRegex(), ""))
            "\"" + inputProvider.getInput(prompt.replace("[\"']".toRegex(), "")) + "\""
        } else throw IllegalArgumentException("Prompt must be a string")
    }
}

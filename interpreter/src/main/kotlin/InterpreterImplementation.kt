import node.NodeVisitor

class InterpreterImplementation : NodeVisitor {

    private val finalValue: Value = Value()
    private var variables: MutableMap<String, String> = mutableMapOf()
    private var terminalPrinter: TerminalPrinter = TerminalPrinter()

    private fun checkType(name: String, type: String) {
        if (type == "string") {
            if (!finalValue.getStringRegex().equals(name)) {
                throw Exception("Type mismatch")
            }
        }
        if (type == "int") {
            if (!finalValue.getNumberRegex().equals(name)) {
                throw Exception("Type mismatch")
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
        finalValue.assigation(varName)
        checkType(varName, valueType)
        variables[varName] = valueType
    }

    override fun visit(assignment: Assignment) {
        val varName = assignment.name
        val value = assignment.value
        value.accept(finalValue)
        val type: String = variables[varName] ?: throw IllegalArgumentException("Variable $varName is not declared")
        checkType(varName, type)
        finalValue.assigation(varName)
    }

    override fun visit(print: Print) {
        val cotent = print.content
        cotent.accept(finalValue)
        terminalPrinter.print(finalValue.getExpressionResult())
    }
}

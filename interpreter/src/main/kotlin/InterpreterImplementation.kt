import node.NodeVisitor

class InterpreterImplementation : NodeVisitor {

    private val finalValue: Value = Value()
    private var variables: MutableMap<String, String> = mutableMapOf()
    var terminalPrinter: TerminalPrinter = TerminalPrinter()

    fun checkType(name: String, type: String) {
        if (type.equals("string")) {
            if (!finalValue.getStringRegex().equals(name)) {
                throw Exception("Type mismatch")
            }
        }
        if (type.equals("int")) {
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
        var varName = declaration.getVarName()
        var value = declaration.getValue()
        var valueType = declaration.getType()
        finalValue.declaration(varName)
        value.accept(finalValue)
        finalValue.assigation(varName)
        checkType(varName, valueType)
        variables.put(varName, valueType)
    }

    override fun visit(assignment: Assignment) {
        val varName = assignment.name
        val value = assignment.value
        value.accept(finalValue)
        val type: String = variables.get(varName) ?: throw IllegalArgumentException("Variable $varName is not declared")
        checkType(varName, type)
        finalValue.assigation(varName)
    }

    override fun visit(print: Print) {
        val cotent = print.content
        cotent.accept(finalValue)
        terminalPrinter.print(finalValue.getExpressionResult())
    }
}

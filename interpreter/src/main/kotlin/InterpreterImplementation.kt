
open class InterpreterImplementation : AbstractInterpreterVisitor() {

    init {
        finalValue = Value()
    }

    override fun checkType(name: String, type: String) {
        super.checkType(name, type)
        if (type == "boolean") {
            if (!isBoolean(finalValue.expressionResult)) {
                throw Exception("Type mismatch")
            }
        }
    }

    open fun isBoolean(result: String): Boolean {
        return result == "true" || result == "false"
    }

    override fun visit(condition: Condition) {
        val booleanValue = condition.booleanValue
        if (booleanValue == "true") {
            condition.ifCode.accept(this)
        } else if (booleanValue == "false") {
            condition.elseCode.accept(this)
        }
    }
}

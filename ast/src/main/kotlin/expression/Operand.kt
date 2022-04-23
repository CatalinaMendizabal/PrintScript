package expression

enum class Operand(val string: String) {
    SUM("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    companion object {
        fun getOperand(string: String): Operand? {
            for (operand in values()) {
                if (operand.string == string) {
                    return operand
                }
            }
            return null
        }
    }
}

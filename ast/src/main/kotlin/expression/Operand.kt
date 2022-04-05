package expression

enum class Operand(val string: String) {
    SUM("+"), SUB("-"), MUL("*"), DIV("/");

    companion object {
        fun getOperand(string: String): Operand {
            for (operand in values()) {
                if (operand.string == string) {
                    return operand
                }
            }
            throw IllegalArgumentException("Unknown operand: $string")
        }
    }
}

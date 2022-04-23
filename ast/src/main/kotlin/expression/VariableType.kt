package expression

enum class VariableType(val variable: String) {
    NUMBER("number"),
    STRING("String"),
    BOOLEAN("boolean");

    companion object {
        fun getVariableType(string: String): VariableType? {
            return values().find { it.variable == string }
        }
    }
}

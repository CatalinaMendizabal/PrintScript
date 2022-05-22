package edu.austral.ingsis.g3.lexer.lexerEnums

enum class Version(val version: String) {
    V1_0("1.0"),
    V1_1("1.1");

    companion object {
        fun getVariableType(string: String): Version? {
            return when (string) {
                "1.0" -> V1_0
                "1.1" -> V1_1
                else -> null
            }
        }
    }
}

package lexerEnums

import org.austral.ingsis.printscript.common.TokenType

enum class Keyword(override val type: String) : TokenType {
    IDENTIFIER("identifier"),
    LITERAL("literal"),
    NUMBER("Number"),
    STRING("String")
    // BOOLEAN
}

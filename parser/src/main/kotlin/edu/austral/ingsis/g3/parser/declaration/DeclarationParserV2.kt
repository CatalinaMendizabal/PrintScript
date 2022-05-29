package edu.austral.ingsis.g3.parser.declaration

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import edu.austral.ingsis.g3.parser.function.FunctionParserV2
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

open class DeclarationParserV2(@NotNull stream: TokenIterator) : AbstractDeclarationParser(stream) {

    init {
        functionParser = FunctionParserV2(stream)
    }

    protected fun consumeDeclarationKeyword(): Boolean {
        var isConst = false
        if (peek(TokenTypes.CONST) != null) {
            consume(TokenTypes.CONST)
            isConst = true
        } else consume(TokenTypes.LET)

        return isConst
    }
}

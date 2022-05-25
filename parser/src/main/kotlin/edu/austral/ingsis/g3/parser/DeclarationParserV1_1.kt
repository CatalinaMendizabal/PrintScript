package edu.austral.ingsis.g3.parser

import edu.austral.ingsis.g3.lexer.lexerEnums.TokenTypes
import org.austral.ingsis.printscript.parser.TokenIterator
import org.jetbrains.annotations.NotNull

open class DeclarationParserV1_1(@NotNull stream: TokenIterator) : AbstractDeclarationParser(stream) {

    init {
        functionParser = FunctionParserV1_1(stream)
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

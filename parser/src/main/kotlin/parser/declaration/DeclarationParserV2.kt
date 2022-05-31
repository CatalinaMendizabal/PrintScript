package parser.declaration

import enums.TokenTypes
import org.austral.ingsis.printscript.parser.TokenIterator
import parser.function.FunctionParserV2

open class DeclarationParserV2(stream: TokenIterator) :
    AbstractDeclarationParser(stream) {
    init {
        functionParser = FunctionParserV2(stream)
    }

    override fun consumeDeclarationKeyword(): Boolean {
        var isConst = false
        if (peek(TokenTypes.CONST) !== null) {
            consume(TokenTypes.CONST)
            isConst = true
        } else {
            consume(TokenTypes.LET)
        }
        return isConst
    }
}

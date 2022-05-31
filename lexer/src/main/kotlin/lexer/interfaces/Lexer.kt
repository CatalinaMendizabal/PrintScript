package lexer.interfaces

import PrintScript.lexer.inputContent.Content
import org.austral.ingsis.printscript.common.Token

interface Lexer {
    fun lex(source: Content): List<Token>
}

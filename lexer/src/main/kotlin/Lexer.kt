package PrintScript.lexer

import PrintScript.lexer.inputContent.Content
import org.austral.ingsis.printscript.common.Token

interface Lexer {
    fun lex(input: Content): List<Token>
}

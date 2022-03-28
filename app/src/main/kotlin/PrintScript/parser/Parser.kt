package PrintScript.parser

import PrintScript.ast.statement.Statement
import org.austral.ingsis.printscript.common.Token

interface Parser {
    fun parse(tokens: List<Token>): List<Statement>
}

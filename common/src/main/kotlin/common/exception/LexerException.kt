package exception

import org.austral.ingsis.printscript.common.LexicalRange

class LexerException(range: LexicalRange) :
    PrintScriptException(message = "Couldn't match token", module = "LEXER", range = range)

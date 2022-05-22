package edu.austral.ingsis.g3.lexer.exceptions

class LexerException(error: String, startCol: Int, row: Int) :
    Throwable(error + " at position: " + startCol + ", line: " + (row + 1))

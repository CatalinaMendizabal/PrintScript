package edu.austral.ingsis.g3.parser

class ParserException(error: String, startCol: Int, row: Int) :
    Throwable("$error at position: $startCol, line: $row")

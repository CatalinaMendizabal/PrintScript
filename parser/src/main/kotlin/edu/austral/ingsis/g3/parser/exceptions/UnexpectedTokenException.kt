package edu.austral.ingsis.g3.parser.exceptions

class UnexpectedTokenException(expected: String, startCol: Int, row: Int) : Throwable("Expected: \"" + expected + "\" at position: " + startCol + ", line: " + (row + 1))

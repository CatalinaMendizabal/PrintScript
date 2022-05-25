package edu.austral.ingsis.g3.parser

class UnexpectedKeywordException(keyword: String, fromCol: Int, row: Int) : Throwable("Unexpected keyword: " + keyword + " at position: " + fromCol + ", line:" + (row + 1))

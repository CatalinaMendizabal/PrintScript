package parser.exceptions

class UnexpectedKeywordException(keyword: String, fromCol: Int, row: Int) : Throwable("Unexpected keyword: " + keyword + " at position: " + fromCol + ", line:" + (row + 1))

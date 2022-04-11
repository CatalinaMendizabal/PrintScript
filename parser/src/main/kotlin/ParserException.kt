class ParserException(error: String, startCol: Int, row: Int) :
    Throwable(error + "\" at position: " + startCol + ", line: " + row + 1)

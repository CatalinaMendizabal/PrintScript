class LexerException(error: String, startCol: Int, row: Int) :
    Throwable(error + "\" at position: " + startCol + ", line: " + row + 1)

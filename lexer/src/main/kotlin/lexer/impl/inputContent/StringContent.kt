package PrintScript.lexer.inputContent

class StringContent(inputString: String) : Content {

    private val inputString = inputString

    override fun convertContent(): String {
        return inputString
    }
}

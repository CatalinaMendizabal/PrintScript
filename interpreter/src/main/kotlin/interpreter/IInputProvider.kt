package interpreter

interface IInputProvider {
    fun getInput(prompt: String): String
}

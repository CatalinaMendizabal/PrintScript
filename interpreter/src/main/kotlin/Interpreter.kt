import expression.Function

interface Interpreter {
    fun interpret(ast: Function)
}

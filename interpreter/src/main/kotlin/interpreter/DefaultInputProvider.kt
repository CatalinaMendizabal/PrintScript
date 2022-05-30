package interpreter

import java.util.Scanner

class DefaultInputProvider : IInputProvider {
    override fun getInput(prompt: String): String {
        println(prompt)
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }
}

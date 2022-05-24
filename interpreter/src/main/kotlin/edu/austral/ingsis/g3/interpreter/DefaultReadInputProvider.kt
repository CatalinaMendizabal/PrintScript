package edu.austral.ingsis.g3.interpreter

import java.util.Scanner

class DefaultReadInputProvider : ReadInputProvider {

    override fun getInput(prompt: String): String {
        println(prompt)
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().trim()
    }
}

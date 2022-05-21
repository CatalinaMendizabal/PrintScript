package edu.austral.ingsis.g3.interpreter

interface ReadInputProvider {

    fun getInput(prompt: String): String
}

package edu.austral.ingsis.g3.lexer.exceptions

class VersionException(error: String) :
    Throwable("$error is not supported on version 1.0")

package exception

import org.austral.ingsis.printscript.common.LexicalRange

open class PrintScriptException(message: String, module: String? = null, range: LexicalRange? = null) :
    Exception("${module ?: "PRINTSCRIPT"} EXCEPTION: " + message + if (range != null) " at ${range.startLine}:${range.startCol}" else "")

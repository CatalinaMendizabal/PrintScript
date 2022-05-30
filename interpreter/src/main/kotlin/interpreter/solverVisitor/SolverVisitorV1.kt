package interpreter.solverVisitor

import java.util.HashMap

class SolverVisitorV1 : AbstractSolverVisitor {

    constructor() {}

    constructor(variables: Map<String, String>) : super(variables as HashMap<String?, String?>)
}

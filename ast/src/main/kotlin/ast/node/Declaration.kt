package ast.node

import ast.expression.Expression

class Declaration : Node {
    var varName: String
    var type: String
    var isConstant = false
    var value: Expression? = null

    constructor(varName: String, type: String) {
        this.varName = varName
        this.type = type
    }

    constructor(varName: String, type: String, isConstant: Boolean) {
        this.varName = varName
        this.type = type
        this.isConstant = isConstant
    }

    constructor(varName: String, type: String, isConstant: Boolean, value: Expression) {
        this.varName = varName
        this.type = type
        this.isConstant = isConstant
        this.value = value
    }

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return if (value != null) "Declaration(" + "varName=" + varName + ", type=" + type + ", value=" + value.toString() + ')' else "Declaration(varName=$varName, type=$type)"
    }
}

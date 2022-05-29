import expression.Expression
import node.Node
import node.NodeVisitor

class Declaration : Node {

    private val varName: String
    private val type: String
    private lateinit var value: Expression
    var isConst: Boolean = false

    constructor(varName: String, type: String, value: Expression) {
        this.varName = varName
        this.type = type
        this.value = value
    }

    constructor(varName: String, type: String, isConst: Boolean) {
        this.varName = varName
        this.type = type
        this.isConst = isConst
    }

    constructor(varName: String, type: String, isConst: Boolean, value: Expression) {
        this.varName = varName
        this.type = type
        this.isConst = isConst
        this.value = value
    }

    constructor(varName: String, type: String) {
        this.varName = varName
        this.type = type
    }

    override fun accept(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return if (this::value.isInitialized) {
            "let $varName: $type = $value"
        } else {
            "let $varName: $type"
        }
    }

    // Getters
    fun getVarName(): String {
        return varName
    }

    fun getType(): String {
        return type
    }

    fun getValue(): Expression {
        return value
    }
}

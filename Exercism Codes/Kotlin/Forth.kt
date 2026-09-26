import java.util.Stack

class Forth {

    fun evaluate(vararg line: String): List<Int> {
        val tokens = mutableListOf<String>()
        val func = mutableListOf<List<String>>()
        val funcName = mutableListOf<String>()

        for (instruction in line) {
            val f = Regex("""[^ :;]+""").findAll(instruction.lowercase()).map { it.value }.toList()
            if (instruction.matches(Regex("""(^:.*;$)"""))) {
                if (isNumber(f[0])) {
                    throw Exception("illegal operation")
                }
                funcName.add(0, f[0])
                func.add(0, f.drop(1))
            }
            else {
                tokens.addAll(f)
            }
        }
        return call(Stack<Int>(), tokens, func, funcName, 0)
    }

    fun call(stack: Stack<Int>, tokens: List<String>, func: List<List<String>>, funcName: List<String>, where: Int): Stack<Int> {
        for (token in tokens) {
            val index = funcName.indexOf(token)
            if (index != -1) {
                call(stack, func.subList(where, func.size)[index], func, funcName, index + 1)
                continue
            }
            if (isNumber(token)) {
                stack.push(token.toInt())
                continue
            }
            if (token == "+") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                stack.push(stack.pop() + stack.pop())
                continue
            }
            if (token == "-") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                val first = stack.pop()
                val second = stack.pop()
                stack.push(second - first)
                continue
            }
            if (token == "*") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                stack.push(stack.pop() * stack.pop())
                continue
            }
            if (token == "/") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                val first = stack.pop()
                if (first == 0) {
                    throw Exception("divide by zero")
                }
                val second = stack.pop()
                stack.push(second / first)
                continue
            }
            if (token == "dup") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                stack.push(stack.peek())
                continue
            }
            if (token == "drop") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                stack.pop()
                continue
            }
            if (token == "swap") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                val first = stack.pop()
                val second = stack.pop()
                stack.push(first)
                stack.push(second)
                continue
            }
            if (token == "over") {
                if (stack.isEmpty()) {
                    throw Exception("empty stack")
                }
                if (stack.size < 2) {
                    throw Exception("only one value on the stack")
                }
                stack.push(stack.elementAt(stack.size - 2))
                continue
            }
            throw Exception("undefined operation")
        }
        return stack
    }

    fun isNumber(token: String): Boolean {
        try {
            token.toInt()
            return true
        }
        catch (e: NumberFormatException) {
            return false
        }
    }
}

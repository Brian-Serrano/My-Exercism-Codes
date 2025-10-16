object MatchingBrackets {

    fun isValid(input: String): Boolean {
        val stack = mutableListOf<Char>()

        for (x in input) {
            if ("([{".contains(x)) {
                stack.add(x)
            }
            if (x == ')') {
                if (stack.isNotEmpty() && stack.last() == '(') {
                    stack.removeLast()
                }
                else return false
            }
            if (x == ']') {
                if (stack.isNotEmpty() && stack.last() == '[') {
                    stack.removeLast()
                }
                else return false
            }
            if (x == '}') {
                if (stack.isNotEmpty() && stack.last() == '{') {
                    stack.removeLast()
                }
                else return false
            }
        }

        return stack.isEmpty()
    }
}

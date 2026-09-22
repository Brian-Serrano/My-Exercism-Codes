object Transpose {
    fun transpose(input: List<String>): List<String> {
        return if (input.isNotEmpty()) {
            val paddedInput = pad(input.toMutableList())
            List(paddedInput[0].length) { index ->
                val sb = StringBuilder()
                paddedInput.forEach {
                    if (it.length > index) {
                        sb.append(it[index])
                    }
                }
                sb.toString()
            }
        } else input
    }
    private fun pad(input: MutableList<String>): List<String> {
        for (i in (1..<input.size).reversed()) {
            if (input[i].length > input[i - 1].length) {
                input[i - 1] += " ".repeat(input[i].length - input[i - 1].length)
            }
        }
        return input.toList()
    }
}
object RunLengthEncoding {

    fun encode(input: String): String {
        var charRepeat = 1
        val result = mutableListOf<String>()
        for (idx in input.indices) {
            if (input.length > idx + 1 && input[idx] == input[idx + 1]) {
                charRepeat++
            } else {
                result.add("${if (charRepeat > 1) charRepeat else ""}${input[idx]}")
                charRepeat = 1
            }
        }
        return result.joinToString("")
    }

    fun decode(input: String): String {
        return Regex("\\d*.").findAll(input).joinToString("") {
            val char = it.value.last().toString()
            val number = it.value.substring(0, it.value.length - 1)
            char.repeat(if (number.isEmpty()) 1 else number.toInt())
        }
    }
}
